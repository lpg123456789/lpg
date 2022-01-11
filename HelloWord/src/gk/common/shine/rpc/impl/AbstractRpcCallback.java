package gk.common.shine.rpc.impl;

import gk.common.shine.message.RpcMessage;
import gk.common.shine.rpc.RpcCallback;

public abstract class AbstractRpcCallback<T extends RpcMessage> implements RpcCallback<T> {

    protected int seq;

    protected long expiredTime;

    @Override
    public int getSeq() {
        return seq;
    }

    @Override
    public boolean isTimeout(long now) {
        return now > expiredTime;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

}
