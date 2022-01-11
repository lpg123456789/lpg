package gk.common.shine.rpc;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gk.common.shine.message.RpcMessage;
import gk.common.shine.rpc.exception.RpcTimeoutException;

/**
 * rpc回调缓存
 * 
 * @author hdh
 *
 */
public class RpcCallbackCache {

    private final static Logger logger = LoggerFactory.getLogger(RpcCallbackCache.class);
    /**
     * 回调过期检查间隔
     */
    public final static long CALLBACK_EXPIRED_CHECK_INTERVAL = 100;

    private final ConcurrentMap<Integer, RpcCallback<?>> callbackMap = new ConcurrentHashMap<>();

    private final AtomicInteger seqGenerator = new AtomicInteger();

    public int generateSeq() {
        return seqGenerator.incrementAndGet();
    }

    public void addCallback(RpcCallback<?> callback) {
        callbackMap.put(callback.getSeq(), callback);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void receiveResponse(RpcMessage response) {
        int seq = response.getSeq();
        RpcCallback callback = callbackMap.remove(seq);
        if (callback == null) {
            logger.warn("receive response[{}],but callback is expired.", response.getId());
            return;
        }
        try {
            callback.receiveResponse(response);
        } catch (Exception e) {
            logger.error("callback receiveResponse[" + response.getId() + "] error.", e);
        }
    }

    public void handleException(int seq, Exception ex) {
        RpcCallback<?> callback = callbackMap.remove(seq);
        if (callback == null) {
            return;
        }
        try {
            callback.handleException(ex);
        } catch (Exception e) {
            logger.error("callback handleException error.", e);
        }

    }

    /**
     * 检查并清理过期回调
     * 
     * @param now
     */
    public void checkExpired(long now) {
        if (callbackMap.isEmpty()) {
            return;
        }
        Iterator<Entry<Integer, RpcCallback<?>>> iterator = callbackMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<Integer, RpcCallback<?>> entry = iterator.next();
            RpcCallback<?> callback = entry.getValue();
            if (!callback.isTimeout(now)) {
                continue;
            }
            try {
                callback.handleException(RpcTimeoutException.getInstance());
            } catch (Exception e) {
                logger.error("callback handle timeout error.", e);
            }
            iterator.remove();
        }
    }

    public RpcCallback<?> getCallback(int seq) {
        return callbackMap.get(seq);
    }

    public ConcurrentMap<Integer, RpcCallback<?>> getCallbackMap() {
        return callbackMap;
    }

    public AtomicInteger getSeqGenerator() {
        return seqGenerator;
    }

}
