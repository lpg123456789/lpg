package gk.common.shine.mina.code.websocket;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * websocket通信数据包
 * 
 * @author zhangzhen
 * 
 */
public class WebSocketCodecPacket {
	private IoBuffer packet;

	public static WebSocketCodecPacket buildPacket(IoBuffer buffer) {
		return new WebSocketCodecPacket(buffer);
	}

	private WebSocketCodecPacket(IoBuffer buffer) {
		packet = buffer;
	}

	public IoBuffer getPacket() {
		return packet;
	}
}
