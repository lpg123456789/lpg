package gk.common.shine.mina.code;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class InnerServerProCodeFactor implements ProtocolCodecFactory{

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return new InnerServerProEncode();
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return new InnerServerProDecode();
	}

}
