package flower.common.communication;

import java.io.IOException;

import io.netty.buffer.ByteBuf;

/**
 * 协议<br>
 * 可编解码
 * 
 * @author hdh
 *
 */
public interface Protocol {

    /**
     * 协议id
     * 
     * @return
     */
    int getId();

    /**
     * 序号
     * 
     * @return
     */
    int getSeq();

    /**
     * 
     * @param seq
     */
    void setSeq(int seq);

    /**
     * 编码
     * 
     * @param buffer
     */
    void encode(ByteBuf buffer);

    /**
     * 解码
     * 
     * @param buffer
     * @throws IOException
     */
    void decode(ByteBuf buffer) throws IOException;

}
