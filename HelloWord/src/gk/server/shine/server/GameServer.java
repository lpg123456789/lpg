package gk.server.shine.server;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gk.common.shine.command.Handler;
import gk.common.shine.message.Message;
import gk.common.shine.mina.impl.MinaServer;
import gk.server.shine.Globals;
import gk.server.shine.manager.ManagerPool;
import gk.server.shine.manager.exception.ServerStarupError;
import gk.server.shine.message.MessagePool;
import gk.server.shine.persistence.manager.SaverManager;

public class GameServer extends MinaServer {
	
	protected final CharsetDecoder decoder = (Charset.forName("UTF-8")).newDecoder();

	private Logger closelog = LoggerFactory.getLogger("SESSIONCLOSE");
	// 连接创建日志
	private Logger createlog = LoggerFactory.getLogger("SESSIONCREATE");
	// 丢弃指令日志
	private Logger droplog = LoggerFactory.getLogger("DROPCOMMAND");

	public ServerState status = ServerState.LOADING;

	// 服务器实例
	private static GameServer _instance;

	private static Object objLock = new Object();
	
	private final ConcurrentMap<Integer, ExecPlayerServer> execPlayerServers = new ConcurrentHashMap<>();
	
	//qz定点调用
	// 调度工厂
    SchedulerFactory schedulerFactory = null;
    // 调度器
    private Scheduler scheduler = null;

	private GameServer() {
		this(Globals.properties);
	}

	protected GameServer(Properties properties) {
		super(properties, false);
		try {
			schedulerFactory = new StdSchedulerFactory(Globals.QUARTS_PROP);
			scheduler = schedulerFactory.getScheduler();
		} catch (SchedulerException e) {
			log.error("init error.", e);
		}
	}

	public static GameServer getInstance() {
		if (_instance == null) {
			synchronized (objLock) {
				if (_instance == null) {
					_instance = new GameServer(Globals.properties);
				}
			}
		}
		return _instance;
	}

	@Override
	public void run() {
		try {
			startup();
		} catch (ServerStarupError e) {
			log.info("server|logic|start error.shutdown.", e);
			System.exit(1);
		}
	}

	private void startup() {
		
		SaverManager.getInstance().startup();
		
		// 玩家处理线程分区 10个
        for (int i = 1; i < 2; i++) {
            int id = i + 1;
            addNewPlayerThread(id);
        }
		
		ManagerPool.getInstance().init();
		// 开始监听
		super.run();
		status = ServerState.RUN;
		log.info("server|logic|start success");
	}
	
	
	 /**
     * 新增一个玩家线程
     *
     * @param execId
     * @return
     */
    private void addNewPlayerThread(int execId) {
        String threadName = "logic" + execId;
        ExecPlayerServer playerThread = new ExecPlayerServer(threadName, 500, execId);
        playerThread.addEvent();
        execPlayerServers.putIfAbsent(execId, playerThread);
        playerThread.start();
        log.info("addNewPlayerThread:{}", execId);
    }

	

	@Override
	public void sessionCreate(IoSession paramIoSession) {
		log.info("sessionCreate");
	}

	@Override
	public void sessionOpened(IoSession paramIoSession) {
		log.info("sessionOpened");
	}

	@Override
	public void sessionIdle(IoSession paramIoSession, IdleStatus paramIdleStatus) {
	

	}

	@Override
	public void sessionClosed(IoSession paramIoSession) {
		log.info("sessionClosed");
	}

	@Override
	public void exceptionCaught(IoSession paramIoSession, Throwable paramThrowable) {
		log.info("sessionCreate");

	}

	@Override
	public void doCommand(IoSession iosession, IoBuffer paramIoBuffer) {
		// TODO Auto-generated method stub
		try {
         			String str = paramIoBuffer.getString(decoder).trim();
			 log.info("doCommand:" + str);
			 
			 //没有加密 简单处理
			 String[] temp=str.split("_");
			 int msgId=Integer.parseInt(temp[0]);
			 String msg=temp[1];
			 
			 Message message = MessagePool.getInstance().createMessage(msgId);
			 Handler handler = MessagePool.getInstance().createHandler(msgId);
			 if(message==null||handler==null) {
				 log.info("message doCommand");
				 return;
			 }
			 message.setSession(iosession);
			 message.setData(msg);
			 //模块调用
			 handler.setMessage(message);
			 handler.execute();
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
	}
	
	public void handleMessage(Message msg, IoSession session) {

	}

	@Override
	protected void stop() {
		// TODO Auto-generated method stub

	}

}
