package gk.common.shine.inner;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import gk.common.shine.command.Handler;
import gk.common.shine.message.Message;
import gk.common.shine.mina.IServer;
import gk.common.shine.mina.code.InnerServerProCodeFactor;
import gk.common.shine.mina.handler.InnerServerHandler;
import gk.common.shine.server.Server;
import gk.common.shine.server.config.ServerConfig;
import gk.common.shine.server.structs.SessionAttType;
import gk.common.shine.utils.SessionUtil;
import gk.server.shine.server.ServerState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class InnerServer extends Server implements IServer {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	public final static long DEFAULT_HEARTBEAT_TIMEOUT = TimeUnit.SECONDS.toMillis(10);

	protected NioSocketAcceptor acceptor;
	

	protected volatile ServerState status = ServerState.LOADING;

	protected InnerServer(ServerConfig serverConfig) {
		super(serverConfig);
	}

	@Override
	public void run() {
		init();
		startup();
		super.run();
	}
	
	  /**
     * 获取该服务端的服务器类型
     * 
     * @return
     */
    public abstract ServerType getServerType();
    
    protected void init() {
        acceptor = new NioSocketAcceptor();

        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
        // add ssl support
        //if (serverConfig.isUseSsl()) {
            //addSSLSupport(chain);
        //}
        // 编解码
        // 和外网使用的不同
        chain.addLast("codec", new ProtocolCodecFilter(new InnerServerProCodeFactor()));

        OrderedThreadPoolExecutor threadpool = new OrderedThreadPoolExecutor(500);
        chain.addLast("threadPool", new ExecutorFilter(threadpool));

        int recsize = 10 * 1024; // 10k
        int sendsize = 64 * 1024; // 64k
        int timeout = 30;
        acceptor.setReuseAddress(true);
        SocketSessionConfig sc = acceptor.getSessionConfig();
        sc.setReuseAddress(true);
        sc.setReceiveBufferSize(recsize);
        sc.setSendBufferSize(sendsize);
        sc.setTcpNoDelay(true);
        sc.setSoLinger(0);
        sc.setIdleTime(IdleStatus.READER_IDLE, timeout);
        acceptor.setHandler(new InnerServerHandler(this));
        acceptor.setDefaultLocalAddress(new InetSocketAddress(serverConfig.getPort()));
    }
    
    /**
     * 启动内网服务器
     */
    protected void startup() {
        // 启动监听
        new Thread(new InnerConnectServer()).start();
        // 启动定时任务 清理过期链接
        startTickTask();
    }
    
    /**
     * 
     */
    protected abstract void startTickTask();

	@Override
	public void doCommand(IoSession session, IoBuffer buffer) {
		try {
			if (status != ServerState.RUN) {
				if (status == ServerState.STOPDING) {
					SessionUtil.closeSession(session, "server is closed");
				}
				buffer.clear();
				return;
			}
			// 获取消息体
			Message msg = buildMessage(session, buffer);
			if (msg == null) {
				return;
			}
			// 生成处理函数
			handleMessage(msg);
		} catch (Exception e) {
			logger.error("receive msg error.", e);
		}
	}
	
	/**
     * 处理普通消息
     * 
     * @param msg
     */
    protected void handleMessage(Message msg) {
        // 生成处理函数
        int id = msg.getId();
        Handler handler = createHandler(id);
        if (handler == null) {
            logger.info("handler is null,msgId:" + id);
            return;
        }
        handler.setMessage(msg);
        // 处理 FIXME
        handler.execute();
    }
	

	protected abstract Message buildMessage(IoSession session, IoBuffer buffer);

	protected abstract Handler createHandler(int msgId);

	
	
	@Override
	public void sessionCreate(IoSession session) {
		long now = System.currentTimeMillis();
		session.setAttribute(SessionAttType.SESSION_CREATE_TIME, now);
		session.setAttribute(SessionAttType.INNER_HEARTBEAT_TIME, now);
		logger.info("session[{}] created", session.getRemoteAddress());

	}

	@Override
	public void sessionOpened(IoSession session) {
		long now = System.currentTimeMillis();
		session.setAttribute(SessionAttType.SESSION_CREATE_TIME, now);
		session.setAttribute(SessionAttType.INNER_HEARTBEAT_TIME, now);
		logger.info("session[{}] opened", session.getRemoteAddress());
	}

	@Override
	public void sessionClosed(IoSession paramIoSession) {
		  logger.info("serverId:{} closed.");
	}
	
	@Override
	public void sessionIdle(IoSession paramIoSession, IdleStatus paramIdleStatus) {
	}
	
	@Override
    public void exceptionCaught(IoSession paramIoSession, Throwable paramThrowable) {
        logger.error(" exceptionCaught." + paramThrowable.getMessage());
    }

	
	/**
     * 内网连接服务器
     * 
     * @author hdh
     *
     */
    protected class InnerConnectServer implements Runnable {

        public void run() {
            int port = serverConfig.getPort();
            try {
                acceptor.bind();
                logger.info("Inner Server Start At Port " + port);
            } catch (IOException e) {
                logger.error("Inner Server Port " + port + "Already Use:" + e.getMessage());
                System.exit(1);
            }
        }
    }

}
