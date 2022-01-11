package gk.common.shine.rpc.exception;

/**
 * rpc调用超时
 * 
 * @author hdh
 *
 */
public class RpcTimeoutException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -7759639460774171111L;

    private static final RpcTimeoutException instance = new RpcTimeoutException();

    public final static RpcTimeoutException getInstance() {
        return instance;
    }

}
