package gk.common.shine.mina.context;

import org.apache.mina.core.buffer.IoBuffer;

public class ServerContext {
	private IoBuffer buff;

	public ServerContext() {
		this.buff = IoBuffer.allocate(256);
		this.buff.setAutoExpand(true);
		this.buff.setAutoShrink(true);
	}

	public void append(IoBuffer buff) {
		this.buff.put(buff);
	}

	public IoBuffer getBuff() {
		return this.buff;
	}
}
