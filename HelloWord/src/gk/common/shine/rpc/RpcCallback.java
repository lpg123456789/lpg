package gk.common.shine.rpc;

import gk.common.shine.message.RpcMessage;

/**
 * rpc回调
 * 
 * @author hdh
 *
 * @param <T>
 */
public interface RpcCallback<T extends RpcMessage> {
    /**
     * 消息序号
     * 
     * @return
     */
    int getSeq();

    /**
     * 接受到回调消息
     * 
     * @param response
     */
    void receiveResponse(T response);

    /**
     * 处理错误
     * 
     * @param ex
     */
    void handleException(Exception ex);

    /**
     * 是否超时
     * 
     * @param now
     * @return
     */
    boolean isTimeout(long now);

}
