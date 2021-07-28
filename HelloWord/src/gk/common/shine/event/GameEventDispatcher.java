package gk.common.shine.event;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;

/**
 * 游戏事件分发器<br>
 * 将事件分发到注册监听该事件的监听类中处理
 * 
 * @author hdh
 *
 */
public class GameEventDispatcher implements GameEventListener {

	protected final Logger logger = Logger.getLogger(getClass());
	/**
	 * 消息id,正在监听该消息的处理类列表<br>
	 * 不保证监听类处理顺序
	 */
	protected final ConcurrentMap<String, List<GameEventListener>> listeners = new ConcurrentHashMap<>();

	/**
	 * 注册事件监听器
	 * 
	 * @param eventId
	 * @param listener
	 */
	public void registerListener(String eventId, GameEventListener listener) {
		String listenerName = (listener == null ? "null" : listener.getClass().getSimpleName());
		if (eventId == null || listener == null) {
			logger.error("registerListener error.eventId[" + eventId + "] listener[" + listenerName + "].");
			return;
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
			logger.warn("registerListener error.eventId[" + eventId + "] listener[" + listenerName + "] registerd.");
			return;
		}
		list.add(listener);
		logger.debug("registerListener eventId[" + eventId + "] listener[" + listenerName + "] success.");
	}

	/**
	 * 移除事件监听
	 * 
	 * @param eventId
	 * @param listener
	 */
	public void removeListener(String eventId, GameEventListener listener) {
		String listenerName = (listener == null ? "null" : listener.getClass().getSimpleName());
		if (eventId == null || listener == null) {
			logger.error("removeListener error.eventId[" + eventId + "] listener[" + listenerName + "].");
			return;
		}
		List<GameEventListener> list = listeners.get(eventId);
		if (list == null) {
			return;
		}
		boolean remove = list.remove(listener);
		if (remove) {
			logger.debug("removeListener eventId[" + eventId + "] listener[" + listenerName + "] success.");
		}
	}

	/**
	 * 接收事件 并阻塞处理
	 * 
	 * @param gameEvent
	 */
	public void receiveGameEvent(GameEvent gameEvent) {
		// 直接处理
		handleGameEvent(gameEvent);
	}

	@Override
	public void handleGameEvent(GameEvent gameEvent) {
		if (gameEvent == null) {
			return;
		}
		String eventId = gameEvent.getId();
		List<GameEventListener> list = listeners.get(eventId);
		if (list == null || list.isEmpty()) {
			return;
		}
		for (GameEventListener listener : list) {
			try {
				listener.handleGameEvent(gameEvent);
			} catch (Exception e) {
				logger.error("[" + listener.getClass().getName() + "] handle event[" + eventId + "] error.", e);
			}
		}
	}

	public ConcurrentMap<String, List<GameEventListener>> getListeners() {
		return listeners;
	}

}
