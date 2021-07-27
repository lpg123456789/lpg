package gk.common.shine.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gk.common.shine.message.Message;

public abstract class Handler implements ICommand {

	protected Logger log = LoggerFactory.getLogger(getClass());
	/**
	 * 消息
	 */
	private Message message;
	
	@Override
    public void execute() {
        action();
    }
	
	protected abstract void action();

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
}
