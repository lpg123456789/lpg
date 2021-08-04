package gk.common.shine.rpc.impl;

import gk.common.shine.message.RpcMessage;

/**
 * rpc返回消息的回调
 * 
 * @author hdh
 *
 * @param <T>
 */
public interface ResponseCallback<T extends RpcMessage> {

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

}
