package gk.common.shine.utils;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import gk.common.shine.mina.code.websocket.WebSocketCodecPacket;
import gk.common.shine.server.structs.SessionAttType;

public class SessionUtil {
    private static Logger closelog = Logger.getLogger("SESSIONCLOSE");

    /**
     * 服务端主动关闭连接
     * 
     * @param session
     * @param reason
     */
    public static void closeSession(IoSession session, String reason) {
        closelog.info(session + "-->close [because] " + reason);
        session.setAttribute(SessionAttType.SESSION_CLOSE_INFO, reason);
        session.closeNow();
    }

    /**
     * 服务端主动关闭连接 断开前 发送未发完的消息
     * 
     * @param session
     * @param reason
     */
    public static void closeSessionOnFlush(IoSession session, String reason) {
        try {
            closelog.info(session + "-->close [because] " + reason);
            session.setAttribute(SessionAttType.SESSION_CLOSE_INFO, reason);
            if (session.isConnected()) {
                IoBuffer sendbuf = null;
                synchronized (session) {
                    if (session.containsAttribute(SessionAttType.SESSION_MSG_BUFFER)) {
                        sendbuf = (IoBuffer) session.removeAttribute(SessionAttType.SESSION_MSG_BUFFER);
                    }
                }
                if ((sendbuf != null) && (sendbuf.position() > 0)) {
                    sendbuf.flip();
                    session.write(WebSocketCodecPacket.buildPacket(sendbuf));
                }
            }
        } catch (Exception e) {
            closelog.error("closeSessionOnFlush error", e);
        } finally {
            session.closeOnFlush();
        }
    }

    /**
     * 获取该session的ip<br>
     * 若为nginx代理的 取的是握手时的头文件中的X-real-ip
     * 
     * @param session
     * @return
     */
    public static String getClientIp(IoSession session) {
        if (session.containsAttribute(SessionAttType.SESSION_REL_IP_ADDRESS)) {
            return (String) session.getAttribute(SessionAttType.SESSION_REL_IP_ADDRESS);
        }
        SocketAddress remoteAddress = session.getRemoteAddress();
        if (remoteAddress != null && remoteAddress instanceof InetSocketAddress) {
            InetSocketAddress address = (InetSocketAddress) remoteAddress;
            return address.getAddress().getHostAddress();
        }
        return "";
    }
}