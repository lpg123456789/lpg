package gk.common.shine.mina.code;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * 客户端消息编码
 * @author zhangzhen
 * @date 2017年9月5日
 * @version 1.0
 */
public class ServerProEncode extends ProtocolEncoderAdapter{

	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		
	}

}
