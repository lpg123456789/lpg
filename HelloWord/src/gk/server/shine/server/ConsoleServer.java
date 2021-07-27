package gk.server.shine.server;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import gk.common.shine.mina.impl.EchoServer;
import gk.common.shine.server.config.ServerConfig;
import gk.common.shine.utils.MemoryLogUtil;
import gk.server.shine.Globals;

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
    }

    @Override
    protected void stop() {

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
        String value = null;
        if (msg.equals("version")) {
           
        } else {
        	//HotfixUtil 通过值决定传什么值
        }
        return value;
    }

    /**
     * 手动执行fullGc
     * 
     * @return
     */
    private String gc() {
        String result = "gc success";
        try {
            MemoryLogUtil.printMemoryInfo();
            System.gc();
            MemoryLogUtil.printGcInfo();
            MemoryLogUtil.printMemoryInfo();
        } catch (Exception e) {
            log.error("gc error.", e);
            result = "gc fail";
        }
        return result;
    }


    private String kick() {
        String value = "success";
        try {
            log.info("begin logout all player");
        } catch (Exception e) {
            this.log.error("logout all player error", e);
            value = "failure";
        }
        return value;
    }

}
