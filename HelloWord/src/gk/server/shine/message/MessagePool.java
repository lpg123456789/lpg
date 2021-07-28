package gk.server.shine.message;

import java.util.HashMap;

import gk.common.shine.command.Handler;
import gk.common.shine.message.Message;
import gk.server.shine.func.a.handler.AHandler;
import gk.server.shine.func.a.handler.TestConfigHandler;
import gk.server.shine.func.a.handler.TestEventHandler;
import gk.server.shine.func.a.message.AMessage;
import gk.server.shine.func.a.message.TestConfigMessage;
import gk.server.shine.func.a.message.TestEventMessage;
import gk.server.shine.func.b.handler.BHandler;
import gk.server.shine.func.b.message.BMessage;

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
		register(1, AHandler.class, AMessage.class);
		register(2, BHandler.class, BMessage.class);
		register(3, TestConfigHandler.class, TestConfigMessage.class);
		register(4, TestEventHandler.class, TestEventMessage.class);
	}

}
