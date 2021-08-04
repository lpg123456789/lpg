package gk.common.shine.mina.handler;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import gk.common.shine.mina.IClient;

/**
 * 内网连接 客户端处理类
 * 
 * @author hdh
 *
 */
public class InnerClientHandler extends IoHandlerAdapter {
	protected static Logger log = Logger.getLogger(InnerClientHandler.class);
	private IClient client;

	public InnerClientHandler(IClient client) {
		this.client = client;
	}

	@Override
	public void sessionCreated(IoSession iosession) throws Exception {
		this.client.sessionCreate(iosession);
	}

	@Override
	public void sessionOpened(IoSession iosession) throws Exception {
		this.client.sessionOpened(iosession);
	}

	@Override
	public void sessionClosed(IoSession iosession) throws Exception {
		this.client.sessionClosed(iosession);
	}

	@Override
	public void sessionIdle(IoSession iosession, IdleStatus idlestatus) throws Exception {
		this.client.sessionIdle(iosession, idlestatus);
	}

	@Override
	public void exceptionCaught(IoSession iosession, Throwable cause) throws Exception {
		this.client.exceptionCaught(iosession, cause);
	}

	@Override
	public void messageReceived(IoSession iosession, Object obj) throws Exception {
		IoBuffer buffer = null;
		if (obj instanceof byte[]) {
			byte[] data = (byte[]) obj;
			buffer = IoBuffer.wrap(data);
		} else if (obj instanceof IoBuffer) {
			buffer = (IoBuffer) obj;
		} else {
			log.error("iosession send empty.obj:" + obj);
			return;
		}
		this.client.doCommand(iosession, buffer);
	}

	@Override
	public void messageSent(IoSession iosession, Object obj) throws Exception {
	}
}
