package gk.server.shine.server;

/**
 * 服务器状态
 * @author zhangzhen
 * @date 2017年8月15日
 * @version 1.0
 */
public enum ServerState {

	/**
	 * 停止中
	 */
	STOPDING,
	/**
	 * 运行中
	 */
	RUN,
	/**
	 * 加载中
	 */
	LOADING,
    /**
     * 已停止
     */
    STOPPED,
}
