package gk.common.shine.mina.code;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * 客户但消息解码
 * 
 * @author zhangzhen
 * @date 2017年9月5日
 * @version 1.0
 */
public class ServerProDecode extends ProtocolDecoderAdapter {

	protected Logger log = Logger.getLogger(ServerProDecode.class);

	// 消息内容最大长度
//	private static int MAX_SIZE = 10240;
	// 每分钟消息最大个数
//	private static int MAX_COUNT = 100;

	@Override
	public void decode(IoSession session, IoBuffer buff,
			ProtocolDecoderOutput out) throws Exception {
//		// 开始发送消息时间
//		long msgTime = 0L;
//		if (session.containsAttribute(SessionAttType.SESSION_MSG_TIME)) {
//			msgTime = ((Long) session
//					.getAttribute(SessionAttType.SESSION_MSG_TIME)).longValue();
//		}
//		// 当前session 发送消息的数量
//		int count = 0;
//		if (session.containsAttribute(SessionAttType.SESSION_MSG_NUM)) {
//			count = ((Integer) session
//					.getAttribute(SessionAttType.SESSION_MSG_NUM)).intValue();
//		}
//
//		// 收到消息以秒为间隔重置收到消息次数
//		if (System.currentTimeMillis() - msgTime > 1000L) {
//			if (count > 10) {
//				log.info(session + " --> 发送消息频繁num=:" + count);
//			}
//			msgTime = System.currentTimeMillis();
//			count = 0;
//		}
//
//		count++;
//		// 收到消息过多
//		if (count > MAX_COUNT) {
//			log.info(session + " --> 发送消息数量过多sendmsg:" + count
//					+ "-->close-->buf:" + buff.remaining() + "(" + buff + ")");
//			buff.clear();
//			SessionUtil.closeSession(session, "发送消息数量过多(" + count + ")");
//			return;
//		}
//		// 记录刷新每秒消息收到时间
//		session.setAttribute(SessionAttType.SESSION_MSG_TIME,
//				Long.valueOf(msgTime));
//		// 记录刷新每秒消息收到次数
//		session.setAttribute(SessionAttType.SESSION_MSG_NUM,
//				Integer.valueOf(count));
//
//		ServerContext context = null;
//		if (session.getAttribute(SessionAttType.SESSION_MSG_CONTEXT) != null)
//			context = (ServerContext) session
//					.getAttribute(SessionAttType.SESSION_MSG_CONTEXT);
//		if (context == null) {
//			context = new ServerContext();
//			session.setAttribute(SessionAttType.SESSION_MSG_CONTEXT, context);
//		}
//
//		IoBuffer io = context.getBuff();
//
//		io.put(buff);
//		while (true) {
//			io.flip();
//			if (io.remaining() < 4) {
//				io.compact();
//				break;
//			}
//			// 总长
//			int length = io.getInt();
//
//			if ((length > MAX_SIZE) || (length <= 0)) {
//				int pre = 0;
//				if (session.containsAttribute("PREMESSAGE")) {
//					pre = ((Integer) session.getAttribute("PREMESSAGE"))
//							.intValue();
//				}
//				int remain = io.remaining();
//				if (remain > 64) {
//					remain = 64;
//				}
//				StringBuffer strbuf = new StringBuffer();
//				for (int i = 0; i < remain / 4; i++) {
//					strbuf.append(" " + Integer.toHexString(io.getInt()));
//				}
//				SessionUtil.closeSession(session, "发送消息出错过长(" + length
//						+ "), 前一消息为(" + pre + "), 消息为(" + strbuf.toString()
//						+ ")");
//				break;
//			} else {
//				if (io.remaining() < length) {
//					io.rewind();
//
//					io.compact();
//					break;
//				}
//				int code = io.getInt();
//				code ^= 512;
//				code ^= (length - 4);
//				int preCode = 0;
//				if (session
//						.containsAttribute(SessionAttType.SESSION_MSG_CLIENT_CODE)) {
//					preCode = ((Integer) session
//							.getAttribute(SessionAttType.SESSION_MSG_CLIENT_CODE))
//							.intValue();
//				}
//
//				byte[] bytes = new byte[length - 4];
//				io.get(bytes);
//				int messageid = 0;
//				try {
//					messageid = (bytes[0] & 0xFF) << 24
//							| (bytes[1] & 0xFF) << 16 | (bytes[2] & 0xFF) << 8
//							| (bytes[3] & 0xFF) << 0;
//				} catch (Exception e) {
//					this.log.error(e, e);
//				}
//				out.write(bytes);
//				if (code == preCode) {
//					if (code == 0) {
//						this.log.debug(session + "发送消息序列成功，发包序列：" + code
//								+ ",当前消息号:" + messageid);
//					}
//					session.setAttribute(
//							SessionAttType.SESSION_MSG_CLIENT_CODE,
//							Integer.valueOf(++code));
//				} else {
//					StringBuffer buffer = new StringBuffer();
//					if (session
//							.containsAttribute(SessionAttType.SESSION_SESSION_IP)) {
//						buffer.append("ip:"
//								+ session
//										.getAttribute(SessionAttType.SESSION_SESSION_IP)
//								+ ",");
//					}
//					if (session
//							.containsAttribute(SessionAttType.SESSION_PLAYER_ID)) {
//						buffer.append("player:"
//								+ session
//										.getAttribute(SessionAttType.SESSION_PLAYER_ID)
//								+ ",");
//					}
//					if (session
//							.containsAttribute(SessionAttType.SESSION_USER_ID)) {
//						buffer.append("user:"
//								+ session
//										.getAttribute(SessionAttType.SESSION_USER_ID)
//								+ ",");
//					}
//					SessionUtil.closeSession(session, "[" + buffer.toString()
//							+ "]发送消息序列出错，发包序列：" + code + ",当前序列:" + preCode
//							+ ",当前消息号:" + messageid);
//				}
//				if (io.remaining() == 0) {
//					io.clear();
//					break;
//				}
//				io.compact();
//			}
//		}
	}

	@Override
	public void dispose(IoSession session) throws Exception {
//		if (session.getAttribute(SessionAttType.SESSION_MSG_CONTEXT) != null) {
//			session.removeAttribute(SessionAttType.SESSION_MSG_CONTEXT);
//		}
	}

}
