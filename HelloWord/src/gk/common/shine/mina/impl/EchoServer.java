package gk.common.shine.mina.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import gk.common.shine.mina.IServer;
import gk.common.shine.mina.handler.EchoServerHandler;
import gk.common.shine.server.Server;
import gk.common.shine.server.config.ServerConfig;

public abstract class EchoServer extends Server implements IServer{

	protected final Logger log = Logger.getLogger(EchoServer.class);
	protected final CharsetDecoder decoder = (Charset.forName("UTF-8")).newDecoder();

	protected NioSocketAcceptor acceptor = null;

	protected String name = "DEFAULT_CONSOLE";

	protected EchoServer(ServerConfig serverConfig) {
		super(serverConfig);
	}

	public void run() {
		this.acceptor = new NioSocketAcceptor();

		OrderedThreadPoolExecutor threadpool = new OrderedThreadPoolExecutor(500);
		this.acceptor.getFilterChain().addLast("threadPool", new ExecutorFilter(threadpool));

		int recSize = 4 *1024;
		int sendSize = 1024;
		int readSize = 4 * 1024;
		int timeout = 30;

		this.acceptor.setReuseAddress(true);
		SocketSessionConfig sc = this.acceptor.getSessionConfig();
		sc.setReuseAddress(true);
		sc.setReceiveBufferSize(recSize);
		sc.setSendBufferSize(sendSize);
		sc.setTcpNoDelay(true);
		sc.setSoLinger(0);
		sc.setIdleTime(IdleStatus.READER_IDLE, timeout);
		sc.setReadBufferSize(readSize);
		this.acceptor.setHandler(new EchoServerHandler(this));
		try {
			this.acceptor.bind(new InetSocketAddress(this.serverConfig.getPort()));
			this.log.info("Echo Server Start At Port " + this.serverConfig.getPort());
		} catch (IOException e) {
			this.log.error("Echo Server Port " + this.serverConfig.getPort() + "Already Use:" + e.getMessage());
			System.exit(1);
		}

		super.run();
	}

	protected void response(IoSession session, String msg) {
		try {
			msg += "\n";
			byte[] response = msg.getBytes("UTF-8");
			IoBuffer buf = IoBuffer.wrap(response);
			WriteFuture future = session.write(buf);
			// 在100毫秒超时间内等待写完成
			future.awaitUninterruptibly(100);
			// The message has been written successfully
			if (future.isWritten()) {
				// log.info("write data succ");
			} else {
				log.warn("write data failure");
			}
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
		}
	}
	
}
