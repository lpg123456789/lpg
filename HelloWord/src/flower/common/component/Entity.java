package flower.common.component;

import flower.common.communication.MessageHandler;
import flower.common.component.event.GameEventListener;

/**
 * 实体
 * 
 * @author hdh
 *
 * @param <C> 该实体相关的组件
 */
public interface Entity<C extends Component> extends Component {

    /**
     * 唯一id
     * 
     * @return
     */
    long getId();

    /**
     * 注册组件
     * 
     * @param tag
     * @param component
     */
    void registerComponent(String tag, C component);

    /**
     * 移除组件
     * 
     * @param tag
     */
    void removeComponent(String tag);

    /**
     * 获取指定组件
     * 
     * @param <T>
     * @param tag
     * @return
     */
    <T extends C> T getComponent(String tag);

    /**
     * 注册事件监听
     * 
     * @param eventId
     * @param listener
     */
    void registerEventListener(String eventId, GameEventListener listener);

    /**
     * 移除事件监听
     * 
     * @param eventId
     * @param listener
     */
    void removeEventListener(String eventId, GameEventListener listener);

    /**
     * 注册协议处理
     * 
     * @param msgId
     * @param handler
     */
    void registerMessageHandler(int msgId, MessageHandler<?, ?> handler);

    /**
     * 移除协议处理
     * 
     * @param msgId
     * @param handler
     */
    void removeMessageHandler(int msgId, MessageHandler<?, ?> handler);

}
