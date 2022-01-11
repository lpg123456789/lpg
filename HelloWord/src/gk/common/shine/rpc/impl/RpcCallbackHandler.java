package gk.common.shine.rpc.impl;

import gk.common.shine.message.RpcMessage;

public class RpcCallbackHandler<T extends RpcMessage> extends AbstractRpcCallback<T> {

    private ResponseCallback<T> callback;

    private volatile boolean complete;

    public RpcCallbackHandler() {
    }

    public RpcCallbackHandler(int seq, long expiredTime, ResponseCallback<T> callback) {
        this.seq = seq;
        this.expiredTime = expiredTime;
        this.callback = callback;
    }

    @Override
    public synchronized void receiveResponse(T response) {
        if (complete) {
            return;
        }
        complete = true;
        callback.receiveResponse(response);
    }

    @Override
    public synchronized void handleException(Exception ex) {
        if (complete) {
            return;
        }
        complete = true;
        callback.handleException(ex);
    }

    public ResponseCallback<T> getCallback() {
        return callback;
    }

    public void setCallback(ResponseCallback<T> callback) {
        this.callback = callback;
    }

}
