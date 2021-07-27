package gk.common.shine.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public abstract interface IServer
{
  public abstract void doCommand(IoSession paramIoSession, IoBuffer paramIoBuffer);

  public abstract void sessionCreate(IoSession paramIoSession);

  public abstract void sessionOpened(IoSession paramIoSession);

  public abstract void sessionClosed(IoSession paramIoSession);

  public abstract void exceptionCaught(IoSession paramIoSession, Throwable paramThrowable);

  public abstract void sessionIdle(IoSession paramIoSession, IdleStatus paramIdleStatus);
}