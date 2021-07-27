package gk.common.shine.mina.handler;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import gk.common.shine.mina.IServer;


public class EchoServerHandler extends IoHandlerAdapter {
	protected static Logger log = Logger.getLogger(EchoServerHandler.class);
	private IServer server;

	public EchoServerHandler(IServer server) {
		this.server = server;
	}

	@Override
	public void sessionCreated(IoSession iosession) throws Exception {
		this.server.sessionCreate(iosession);
	}

	@Override
	public void sessionOpened(IoSession iosession) throws Exception {
		this.server.sessionOpened(iosession);
	}

	@Override
	public void sessionClosed(IoSession iosession) throws Exception {
		this.server.sessionClosed(iosession);
	}

	@Override
	public void sessionIdle(IoSession iosession, IdleStatus idlestatus) throws Exception {
		this.server.sessionIdle(iosession, idlestatus);
	}

	@Override
	public void exceptionCaught(IoSession iosession, Throwable cause) throws Exception {
		this.server.exceptionCaught(iosession, cause);
	}

	@Override
	public void messageReceived(IoSession iosession, Object obj) throws Exception {
		this.server.doCommand(iosession, (IoBuffer) obj);
	}

	@Override
	public void messageSent(IoSession iosession, Object obj) throws Exception {
	}
}