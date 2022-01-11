package gk.common.shine.rpc.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledFuture;

import org.apache.mina.core.session.IoSession;

import gk.common.shine.inner.InnerClient;
import gk.common.shine.message.Message;
import gk.common.shine.message.RpcMessage;
import gk.common.shine.rpc.RpcCallbackCache;
import gk.common.shine.rpc.RpcClient;
import gk.common.shine.rpc.exception.RpcInvalidConnectException;
import gk.common.shine.server.config.ServerConfig;
import gk.common.shine.utils.WriteUtil;

/**
 * rpc客户端
 * 
 * @author hdh
 *
 */
public abstract class AbstractRpcClient extends InnerClient implements RpcClient {

    protected final RpcCallbackCache callbackCache = new RpcCallbackCache();

    protected ScheduledFuture<?> callbackTimerTask;

    public AbstractRpcClient(ServerConfig remoteConfig) {
        super(remoteConfig);
    }

    @Override
    public void startup() {
        super.startup();
        startCallbackTimerTask();
    }

    /**
     * 启动回调过期检查定时器
     */
    protected abstract void startCallbackTimerTask();

    @Override
    protected void receiveMessage(IoSession session, Message msg) {
        if (msg instanceof RpcMessage) {
            // rpc返回消息
            RpcMessage rpcMsg = (RpcMessage) msg;
            handleRpcMessage(rpcMsg);
            return;
        }
        super.receiveMessage(session, msg);
    }

    /**
     * 接受到rpc消息并处理
     * 
     * @param rpcMsg
     */
    protected void handleRpcMessage(RpcMessage rpcMsg) {
        callbackCache.receiveResponse(rpcMsg);
    }

    @Override
    public void tell(Message message) {
        messageSent(message);
    }

    @Override
    public <T extends RpcMessage> CompletableFuture<T> ask(RpcMessage rpcMessage, long timeout) {
        CompletableFuture<T> future = new CompletableFuture<>();
        if (!isSessionValid()) {
            future.completeExceptionally(RpcInvalidConnectException.getInstance());
            return future;
        }
        int seq = callbackCache.generateSeq();
        rpcMessage.setSeq(seq);
        long now = System.currentTimeMillis();
        long expiredTime = now + timeout;
        RpcCallbackFuture<T> futureCallback = new RpcCallbackFuture<>(seq, expiredTime, future);
        callbackCache.addCallback(futureCallback);
        try {
            WriteUtil.writeMsgAndFlush(session, rpcMessage);
        } catch (Exception e) {
            logger.error("rpc ask[" + rpcMessage.getId() + "] error.", e);
            try {
                callbackCache.handleException(seq, e);
            } catch (Exception e2) {
                logger.error("rpc ask[" + rpcMessage.getId() + "] handleException error.", e2);
            }
        }
        return future;
    }

    @Override
    public <T extends RpcMessage> void ask(RpcMessage rpcMessage, long timeout, ResponseCallback<T> callback) {
        if (callback == null) {
            tell(rpcMessage);
            return;
        }
        if (!isSessionValid()) {
            callback.handleException(RpcInvalidConnectException.getInstance());
            return;
        }
        int seq = callbackCache.generateSeq();
        rpcMessage.setSeq(seq);
        long now = System.currentTimeMillis();
        long expiredTime = now + timeout;
        RpcCallbackHandler<T> handler = new RpcCallbackHandler<>(seq, expiredTime, callback);
        callbackCache.addCallback(handler);
        try {
            WriteUtil.writeMsgAndFlush(session, rpcMessage);
        } catch (Exception e) {
            logger.error("rpc ask[" + rpcMessage.getId() + "] error.", e);
            try {
                callbackCache.handleException(seq, e);
            } catch (Exception e2) {
                logger.error("rpc ask[" + rpcMessage.getId() + "] handleException error.", e2);
            }
        }

    }

}
