package gk.common.shine.rpc;

import java.util.concurrent.CompletableFuture;

import gk.common.shine.message.Message;
import gk.common.shine.message.RpcMessage;
import gk.common.shine.rpc.impl.ResponseCallback;

public interface RpcClient {
	
	

    /**
     * 通知服务端<br>
     * 只发送消息 不需要返回
     * 
     * @param message
     */
    void tell(Message message);

    /**
     * 询问服务端<br>
     * 发送消息 返回返回消息的future
     * 
     * @param <T>
     * @param rpcMessage
     * @returns
     */
    <T extends RpcMessage> CompletableFuture<T> ask(RpcMessage rpcMessage, long timeout);

    /**
     * 询问服务端<br>
     * 发送消息 若有回调/超时 则执行callback
     * 
     * @param rpcMessage
     * @param timeout
     * @param callback
     */
    <T extends RpcMessage> void ask(RpcMessage rpcMessage, long timeout, ResponseCallback<T> callback);

}
