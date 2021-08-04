package gk.common.shine.inner;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gk.common.shine.command.Handler;
import gk.common.shine.message.Message;
import gk.common.shine.mina.IClient;
import gk.common.shine.mina.code.InnerServerProCodeFactor;
import gk.common.shine.mina.handler.InnerClientHandler;
import gk.common.shine.server.config.ServerConfig;
import gk.common.shine.utils.WriteUtil;

/**
 * 内网连接客户端<br>
 * 服务器之间的通信 客户端的一方
 * 
 * @author hdh
 *
 */
public abstract class InnerClient implements IClient, Runnable {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 心跳间隔
	 */
	public final static long DEFAULT_HEARTBEAT_INTERVAL = TimeUnit.SECONDS.toMillis(3);;
	/**
	 * 远程服务器的配置
	 */
	protected final ServerConfig remoteConfig;
	/**
	 * 连接器<br>
	 * 客户端
	 */
	protected NioSocketConnector socket;
	/**
	 * 与该远程服务器的链接
	 */
	protected IoSession session;
	/**
	 * 是否正在链接中
	 */
	protected volatile boolean connecting = false;

	protected String localServerName;

	protected String remoteServerName;

	public InnerClient(ServerConfig remoteConfig) {
		super();
		this.remoteConfig = remoteConfig;
	}

	/**
	 * 该客户端的服务器id
	 * 
	 * @return
	 */
	public abstract int getServerId();

	/**
	 * 该客户端的服务器类型
	 * 
	 * @return
	 */
	public abstract ServerType getLocalServerType();

	/**
	 * 远程服务器类型
	 * 
	 * @return
	 */
	public abstract ServerType getRemoteServerType();

	protected void init() {
		this.socket = new NioSocketConnector();
		// 编解码
		this.socket.getFilterChain().addLast("codec", new ProtocolCodecFilter(new InnerServerProCodeFactor()));
		// 接受协议时的处理线程
		OrderedThreadPoolExecutor threadpool = new OrderedThreadPoolExecutor(500);
		this.socket.getFilterChain().addLast("threadPool", new ExecutorFilter(threadpool));
		this.socket.setDefaultRemoteAddress(new InetSocketAddress(remoteConfig.getHostName(), remoteConfig.getPort()));

		// 接受大小
		int recsize = 10 * 1024;
		// 发送大小
		int sendsize = 10 * 1024;
		SocketSessionConfig sc = this.socket.getSessionConfig();
		sc.setReceiveBufferSize(recsize);
		sc.setSendBufferSize(sendsize);
		sc.setSoLinger(0);
		this.socket.setHandler(new InnerClientHandler(this));
		localServerName = buildLocalClientName();
		remoteServerName = buildRemoteServerName();
	}

	protected void startup() {
		connect(true);
		startTickTask();
		logger.info("{} startup.", localServerName);
	}

	/**
	 * 开始定时任务
	 */
	protected abstract void startTickTask();

	@Override
	public void run() {
		init();
		startup();
	}

	@Override
	public void messageSent(Message message) {
		if (message == null) {
			throw new NullPointerException("send message error.msg is null.");
		}
		if (!isSessionValid()) {
			logger.error(remoteServerName + " is not connected.");
			return;
		}
		WriteUtil.writeMsgAndFlush(session, message);
	}

	@Override
	public void doCommand(IoSession session, IoBuffer buffer) {
		try {
			// 获取消息体
			Message msg = buildMessage(session, buffer);
			if (msg == null) {
				return;
			}
			// 生成处理函数
			receiveMessage(session, msg);
		} catch (Exception e) {
			logger.error("receive msg error.", e);
		}
	}

	/**
	 * 接受消息 分派到对应处理方法中
	 * 
	 * @param session
	 * @param msg
	 */
	protected void receiveMessage(IoSession session, Message msg) {
		handleMessage(msg);
	}

	/**
	 * 处理普通消息
	 * 
	 * @param msg
	 */
	protected void handleMessage(Message msg) {
		int msgId = msg.getId();
		// 生成处理函数
		Handler handler = createHandler(msgId);
		if (handler == null) {
			logger.info("handler is null,msgId:" + msgId);
			return;
		}
		handler.setMessage(msg);
		handler.execute();
	}

	protected abstract Message buildMessage(IoSession session, IoBuffer buffer);

	protected abstract Handler createHandler(int msgId);

	/**
	 * 若断开链接 则重连<br>
	 * 若链接正常 则发送心跳包<br>
	 */
	public void tick() {
		if (isSessionValid()) {
			//sendHeartbeatMsg();
		} else {
			// 链接已失效
			if (connecting) {
				// 重连中
				return;
			}
			// 重连
			connect(false);
		}
	}

	/**
	 * 与服务端的会话是否有效
	 * 
	 * @return
	 */
	public boolean isSessionValid() {
		if (session == null || !session.isConnected()) {
			return false;
		}
		return true;
	}

	/**
	 * 连接服务器
	 * 
	 * @param block 是否阻塞当前进程到连接成功
	 */
	public void connect(boolean block) {
		ConnectFuture future = doConnect();
		if (!block) {
			return;
		}
		future.awaitUninterruptibly();
		// 阻塞到启动成功
		while (!future.isConnected()) {
			try {
				Thread.sleep(5000L);
				future = doConnect();
				future.awaitUninterruptibly();
			} catch (Exception e) {
				logger.error("connect " + remoteServerName + " error.", e);
			}
		}
	}

	protected ConnectFuture doConnect() {
		logger.info(remoteServerName + " connecting.");
		connecting = true;
		ConnectFuture connectFuture = this.socket.connect();
		connectFuture.addListener(new IoFutureListener<ConnectFuture>() {

			@Override
			public void operationComplete(ConnectFuture future) {
				connecting = false;
				Throwable exception = future.getException();
				if (exception != null) {
					logger.error(remoteServerName + " connect error." + exception.getMessage());
				}
			}
		});
		return connectFuture;
	}

	@Override
	public void sessionCreate(IoSession session) {
		this.session = session;
		logger.info(remoteServerName + " session created.");
	}

	@Override
	public void sessionOpened(IoSession session) {
		this.session = session;
		logger.info(remoteServerName + " session opened.");
		afterSessionOpen(session);
	}

	/**
	 * 连接成功后执行
	 * 
	 * @param session
	 */
	protected void afterSessionOpen(IoSession session) {
		sendRegisterMsg();
	}

	protected void sendRegisterMsg() {
		/* ReqInnerRegisterServerMessage msg = new ReqInnerRegisterServerMessage();
		msg.setServerId(getServerId());
		msg.setServerType(getLocalServerType().getType());
		messageSent(msg);*/
	}

	@Override
	public void sessionClosed(IoSession session) {
		logger.warn(remoteServerName + "  close.");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable paramThrowable) {
		logger.error(remoteServerName + " exceptionCaught." + paramThrowable.getMessage());
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus paramIdleStatus) {
	}

	public IoSession getSession() {
		return session;
	}

	/**
	 * 获取该内连接的客户端名
	 * 
	 * @return
	 */
	public String buildLocalClientName() {
		StringBuffer sb = new StringBuffer();
		sb.append(getLocalServerType().name());
		sb.append("-");
		sb.append(getRemoteServerType().name());
		sb.append("_InnerClient");
		return sb.toString();
	}

	/**
	 * 远程服务器名
	 * 
	 * @return
	 */
	public String buildRemoteServerName() {
		StringBuffer sb = new StringBuffer();
		sb.append(getRemoteServerType().name());
		sb.append("[");
		sb.append(remoteConfig.getHostName()).append(":").append(remoteConfig.getPort());
		sb.append("]");
		return sb.toString();
	}

	public ServerConfig getRemoteConfig() {
		return remoteConfig;
	}

	public String getLocalServerName() {
		return localServerName;
	}

	public String getRemoteServerName() {
		return remoteServerName;
	}
}