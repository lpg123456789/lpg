package gk.cross.shine.cross.across.message;

import org.apache.mina.core.buffer.IoBuffer;

import gk.common.shine.message.Message;

public class ACrossMessage extends Message{

	@Override
	public int getId() {
		return 100;
	}

}
