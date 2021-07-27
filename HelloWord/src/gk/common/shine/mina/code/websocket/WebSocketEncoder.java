package gk.common.shine.mina.code.websocket;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * 编码器
 * 
 * @author zhangzhen
 * 
 */
public class WebSocketEncoder extends ProtocolEncoderAdapter {
	Logger log = Logger.getLogger("PLAYERLOGIN");

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
	}
}
