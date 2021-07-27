package gk.client.shine;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class MyClientHander extends IoHandlerAdapter {
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.println("發生異常時：exceptionCaught");
	}
	
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String msg = (String)message;
		System.out.println("客戶端收到數據"+msg);
	}
}