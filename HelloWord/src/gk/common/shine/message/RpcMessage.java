package gk.common.shine.message;

import org.apache.mina.core.buffer.IoBuffer;

public abstract class RpcMessage extends Message{

	protected int seq;

    @Override
    public boolean write(IoBuffer buffer) {
        buffer.putInt(seq);
        writeImpl(buffer);
        return true;
    }

    @Override
    public boolean read(IoBuffer buffer) {
        seq = buffer.getInt();
        readImpl(buffer);
        return true;
    }

    protected abstract void writeImpl(IoBuffer buffer);

    protected abstract void readImpl(IoBuffer buffer);

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

}
