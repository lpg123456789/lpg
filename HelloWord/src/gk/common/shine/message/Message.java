package gk.common.shine.message;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

//通过json来做
public abstract class Message extends Bean{

	private IoSession session;

	private Object data;//转成出的数据

	public abstract int getId();

	public IoSession getSession() {
		return session;
	}

	public void setSession(IoSession session) {
		this.session = session;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public boolean write(IoBuffer buffer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean read(IoBuffer buffer) {
		// TODO Auto-generated method stub
		return false;
	}

}
