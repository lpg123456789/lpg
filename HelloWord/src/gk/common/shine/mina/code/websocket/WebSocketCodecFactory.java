package gk.common.shine.mina.code.websocket;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * 编解码工厂
 * 
 * @author zhangzhen
 *
 */
public class WebSocketCodecFactory implements ProtocolCodecFactory {
    private WebSocketEncoder encoder;
    private WebSocketDecoder decoder;

    public WebSocketCodecFactory() {
        encoder = new WebSocketEncoder();
        decoder = new WebSocketDecoder();
    }

    public WebSocketCodecFactory(WebSocketEncoder encoder, WebSocketDecoder decoder) {
        this.encoder = encoder;
        this.decoder = decoder;
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return decoder;
    }
}
