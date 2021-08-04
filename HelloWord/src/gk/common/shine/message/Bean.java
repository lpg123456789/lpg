package gk.common.shine.message;

import org.apache.commons.io.Charsets;
import org.apache.mina.core.buffer.IoBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用来处理数据写入 读取
 * 
 * @author zhangzhen
 * @date 2017年8月14日
 * @version 1.0
 */
public abstract class Bean {
    protected static final Logger LOGGER = LoggerFactory.getLogger(Bean.class);

    public abstract boolean write(IoBuffer buffer);

    public abstract boolean read(IoBuffer buffer);

    /**
     * int 写入
     * 
     * @param buf
     * @param value
     */
    protected void writeInt(IoBuffer buf, int value) {
        buf.putInt(value);
    }

    /**
     * String写入
     * 
     * @param buf
     * @param value
     */
    protected void writeString(IoBuffer buf, String value) {
        if (value == null) {
            buf.putInt(0);
            return;
        }
        byte[] bytes = value.getBytes(Charsets.UTF_8);
        buf.putInt(bytes.length);
        buf.put(bytes);
    }

    /**
     * long写入
     * 
     * @param buf
     * @param value
     */
    protected void writeLong(IoBuffer buf, long value) {
        buf.putLong(value);
    }

    /**
     * bean写入
     * 
     * @param buf
     * @param value
     */
    protected void writeBean(IoBuffer buf, Bean value) {
        value.write(buf);
    }

    /**
     * bean写入(包括可能为Null的bean)
     *
     * @param buf
     * @param value
     */
    protected void writeBeanIncludeNull(IoBuffer buf, Bean value) {
        if(value==null){
            buf.put((byte) 0);
        }else{
            buf.put((byte) 1);
            value.write(buf);
        }
    }

    /**
     * short写入
     * 
     * @param buf
     * @param value
     */
    protected void writeShort(IoBuffer buf, int value) {
        buf.putShort((short) value);
    }

    /**
     * short写入
     * 
     * @param buf
     * @param value
     */
    protected void writeShort(IoBuffer buf, short value) {
        buf.putShort(value);
    }

    /**
     * byte写入
     * 
     * @param buf
     * @param value
     */
    protected void writeByte(IoBuffer buf, byte value) {
        buf.put(value);
    }

    /**
     * byte写入
     * 
     * @param buf
     * @param value
     */
    protected void writeBytes(IoBuffer buf, byte[] value) {
        if (value == null) {
            buf.putInt(0);
            return;
        }

        buf.putInt(value.length);
        buf.put(value);
    }

    /**
     * 读取int
     * 
     * @param buf
     * @return
     */
    protected int readInt(IoBuffer buf) {
        return buf.getInt();
    }

    /**
     * 读取String
     * 
     * @param buf
     * @return
     */
    protected String readString(IoBuffer buf) {
        int length = buf.getInt();
        if (length <= 0)
            return null;
        if (buf.remaining() < length)
            return null;
        byte[] bytes = new byte[length];
        buf.get(bytes);
        return new String(bytes, Charsets.UTF_8);
    }

    /**
     * 读取long
     * 
     * @param buf
     * @return
     */
    protected long readLong(IoBuffer buf) {
        return buf.getLong();
    }

    /**
     * 读取bean
     * 
     * @param buf
     * @param clazz
     * @return
     */
    protected <T extends Bean> T readBean(IoBuffer buf, Class<T> clazz) {
        try {
            T bean = (T) clazz.newInstance();
            bean.read(buf);
            return bean;
        } catch (IllegalAccessException e) {
            LOGGER.error("Decode Bean Error:" + e.getMessage());
        } catch (InstantiationException e) {
            LOGGER.error("Decode Bean Error:" + e.getMessage());
        }
        return null;
    }

    protected <T extends Bean> T readBeanIncludeNull(IoBuffer buf, Class<T> clazz) {
        byte flag=readByte(buf);
        if(flag==0){
            return null;
        }
        try {
            T bean = (T) clazz.newInstance();
            bean.read(buf);
            return bean;
        } catch (IllegalAccessException e) {
            LOGGER.error("Decode Bean Error:" + e.getMessage());
        } catch (InstantiationException e) {
            LOGGER.error("Decode Bean Error:" + e.getMessage());
        }
        return null;
    }

    /**
     * 读取short
     * 
     * @param buf
     * @return
     */
    protected short readShort(IoBuffer buf) {
        return buf.getShort();
    }

    /**
     * 读取byte
     * 
     * @param buf
     * @return
     */
    protected byte readByte(IoBuffer buf) {
        return buf.get();
    }

    /**
     * 读取byte数组
     * 
     * @param buf
     * @return
     */
    protected byte[] readBytes(IoBuffer buf) {
        int length = buf.getInt();
        if (length == 0)
            return new byte[0];
        byte[] bytes = new byte[length];
        buf.get(bytes);
        return bytes;
    }
}
