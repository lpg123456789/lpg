package flower.common.component.event;

public interface GameEventListener {
	
	  /**
     * 处理事件
     * 
     * @param gameEvent
     */
    void handleGameEvent(GameEvent gameEvent);
}
