package gk.client.shine;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaClient {
	private static String host = "127.0.0.1";
	private static int port = 9001;

	public static void main(String[] args) {
		IoSession session = null;
		IoConnector connector = new NioSocketConnector();
		//设置过滤器
		connector.getFilterChain().addLast("coderc", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"), LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));
		connector.setHandler(new MyClientHander());
		ConnectFuture future = connector.connect(new InetSocketAddress(host, port));
		future.awaitUninterruptibly();//等待我們的連接

		session = future.getSession();		
		session.write("3_贝吉塔");
		session.getCloseFuture().awaitUninterruptibly();//等待關閉連接
		connector.dispose();
	}

}
