package gk.server.shine.persistence;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 
 * @author hdh
 *
 * @param <T>
 */
public interface Saver<T> {

    long DEFAULT_TICK_INTERVAL = TimeUnit.SECONDS.toMillis(30);

    void startup();

    void shutdown();

    /**
     * 异步保存数据
     * 
     * @param bean
     */
    void asyncSave(T bean);

    /**
     * 阻塞保存数据
     * 
     * @param bean
     * @return
     */
    boolean blockSave(T bean);

    /**
     * 保存数据
     * 
     * @param bean
     * @param block 是否阻塞保存
     * @return
     */
    boolean save(T bean, boolean block);

    /**
     * 批量保存数据
     * 
     * @param beans
     * @param block 是否阻塞保存
     */
    void batchSave(Collection<T> beans, boolean block);
}
