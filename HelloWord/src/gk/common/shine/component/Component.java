package gk.common.shine.component;

public interface Component {
	 /**
     * 初始化<br>
     * 执行不需要依赖其他模块的代码
     * 
     * @throws Exception
     */
    void init() throws Exception;

    /**
     * 准备
     * 
     * @throws Exception
     */
    void ready() throws Exception;

    /**
     * 暂停
     */
    void suspend();

    /**
     * 销毁
     */
    void destroy();
}
