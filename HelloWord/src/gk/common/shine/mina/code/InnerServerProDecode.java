package gk.common.shine.mina.code;


import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderAdapter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import gk.common.shine.mina.context.ServerContext;
import gk.common.shine.server.structs.SessionAttType;

/**
 * 网关和公共服解码器
 * 
 * @author zhangzhen
 * @date 2017年9月5日
 * @version 1.0
 */
public class InnerServerProDecode extends ProtocolDecoderAdapter {

	@Override
	public void decode(IoSession session, IoBuffer buff,
			ProtocolDecoderOutput out) throws Exception {
		ServerContext context = null;
		if (session.getAttribute(SessionAttType.SESSION_MSG_CONTEXT) != null)
			context = (ServerContext) session
					.getAttribute(SessionAttType.SESSION_MSG_CONTEXT);
		if (context == null) {
			context = new ServerContext();
			session.setAttribute(SessionAttType.SESSION_MSG_CONTEXT, context);
		}
		
		IoBuffer io = context.getBuff();

		io.put(buff);
		while (true) {
			io.flip();
			if (io.remaining() < 4) {
				io.compact();
				break;
			}

			int length = io.getInt();

			if (io.remaining() < length) {
				io.rewind();

				io.compact();
				break;
			}

			byte[] bytes = new byte[length];
			io.get(bytes);

			out.write(bytes);

			if (io.remaining() == 0) {
				io.clear();
				break;
			}
			io.compact();
		}
	}

	@Override
	public void dispose(IoSession session) throws Exception {
		if (session.getAttribute(SessionAttType.SESSION_MSG_CONTEXT) != null)
			session.removeAttribute(SessionAttType.SESSION_MSG_CONTEXT);
	}

}
