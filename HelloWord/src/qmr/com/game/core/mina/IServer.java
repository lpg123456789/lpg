package qmr.com.game.core.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public interface IServer {
    int GATE_SERVER = 1;
    int GAME_SERVER = 2;
    int WORLD_SERVER = 3;
    int PUBLIC_SERVER = 4;

    void doCommand(IoSession var1, IoBuffer var2);

    void sessionCreate(IoSession var1);

    void sessionOpened(IoSession var1);

    void sessionClosed(IoSession var1);

    void exceptionCaught(IoSession var1, Throwable var2);

    void sessionIdle(IoSession var1, IdleStatus var2);
}