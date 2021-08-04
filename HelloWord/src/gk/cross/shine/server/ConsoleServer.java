package gk.cross.shine.server;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import gk.common.shine.mina.impl.EchoServer;
import gk.common.shine.server.config.ServerConfig;
import gk.cross.shine.Globals;

import java.nio.charset.CharacterCodingException;

/**
 * 控制台服务
 * 
 * @author oyxz
 * @date 2018-12-06
 * @version 1.0
 */
public class ConsoleServer extends EchoServer {
	private static ConsoleServer _instance = null;
	private static Object objLock = new Object();

	protected ConsoleServer(ServerConfig serverConfig) {
		super(serverConfig);
	}

	public static ConsoleServer getInstance() {
		if (_instance == null) {
			synchronized (objLock) {
				if (_instance == null) {
					ServerConfig config = new ServerConfig();
					config.setHostName(Globals.properties.getProperty("gm.console.host").trim());
					config.setPort(Integer.parseInt(Globals.properties.getProperty("gm.console.port").trim()));
					_instance = new ConsoleServer(config);
				}
			}
		}
		return _instance;
	}

	@Override
	public void run() {
		super.run();
		log.info("server|cross|start success");
	}

	@Override
	protected void stop() {
		log.info("server|cross|shutdown success");
	}

	@Override
	public void doCommand(IoSession paramIoSession, IoBuffer message) {
		IoBuffer buffer = (IoBuffer) message;
		try {
			String str = buffer.getString(decoder).trim();
			log.info("recv console:" + str);
			String value = dispatch(str);
			log.info("resp console:" + value);
			if (!value.isEmpty()) {
				response(paramIoSession, value);
			}
		} catch (CharacterCodingException e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public void sessionCreate(IoSession paramIoSession) {
	}

	@Override
	public void sessionOpened(IoSession paramIoSession) {
	}

	@Override
	public void sessionClosed(IoSession paramIoSession) {
	}

	@Override
	public void exceptionCaught(IoSession paramIoSession, Throwable paramThrowable) {
	}

	@Override
	public void sessionIdle(IoSession paramIoSession, IdleStatus paramIdleStatus) {
	}

	private String dispatch(String msg) {
		return "test";
	}

}
