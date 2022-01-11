package flower.com.game.context;

import java.util.List;

import flower.common.component.event.GameEvent;
import flower.common.component.event.GameEventListener;
import flower.common.component.service.Context;

public class GameContext extends Context {

	private final static GameContext instance = new GameContext();

	public static GameContext getInstance() {
		return instance;
	}
	
	
	 /**
     * 玩家相关的事件 将扔到玩家线程池中处理
     */
    @Override
    public void handleGameEvent(final GameEvent gameEvent) {
        if (gameEvent == null) {
            throw new NullPointerException("handleEvent error.event is null.");
        }
        String eventId = gameEvent.getId();
        List<GameEventListener> list = listeners.get(eventId);
        if (list == null || list.isEmpty()) {
            return;
        }
        for (GameEventListener listener : list) {
            try {
                handleGameEvent(listener, gameEvent);
            } catch (Exception e) {
                logger.error("[" + listener.getClass().getName() + "] handle event[" + eventId + "] error.", e);
            }
        }
    }

    private void handleGameEvent(GameEventListener listener, final GameEvent gameEvent) {
      
    }
	
	
	
}
