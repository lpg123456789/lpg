package gk.common.shine.mina.code.websocket;

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gk.common.shine.server.structs.SessionAttType;


/**
 * 解码器
 * 
 * @author ad
 */
public class WebSocketDecoder extends CumulativeProtocolDecoder {

    protected final static Logger log = LoggerFactory.getLogger("PLAYERLOGIN");

    protected final static String HEADER_REAL_IP = "X-real-ip";
    protected final static String HEADER_FORWARDED_FOR = "X-Forwarded-For";

    /**
     * 消息内容最大长度
     */
    protected final static int MAX_SIZE = 10240;
    /**
     * 每秒消息最大个数
     */
    protected final static int MAX_COUNT = 100;

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
       return false;
    }

   
}
