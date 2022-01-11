package flower.common.component;

import flower.common.communication.MessageHandler;
import flower.common.communication.Protocol;
import flower.common.component.event.GameEventListener;

/**
 * 组件
 * 
 * @author hdh
 *
 */
public interface Component extends GameEventListener, MessageHandler<Protocol, Protocol>, LifeCycle {

}
