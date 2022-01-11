package flower.common.component.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import flower.common.communication.Connection;
import flower.common.communication.Protocol;
import flower.common.component.event.GameEvent;

/**
 * 服务
 * 
 * @author hdh
 *
 */
public abstract class AbstractService implements Service {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected volatile ServiceStatus _status = ServiceStatus.ORIGINAL;

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public ServiceStatus getStatus() {
        return _status;
    }

    /**
     * 初始化<br>
     * 可执行不依赖其他模块的逻辑<br>
     * 所有模块的init执行结束才会执行start
     * 
     */
    @Override
    public final void initialize() {
        String serviceName = getName();
        _status = ServiceStatus.INITIALIZING;
        logger.debug("{} initializing", serviceName);
        try {
            onInitialize();
        } catch (Exception e) {
            logger.error(serviceName + " initialize error.", e);
            //throw new ServerStarupError(e);
        }
        _status = ServiceStatus.INITIALIZED;
        logger.debug("{} initialized", serviceName);
    }

    /**
     * 启动服务 <br>
     * 可执行依赖其他模块init的逻辑<br>
     * 所有模块的init执行结束才会执行start
     * 
     */
    @Override
    public final void start() {
        String serviceName = getName();
        _status = ServiceStatus.STARTING;
        logger.debug("{} starting", serviceName);
        try {
            onStart();
        } catch (Exception e) {
            logger.error(serviceName + " start error.", e);
            //throw new ServerStarupError(e);
        }
        _status = ServiceStatus.STARTED;
        logger.debug("{} started", serviceName);
    }

    /**
     * 暂停<br>
     * 游戏服停服时 先执行的逻辑<br>
     * 所有模块的stop执行结束才会执行destroy
     */
    @Override
    public final void stop() {
        String serviceName = getName();
        _status = ServiceStatus.STOPPING;
        logger.debug("{} stopping", serviceName);
        try {
            onStop();
        } catch (Exception e) {
            logger.error(serviceName + " stop error.", e);
        }
        _status = ServiceStatus.STOPPED;
        logger.debug("{} stopped", serviceName);
    }

    /**
     * 销毁<br>
     * 游戏服停服时 后执行的逻辑<br>
     * 所有模块的stop执行结束才会执行destroy
     */
    @Override
    public final void destroy() {
        String serviceName = getName();
        _status = ServiceStatus.DESTROYING;
        logger.debug("{} destroying", serviceName);
        try {
            onDestroy();
        } catch (Exception e) {
            logger.error(serviceName + " destroy error.", e);
        }
        _status = ServiceStatus.DESTROYED;
        logger.debug("{} destroyed", serviceName);
    }

    /**
     * 初始化
     * 
     * @throws Exception
     */
    protected void onInitialize() throws Exception {
    }

    /**
     * 启动
     * 
     * @throws Exception
     */
    protected void onStart() throws Exception {
    }

    /**
     * 停止
     */
    protected void onStop() {
    }

    /**
     * 销毁
     */
    protected void onDestroy() {
    }

    @Override
    public void handleGameEvent(GameEvent gameEvent) {
    }

    @Override
    public Protocol handleMessage(Connection connection, Protocol message) {
        return message;
    }

}
