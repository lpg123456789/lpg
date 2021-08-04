package gk.common.shine.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import gk.common.shine.message.Message;


public abstract interface IClient {
	void doCommand(IoSession paramIoSession, IoBuffer paramIoBuffer);

	void messageSent(Message message);

	void sessionCreate(IoSession paramIoSession);

	void sessionOpened(IoSession paramIoSession);

	void sessionClosed(IoSession paramIoSession);

	void exceptionCaught(IoSession paramIoSession, Throwable paramThrowable);

	void sessionIdle(IoSession paramIoSession, IdleStatus paramIdleStatus);

}
