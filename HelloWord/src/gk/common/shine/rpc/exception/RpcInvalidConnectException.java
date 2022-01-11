package gk.common.shine.rpc.exception;

/**
 * rpc链接失效
 * 
 * @author hdh
 *
 */
public class RpcInvalidConnectException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = -3415977315399507706L;

    private static final RpcInvalidConnectException instance = new RpcInvalidConnectException();

    public final static RpcInvalidConnectException getInstance() {
        return instance;
    }

}
