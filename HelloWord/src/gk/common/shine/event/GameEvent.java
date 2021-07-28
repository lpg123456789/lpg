package gk.common.shine.event;

/**
 * 游戏事件<br>
 * 消息应当是不可变的<br>
 * 不建议在处理消息过程中进行修改<br>
 * 
 * @author hdh
 *
 */
public interface GameEvent {

	/**
	 * 事件id<br>
	 * 目前直接用class.getName();
	 * 
	 * @return
	 */
	String getId();

}
