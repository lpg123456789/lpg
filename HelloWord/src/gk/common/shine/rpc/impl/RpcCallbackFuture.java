package gk.common.shine.rpc.impl;

import java.util.concurrent.CompletableFuture;

import gk.common.shine.message.RpcMessage;


/**
 * rpc调用返回
 * 
 * @author hdh
 *
 * @param <T> 返回消息
 */
public class RpcCallbackFuture<T extends RpcMessage> extends AbstractRpcCallback<T> {

    private CompletableFuture<T> future;

    public RpcCallbackFuture() {
    }

    public RpcCallbackFuture(int seq, long expiredTime, CompletableFuture<T> future) {
        this.seq = seq;
        this.expiredTime = expiredTime;
        this.future = future;
    }

    public CompletableFuture<T> getFuture() {
        return future;
    }

    public void setFuture(CompletableFuture<T> future) {
        this.future = future;
    }

    @Override
    public void receiveResponse(T response) {
        future.complete(response);
    }

    @Override
    public void handleException(Exception ex) {
        future.completeExceptionally(ex);
    }

}
