package flower.common.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import flower.common.communication.Connection;
import flower.common.communication.MessageHandler;
import flower.common.communication.Protocol;
import flower.common.component.event.GameEvent;
import flower.common.component.event.GameEventListener;


public abstract class AbstractEntity<C extends Component> implements Entity<C> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 标签,组件<br>
     * 标签默认为组件类名
     */
    protected final Map<String, C> components = new HashMap<>();
    /**
     * 消息id,处理类
     */
    protected final Map<Integer, MessageHandler<?, ?>> messageHandlers = new HashMap<>();
    /**
     * 事件id,监听器<br>
     * 不保证监听类处理顺序
     */
    protected final ConcurrentMap<String, List<GameEventListener>> listeners = new ConcurrentHashMap<>();

    @Override
    public void handleGameEvent(GameEvent gameEvent) {
        if (gameEvent == null) {
            throw new NullPointerException("handleEvent error.event is null.");
        }
        String eventId = gameEvent.getId();
        List<GameEventListener> list = listeners.get(eventId);
        if (list == null || list.isEmpty()) {
            return;
        }
        long beginTime = System.currentTimeMillis();
        for (GameEventListener listener : list) {
            try {
                listener.handleGameEvent(gameEvent);
            } catch (Exception e) {
                logger.error("[" + listener.getClass().getName() + "] handle event[" + eventId + "] error.", e);
            }
        }
        long costTime = System.currentTimeMillis() - beginTime;
        if (costTime > 100) {
            logger.warn("{}[{}] handle event[{}],costTime={}ms", getClass().getSimpleName(), getId(), eventId, costTime);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Protocol handleMessage(Connection connection, Protocol message) {
        if (message == null) {
            throw new NullPointerException("handleMessage error.msg is null.");
        }
        int msgId = message.getId();
        MessageHandler messageHandler = messageHandlers.get(msgId);
        if (messageHandler == null) {
            logger.warn("receive message[{}],but handler is null.", msgId);
            return null;
        }
        long beginTime = System.currentTimeMillis();
        Protocol response = messageHandler.handleMessage(connection, message);
        long costTime = System.currentTimeMillis() - beginTime;
        if (costTime > 100) {
            logger.warn("{}[{}] handle message[{}],costTime={}ms", getClass().getSimpleName(), getId(), msgId, costTime);
        }
        return response;
    }

    @Override
    public void initialize() {
        for (Entry<String, C> entry : components.entrySet()) {
            C component = entry.getValue();
            try {
                component.initialize();
            } catch (Exception e) {
                logger.error("component[" + entry.getKey() + "] init error.", e);
            }
        }
    }

    @Override
    public void start() {
        for (Entry<String, C> entry : components.entrySet()) {
            C component = entry.getValue();
            try {
                component.start();
            } catch (Exception e) {
                logger.error("component[" + entry.getKey() + "] start error.", e);
            }
        }
    }

    @Override
    public void stop() {
        for (Entry<String, C> entry : components.entrySet()) {
            C component = entry.getValue();
            try {
                component.stop();
            } catch (Exception e) {
                logger.error("component[" + entry.getKey() + "] stop error.", e);
            }
        }
    }

    @Override
    public void destroy() {
        for (Entry<String, C> entry : components.entrySet()) {
            C component = entry.getValue();
            try {
                component.destroy();
            } catch (Exception e) {
                logger.error("component[" + entry.getKey() + "] destroy error.", e);
            }
        }
    }

    @Override
    public void registerComponent(String tag, C component) {
        if (components.containsKey(tag)) {
            throw new IllegalArgumentException("register component error.tag[" + tag + "] is repeated.");
        }
        components.put(tag, component);
    }

    @Override
    public void removeComponent(String tag) {
        components.remove(tag);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends C> T getComponent(String tag) {
        C component = components.get(tag);
        if (component == null) {
            return null;
        }
        return (T) component;
    }

    /**
     * 根据类获取指定的服务<br>
     * 若有多个同类对象 返回的结果可能不确定
     * 
     * @param <T>
     * @param clazz
     * @return
     */
    public <T extends C> T getComponent(Class<T> clazz) {
        String defaultTag = clazz.getSimpleName();
        Component component = components.get(defaultTag);
        if (component != null) {
            return clazz.cast(component);
        }
        for (C tmp : components.values()) {
            Class<?> tmpClazz = tmp.getClass();
            if (tmpClazz.equals(clazz)) {
                return clazz.cast(tmp);
            }
        }
        return null;
    }

    @Override
    public void registerEventListener(String eventId, GameEventListener listener) {
        if (eventId == null) {
            throw new NullPointerException("registerListener error.eventId is null.");
        }
        if (listener == null) {
            throw new NullPointerException("registerListener error.listener is null.");
        }
        List<GameEventListener> list = listeners.get(eventId);
        if (list == null) {
            list = new CopyOnWriteArrayList<>();
            List<GameEventListener> oldList = listeners.putIfAbsent(eventId, list);
            if (oldList != null) {
                list = oldList;
            }
        }
        if (list.contains(listener)) {
            logger.warn("registerListener error.eventId[{}] listener[{}] registerd.", eventId, listener.getClass().getName());
            return;
        }
        list.add(listener);
        logger.debug("registerListener eventId[{}] listener[{}] success.", eventId, listener.getClass().getName());
    }

    @Override
    public void removeEventListener(String eventId, GameEventListener listener) {
        if (eventId == null) {
            throw new NullPointerException("removeListener error.eventId is null.");
        }
        if (listener == null) {
            throw new NullPointerException("removeListener error.listener is null.");
        }
        List<GameEventListener> list = listeners.get(eventId);
        if (list == null) {
            return;
        }
        boolean remove = list.remove(listener);
        if (remove) {
            logger.debug("removeListener eventId[{}] listener[{}] success.", eventId, listener.getClass().getName());
        }
    }

    @Override
    public void registerMessageHandler(int msgId, MessageHandler<?, ?> handler) {
        MessageHandler<?, ?> otherHandler = messageHandlers.putIfAbsent(msgId, handler);
        if (otherHandler != null) {
            logger.warn("msgId[{}] register handler repeated.handler1[{}],handler2[{}]", msgId, otherHandler.getClass().getName(),
                    handler.getClass().getName());
        }
    }

    @Override
    public void removeMessageHandler(int msgId, MessageHandler<?, ?> handler) {
        messageHandlers.remove(msgId);
    }

    public Map<String, C> getComponents() {
        return components;
    }

}
