package flower.common.component.service;

/**
 * 上下文注册类<br>
 * 早于service.initialize执行<br>
 * 
 * 
 * {@link Context}
 * 
 * @author hdh
 *
 */
public interface ContextRegister {

    void registerAll() throws Exception;

}
