package gk.cross.shine.server;

import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import gk.common.shine.command.Handler;
import gk.common.shine.inner.InnerServer;
import gk.common.shine.inner.ServerType;
import gk.common.shine.message.Message;
import gk.common.shine.server.config.ServerConfig;
import gk.cross.shine.Globals;
import gk.cross.shine.manager.ManagerPool;
import gk.cross.shine.message.MessagePool;
import gk.server.shine.server.ServerState;

public class CrossServer extends InnerServer {

	private static CrossServer instance = buildCrossServer();

	/**
	 * 服务器id别名,key为子服服务器id,value为母服服务器id
	 */
	protected final ConcurrentMap<Integer, Integer> serverIdAlias = new ConcurrentHashMap<>();

	public static CrossServer getInstance() {
		return instance;
	}

	public ConcurrentMap<Integer, Integer> getServerIdAlias() {
		return serverIdAlias;
	}

	/**
	 * 注册子服id
	 */
	public void registerSubServerId(int serverId, int subServerId) {
		serverIdAlias.put(subServerId, serverId);
	}

	private CrossServer(ServerConfig serverConfig) {
		super(serverConfig);
	}

	private static CrossServer buildCrossServer() {
		Properties properties = Globals.properties;
		ServerConfig serverConfig = new ServerConfig();
		serverConfig.setHostName(properties.getProperty("cross.server.host"));
		serverConfig.setPort(Integer.parseInt(properties.getProperty("cross.server.port")));
		CrossServer server = new CrossServer(serverConfig);
		return server;
	}
	
	
	@Override
    public void run() {
        super.run();
        while (acceptor == null) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        status = ServerState.RUN;
    }
	
	@Override
	protected void init() {
	    ManagerPool.getInstance().init();
	    super.init();
	}
	

	@Override
	public ServerType getServerType() {
		 return ServerType.CROSS;
	}

	@Override
	protected void startTickTask() {
		
	}

	@Override
	protected void stop() {
		for (IoSession session : acceptor.getManagedSessions().values()) {
			session.closeNow();
		}
		status = ServerState.STOPDING;
		// FIXME 关服其他处理
		logger.info("server|cross|shutdown success");
		
	}

	@Override
	protected Message buildMessage(IoSession session, IoBuffer buffer) {
		int msgId = buffer.getInt();
		Message msg = MessagePool.getInstance().createMessage(msgId);
		if (msg == null) {
			logger.error("msg[{}] is null.", msgId);
			return null;
		}
		msg.read(buffer);
		msg.setSession(session);
		return msg;
	}

	@Override
	protected Handler createHandler(int msgId) {
		return MessagePool.getInstance().createHandler(msgId);
	}
}
