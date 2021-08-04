package gk.common.shine.utils;


import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import gk.common.shine.message.Message;
import gk.common.shine.server.structs.SessionAttType;


public class WriteUtil {

	public static Logger log = Logger.getLogger(WriteUtil.class);

	/**
	 * 最大等待发送字节
	 */
	private static int MAX_SCHEDULED_WRITE_BYTE = 4 * 1024 * 1024;

	public static ConcurrentHashMap<Integer, Long> packages = new ConcurrentHashMap<Integer, Long>();

	public static ConcurrentHashMap<Integer, Integer> packagenums = new ConcurrentHashMap<Integer, Integer>();

	public static ConcurrentHashMap<Integer, Integer> packagemax = new ConcurrentHashMap<Integer, Integer>();

	public static ConcurrentHashMap<Integer, Integer> packagemin = new ConcurrentHashMap<Integer, Integer>();

	/**
	 * 发送消息到公共服
	 * 
	 * @param session
	 * @param message
	 */
	public static void writeInnerPublic(IoSession session, Message message) {
		try {
			IoBuffer ioBuffer = IoBuffer.allocate(100);
			int length = 0;
			ioBuffer.setAutoExpand(true);
			ioBuffer.setAutoShrink(true);
			ioBuffer.putInt(message.getId());
			message.write(ioBuffer);
			ioBuffer.flip();
			length = ioBuffer.limit();
			IoBuffer buf = IoBuffer.allocate(length + 4);
			buf.putInt(length);

			buf.put(ioBuffer);
			buf.flip();
			synchronized (session) {
				session.write(buf);
			}
		} catch (Exception e) {
			log.error("发送消息到公共服异常", e);
		}
	}

	/**
	 * 发送消息到网关
	 * 
	 * @param session
	 * @param message
	 */
	public static void writeInnerGate(IoSession session, Message message) {
		try {
			IoBuffer ioBuffer = IoBuffer.allocate(100);
			int length = 0;
			ioBuffer.setAutoExpand(true);
			ioBuffer.setAutoShrink(true);
			ioBuffer.putInt(message.getId());
			message.write(ioBuffer);
			ioBuffer.flip();
			length = ioBuffer.limit() + 8;
			IoBuffer buf = IoBuffer.allocate(length);
			buf.putInt(length);
			if (!session.containsAttribute(SessionAttType.SESSION_MSG_GATE_CODE)) {
				session.setAttribute(SessionAttType.SESSION_MSG_GATE_CODE, 1);
			}
			int code = Integer.parseInt(session.getAttribute(SessionAttType.SESSION_MSG_GATE_CODE).toString());
			int tempCode = code;
			code ^= 512;
			code ^= length;
			buf.putInt(code);

			buf.put(ioBuffer);
			buf.flip();
			session.write(buf);
			session.setAttribute(SessionAttType.SESSION_MSG_GATE_CODE, ++tempCode);
		} catch (Exception e) {
			log.error("发送消息到网关异常", e);
		}
	}

	/**
	 * 发送消息到工具
	 * 
	 * @param session
	 * @param message
	 */
	public static void writeInnerTools(IoSession session, Message message) {
		try {
			if (session.getScheduledWriteBytes() > MAX_SCHEDULED_WRITE_BYTE) {
				SessionUtil.closeSession(session, "等待发送字节过多(" + session.getScheduledWriteBytes() + ")");
				return;
			}

			IoBuffer buf = IoBuffer.allocate(100);
			buf.setAutoExpand(true);
			buf.setAutoShrink(true);

			buf.putInt(0);
			buf.putInt(message.getId());

			boolean zlib = false;
			IoBuffer zipbuf = IoBuffer.allocate(100);
			zipbuf.setAutoExpand(true);
			zipbuf.setAutoShrink(true);
			message.write(buf);
			zipbuf.flip();
			if (zipbuf.remaining() > 512) {
				byte[] bytes = ZLibUtils.compress(zipbuf.array());
				buf.put(bytes);
				zlib = true;
			} else {
				buf.put(zipbuf);
			}
			buf.flip();
			int length = buf.limit() - 4;
			if (zlib) {
				length = 0x1000000 | length;
			}
			buf.putInt(length);
			buf.rewind();

			IoBuffer sendbuf = null;
			synchronized (session) {
				if (session.containsAttribute(SessionAttType.SESSION_MSG_BUFFER)) {
					sendbuf = (IoBuffer) session.getAttribute(SessionAttType.SESSION_MSG_BUFFER);
				} else {
					sendbuf = IoBuffer.allocate(1024);
					sendbuf.setAutoExpand(true);
					sendbuf.setAutoShrink(true);
					session.setAttribute(SessionAttType.SESSION_MSG_BUFFER, sendbuf);
				}

				sendbuf.put(buf);
			}
		} catch (Exception e) {
			log.error("发送消息到工具异常", e);
		}
	}

	/**
	 * 发送消息到客户端
	 * 
	 * @param session
	 * @param message
	 */
	public static void writeData(IoSession session, int msgId, byte[] data) {
		try {
			if (session.getScheduledWriteBytes() > MAX_SCHEDULED_WRITE_BYTE) {
				SessionUtil.closeSession(session, "等待发送字节过多(" + session.getScheduledWriteBytes() + ")");
				return;
			}

			IoBuffer buf = IoBuffer.allocate(100);
			// 自动扩展容量
			buf.setAutoExpand(true);
			// 自动收缩容量
			buf.setAutoShrink(true);

			buf.putInt(0);
			buf.putInt(msgId);
			buf.put(data);
			buf.flip();
			int length = buf.limit() - 4;

			buf.putInt(length);
			buf.rewind();

			// 消息日志统计
			if (buf.remaining() > 0) {
				if (packages.containsKey(Integer.valueOf(msgId))) {
					packages.put(Integer.valueOf(msgId), Long.valueOf(((Long) packages.get(Integer.valueOf(msgId))).longValue() + buf.remaining()));
					packagenums.put(Integer.valueOf(msgId), Integer.valueOf(((Integer) packagenums.get(Integer.valueOf(msgId))).intValue() + 1));
					int max = ((Integer) packagemax.get(Integer.valueOf(msgId))).intValue();
					if (max < buf.remaining()) {
						packagemax.put(Integer.valueOf(msgId), Integer.valueOf(buf.remaining()));
					}
					int min = ((Integer) packagemin.get(Integer.valueOf(msgId))).intValue();
					if (min > buf.remaining())
						packagemin.put(Integer.valueOf(msgId), Integer.valueOf(buf.remaining()));
				} else {
					packages.put(Integer.valueOf(msgId), Long.valueOf(buf.remaining()));
					packagenums.put(Integer.valueOf(msgId), Integer.valueOf(1));
					packagemax.put(Integer.valueOf(msgId), Integer.valueOf(buf.remaining()));
					packagemin.put(Integer.valueOf(msgId), Integer.valueOf(buf.remaining()));
				}
			}

			IoBuffer sendbuf = null;
			synchronized (session) {
				if (session.containsAttribute(SessionAttType.SESSION_MSG_BUFFER)) {
					sendbuf = (IoBuffer) session.getAttribute(SessionAttType.SESSION_MSG_BUFFER);
				} else {
					sendbuf = IoBuffer.allocate(1024);
					sendbuf.setAutoExpand(true);
					sendbuf.setAutoShrink(true);
					session.setAttribute(SessionAttType.SESSION_MSG_BUFFER, sendbuf);
				}

				sendbuf.put(buf);
			}
		} catch (Exception e) {
			log.error("发送消息到客户端异常", e);
		}
	}

	/**
	 * 群发消息到客户端
	 * 
	 * @param sessions
	 * @param message
	 */
	public static void writeData(List<IoSession> sessions, int msgId, byte[] data) {
		try {

			IoBuffer buf = IoBuffer.allocate(100);
			// 自动扩展容量
			buf.setAutoExpand(true);
			// 自动收缩容量
			buf.setAutoShrink(true);

			buf.putInt(0);
			buf.putInt(msgId);
			buf.put(data);
			buf.flip();
			int length = buf.limit() - 4;

			buf.putInt(length);
			buf.rewind();

			// 消息日志统计
			if (buf.remaining() > 0) {
				if (packages.containsKey(Integer.valueOf(msgId))) {
					packages.put(Integer.valueOf(msgId), Long.valueOf(((Long) packages.get(Integer.valueOf(msgId))).longValue() + buf.remaining()));
					packagenums.put(Integer.valueOf(msgId), Integer.valueOf(((Integer) packagenums.get(Integer.valueOf(msgId))).intValue() + 1));
					int max = ((Integer) packagemax.get(Integer.valueOf(msgId))).intValue();
					if (max < buf.remaining()) {
						packagemax.put(Integer.valueOf(msgId), Integer.valueOf(buf.remaining()));
					}
					int min = ((Integer) packagemin.get(Integer.valueOf(msgId))).intValue();
					if (min > buf.remaining())
						packagemin.put(Integer.valueOf(msgId), Integer.valueOf(buf.remaining()));
				} else {
					packages.put(Integer.valueOf(msgId), Long.valueOf(buf.remaining()));
					packagenums.put(Integer.valueOf(msgId), Integer.valueOf(1));
					packagemax.put(Integer.valueOf(msgId), Integer.valueOf(buf.remaining()));
					packagemin.put(Integer.valueOf(msgId), Integer.valueOf(buf.remaining()));
				}
			}
			for (IoSession session : sessions) {
				if (session.getScheduledWriteBytes() > MAX_SCHEDULED_WRITE_BYTE) {
					SessionUtil.closeSession(session, "等待发送字节过多(" + session.getScheduledWriteBytes() + ")");
					continue;
				}
				IoBuffer sendbuf = null;
				synchronized (session) {
					if (session.containsAttribute(SessionAttType.SESSION_MSG_BUFFER)) {
						sendbuf = (IoBuffer) session.getAttribute(SessionAttType.SESSION_MSG_BUFFER);
					} else {
						sendbuf = IoBuffer.allocate(1024);
						sendbuf.setAutoExpand(true);
						sendbuf.setAutoShrink(true);
						session.setAttribute(SessionAttType.SESSION_MSG_BUFFER, sendbuf);
					}

					sendbuf.put(buf);
				}
				buf.flip();
			}
		} catch (Exception e) {
			log.error("群发发送消息到客户端异常", e);
		}
	}

	/**
	 * tcp内网发送msg
	 * 
	 * @param session
	 * @param message
	 */
	public static void writeMsg(IoSession session, Message message) {
		if (session.getScheduledWriteBytes() > MAX_SCHEDULED_WRITE_BYTE) {
			SessionUtil.closeSession(session, "等待发送字节过多(" + session.getScheduledWriteBytes() + ")");
			return;
		}

		IoBuffer buf = IoBuffer.allocate(100);
		// 自动扩展容量
		buf.setAutoExpand(true);
		// 自动收缩容量
		buf.setAutoShrink(true);

		buf.putInt(0);
		buf.putInt(message.getId());

		message.write(buf);
		buf.flip();
		int length = buf.limit() - 4;

		buf.putInt(length);
		buf.rewind();

		IoBuffer sendbuf = null;
		synchronized (session) {
			if (session.containsAttribute(SessionAttType.SESSION_MSG_BUFFER)) {
				sendbuf = (IoBuffer) session.getAttribute(SessionAttType.SESSION_MSG_BUFFER);
			} else {
				sendbuf = IoBuffer.allocate(1024);
				sendbuf.setAutoExpand(true);
				sendbuf.setAutoShrink(true);
				session.setAttribute(SessionAttType.SESSION_MSG_BUFFER, sendbuf);
			}
			sendbuf.put(buf);
		}
	}

	/**
	 * tcp内网发送msg 直接发送
	 * 
	 * @param session
	 * @param message
	 */
	public static void writeMsgAndFlush(IoSession session, Message message) {
		if (session.getScheduledWriteBytes() > MAX_SCHEDULED_WRITE_BYTE) {
			SessionUtil.closeSession(session, "等待发送字节过多(" + session.getScheduledWriteBytes() + ")");
			return;
		}

		IoBuffer buf = IoBuffer.allocate(100);
		// 自动扩展容量
		buf.setAutoExpand(true);
		// 自动收缩容量
		buf.setAutoShrink(true);

		buf.putInt(0);
		buf.putInt(message.getId());

		message.write(buf);
		buf.flip();
		int length = buf.limit() - 4;

		buf.putInt(length);
		buf.rewind();
		session.write(buf);
	}

	/**
	 * tcp内网发送msg 直接发送
	 * @param session
	 */
	public static void writeMsgAndFlush(IoSession session, int msgId,byte[] data) {
		if (session.getScheduledWriteBytes() > MAX_SCHEDULED_WRITE_BYTE) {
			SessionUtil.closeSession(session, "等待发送字节过多(" + session.getScheduledWriteBytes() + ")");
			return;
		}

		IoBuffer buf = IoBuffer.allocate(100);
		// 自动扩展容量
		buf.setAutoExpand(true);
		// 自动收缩容量
		buf.setAutoShrink(true);

		buf.putInt(0);
		buf.putInt(msgId);
        buf.put(data);
		buf.flip();
		int length = buf.limit() - 4;

		buf.putInt(length);
		buf.rewind();
		session.write(buf);
	}
}