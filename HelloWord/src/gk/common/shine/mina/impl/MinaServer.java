package gk.common.shine.mina.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import org.apache.commons.codec.binary.Base64;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gk.common.shine.mina.IServer;
import gk.common.shine.mina.code.websocket.WebSocketCodecFactory;
import gk.common.shine.mina.code.websocket.WebSocketCodecPacket;
import gk.common.shine.mina.handler.ServerProHandler;
import gk.common.shine.server.Server;
import gk.common.shine.server.config.ServerConfig;
import gk.common.shine.server.loader.ServerConfigLoader;
import gk.common.shine.server.structs.SessionAttType;

public abstract class MinaServer extends Server implements IServer {
	
	protected static Logger log = LoggerFactory.getLogger(MinaServer.class);

    protected NioSocketAcceptor acceptor;
    protected List<IoSession> sessions = new ArrayList<IoSession>();
    /**
     * 是否已开始监听
     */
    private volatile boolean started;

	protected MinaServer(Properties properties, boolean isGate) {
		super(new ServerConfigLoader().load(properties, isGate));
	}

	public IoSession getSession(long sessionId) {
		return acceptor.getManagedSessions().get(sessionId);
	}

	protected MinaServer(ServerConfig serverConfig) {
		super(serverConfig);
	}
	
	@Override
	public void run() {
        super.run();
        new Thread(new ConnectServer(this)).start();
        // 阻塞等待到启动成功或报错
        while (!started) {
            try {
                Thread.sleep(50l);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
	
	
	public abstract void sessionClosed(IoSession paramIoSession);

    public abstract void exceptionCaught(IoSession paramIoSession, Throwable paramThrowable);

    public abstract void doCommand(IoSession paramIoSession, IoBuffer paramIoBuffer);

    /**
     * 协议编解码工具
     * 
     * @return
     */
    protected ProtocolCodecFactory buildCodecFactory() {
        return new WebSocketCodecFactory();
    }
	
	
	
	
    private class ConnectServer implements Runnable {
        private Logger log = LoggerFactory.getLogger(ConnectServer.class);
        private MinaServer server;
        private Timer sendTimer;

        public ConnectServer(MinaServer server) {
            this.server = server;
            this.sendTimer = new Timer("Send-Timer");
        }

        public void run() {
            MinaServer.this.acceptor = new NioSocketAcceptor();

            DefaultIoFilterChainBuilder chain = MinaServer.this.acceptor.getFilterChain();
            // add ssl support
			/*if (MinaServer.this.serverConfig.isUseSsl()) {
			    addSSLSupport(chain);
			}*/
            //chain.addLast("codec", new ProtocolCodecFilter(buildCodecFactory()));
            chain.addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"), LineDelimiter.WINDOWS.getValue(), LineDelimiter.WINDOWS.getValue())));
            OrderedThreadPoolExecutor threadpool = new OrderedThreadPoolExecutor(500);
            chain.addLast("threadPool", new ExecutorFilter(threadpool));

            int recsize = 10 * 1024; // 10k
            int sendsize = 64 * 1024; // 64k
            int timeout = 30;
            MinaServer.this.acceptor.setReuseAddress(true);
            SocketSessionConfig sc = MinaServer.this.acceptor.getSessionConfig();
            sc.setReuseAddress(true);
            sc.setReceiveBufferSize(recsize);
            sc.setSendBufferSize(sendsize);
            sc.setTcpNoDelay(true);
            sc.setSoLinger(0);
            sc.setIdleTime(IdleStatus.READER_IDLE, timeout);

            MinaServer.this.acceptor.setHandler(new ServerProHandler(this.server));
            try {
                MinaServer.this.acceptor.bind(new InetSocketAddress(MinaServer.this.getServerConfig().getPort()));
                started = true;
                this.log.info("Mina Server Start At Port " + MinaServer.this.getServerConfig().getPort());
            } catch (IOException e) {
                this.log.error("Mina Server Port " + MinaServer.this.getServerConfig().getPort() + "Already Use:" + e.getMessage());
                System.exit(1);
            }

            this.sendTimer.scheduleAtFixedRate(new MinaServer.SendTimer(), 1L, 1L);
        }

		/* private void addSSLSupport(DefaultIoFilterChainBuilder chain) {
		    try {
		        // see:
		        // https://www.cnblogs.com/wucao/archive/2017/02/27/6474711.html
		        // 证书
		        InputStream inStream = null;
		        Certificate certificate = null;
		
		        try {
		            inStream = new FileInputStream(MinaServer.this.serverConfig.getSslCert());
		            CertificateFactory cf = CertificateFactory.getInstance("X.509");
		            certificate = cf.generateCertificate(inStream);
		        } finally {
		            if (inStream != null) {
		                inStream.close();
		            }
		        }
		
		        // 私钥
		        // https://stackoverflow.com/questions/3243018/how-to-load-rsa-private-key-from-file
		
		        // PrivateKey privateKey =
		        // readPKCS8PrivateKey(MinaServer.this.serverConfig.getSslKey());
		
		        // 读取rsa private key
		        PrivateKey privateKey = readRSAPrivateKey(MinaServer.this.serverConfig.getSslKey());
		
		        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		        ks.load(null, null);
		        Certificate[] certificates = { certificate };
		        ks.setKeyEntry("key", privateKey, "".toCharArray(), certificates);
		
		        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		        kmf.init(ks, "".toCharArray());
		
		        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
		        sslContext.init(kmf.getKeyManagers(), null, null);
		
		        chain.addLast("ssl", new SslFilter(sslContext));
		        this.log.info("Mina Server SSL ON");
		    } catch (Exception e) {
		        this.log.error(e.getMessage(), e);
		    }
		}
		
		private PrivateKey readRSAPrivateKey(String privateFile) throws Exception {
		    BufferedReader bufferReader = null;
		    PEMParser parser = null;
		    try {
		        bufferReader = new BufferedReader(new FileReader(privateFile));
		        parser = new PEMParser(bufferReader);
		
		        Security.addProvider(new BouncyCastleProvider());
		        PEMKeyPair pemKeyPair = (PEMKeyPair) parser.readObject();
		        KeyPair keyPair = new JcaPEMKeyConverter().getKeyPair(pemKeyPair);
		        return keyPair.getPrivate();
		    } finally {
		        if (bufferReader != null) {
		            bufferReader.close();
		        }
		        if (parser != null) {
		            parser.close();
		        }
		    }
		}
		
		@SuppressWarnings("unused")
		private PrivateKey readPKCS8PrivateKey(String privateFile) throws Exception {
		    BufferedReader bufferReader = null;
		    StringBuffer buffer = new StringBuffer();
		    try {
		        bufferReader = new BufferedReader(new FileReader(privateFile));
		        String str = null;
		        while ((str = bufferReader.readLine()) != null) {
		            // if (!str.startsWith("-")) {
		            buffer.append(str.trim());
		            // }
		        }
		    } finally {
		        if (bufferReader != null) {
		            bufferReader.close();
		        }
		    }
		
		    // 读取pkcs8格式
		    byte[] keyBytes = Base64.decodeBase64(buffer.toString());
		    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		    return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
		}*/
    }
    
    
    private class SendTimer extends TimerTask {
        private SendTimer() {
        }

        public void run() {
            try {
                MinaServer.this.sessions.clear();
                MinaServer.this.sessions.addAll(MinaServer.this.acceptor.getManagedSessions().values());
                for (IoSession ioSession : MinaServer.this.sessions) {
                    IoBuffer sendbuf = null;
                    synchronized (ioSession) {
                        if (ioSession.containsAttribute(SessionAttType.SESSION_MSG_BUFFER)) {
                            sendbuf = (IoBuffer) ioSession.getAttribute(SessionAttType.SESSION_MSG_BUFFER);
                            ioSession.removeAttribute(SessionAttType.SESSION_MSG_BUFFER);
                        }
                    }
                    if ((sendbuf != null) && (sendbuf.position() > 0)) {
                        sendbuf.flip();
                        ioSession.write(WebSocketCodecPacket.buildPacket(sendbuf));
                    }
                }
            } catch (Exception e) {
                log.error("send message error：", e);
            }
        }
    }

}
