package gk.cross.shine.message;
	
import java.util.HashMap;

import gk.common.shine.command.Handler;
import gk.common.shine.message.Message;
import gk.cross.shine.cross.across.handler.ACrossHandler;
import gk.cross.shine.cross.across.message.ACrossMessage;


/**
 * 引用类列表
 * 
 * @author auto gen
 * @version 1.0
 */
public class MessagePool {
    private static MessagePool instance = new MessagePool();
    public static MessagePool getInstance() {
        return instance;
    }

    private HashMap<Integer, Class<? extends Handler>> id2handler = new HashMap<Integer, Class<? extends Handler>>();
    private HashMap<Integer, Class<? extends Message>> id2message = new HashMap<Integer, Class<? extends Message>>();

    public void register(int id, Class<? extends Handler> handlerClass, Class<? extends Message> messageClass) {
        id2handler.put(id, handlerClass);
        id2message.put(id, messageClass);
    }

    public Handler createHandler(int id) {
        Class<? extends Handler> handlerClass = id2handler.get(id);
        if (handlerClass == null) {
            return null;
        }
        Handler handler = null;
        try {
            handler = handlerClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return handler;
    }

    public Message createMessage(int id) {
        Class<? extends Message> messageClass = id2message.get(id);
        if (messageClass == null) {
            return null;
        }
        Message message = null;
        try {
            message = messageClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return message;
    }

    private MessagePool() {
		register(100, ACrossHandler.class, ACrossMessage.class);
    }
}
