package qmr.com.game.core.mina.impl;

import qmr.com.game.core.mina.IServer;
import qmr.com.game.core.server.Server;
import qmr.com.game.core.server.config.ServerConfig;

public abstract class ClientServer extends Server implements IServer{

	 protected Logger log;
	    public static int gateSessionNumber = 1;
	    public static int worldSessionNumber = 1;
	    public static int publicSessionNumber = 1;
	    protected HashMap<Integer, List<IoSession>> gateSessions;
	    protected List<IoSession> worldSessions;
	    protected NioSocketConnector socket;

	    protected ClientServer(String serverConfig) {
	        this(serverConfig, gateSessionNumber, worldSessionNumber);
	    }

	    protected ClientServer(String serverConfig, int gSessionNumber, int wSessionNumber) {
	        super((new ClientServerConfigXmlLoader()).load(serverConfig));
	        this.log = Logger.getLogger(ClientServer.class);
	        this.gateSessions = new HashMap();
	        this.worldSessions = new ArrayList();
	        this.socket = null;
	        gateSessionNumber = gSessionNumber;
	        worldSessionNumber = wSessionNumber;
	    }

	    protected void init() {
	        super.init();
	    }

	    public void run() {
	        super.run();
	        this.socket = new NioSocketConnector();
	        this.socket.getFilterChain().addLast("codec", new ProtocolCodecFilter(new InnerServerProtocolCodecFactory()));
	        OrderedThreadPoolExecutor threadpool = new OrderedThreadPoolExecutor(500);
	        this.socket.getFilterChain().addLast("threadPool", new ExecutorFilter(threadpool));
	        int recsize = 524288;
	        int sendsize = 1048576;
	        SocketSessionConfig sc = this.socket.getSessionConfig();
	        sc.setReceiveBufferSize(recsize);
	        sc.setSendBufferSize(sendsize);
	        sc.setSoLinger(0);
	        this.socket.setHandler(new ServerProtocolHandler(this));
	        ClientServerConfig config = (ClientServerConfig)this.serverConfig;
	        if (config != null) {
	            int connected;
	            ConnectFuture connect;
	            IoSession session;
	            if (config.getWorldServer() != null) {
	                connected = 0;

	                while(connected < worldSessionNumber) {
	                    connect = this.socket.connect(new InetSocketAddress(config.getWorldServer().getIp(), config.getWorldServer().getPort()));
	                    connect.awaitUninterruptibly(60000L);
	                    if (!connect.isConnected()) {
	                        try {
	                            Thread.sleep(5000L);
	                        } catch (Exception var13) {
	                            this.log.error(var13, var13);
	                        }
	                    } else {
	                        session = connect.getSession();
	                        session.setAttribute("connect-server-id", config.getWorldServer().getId());
	                        session.setAttribute("connect-server-ip", config.getWorldServer().getIp());
	                        session.setAttribute("connect-server-port", config.getWorldServer().getPort());
	                        this.add(session, config.getWorldServer().getId(), 3);
	                        this.register(session, 3);
	                        ++connected;
	                    }
	                }
	            }

	            for(connected = 0; connected < config.getGateServers().size(); ++connected) {
	                ServerInfo info = (ServerInfo)config.getGateServers().get(connected);
	                int connected = 0;

	                while(connected < gateSessionNumber) {
	                    ConnectFuture connect = this.socket.connect(new InetSocketAddress(info.getIp(), info.getPort()));
	                    connect.awaitUninterruptibly();
	                    if (!connect.isConnected()) {
	                        try {
	                            Thread.sleep(5000L);
	                        } catch (Exception var12) {
	                            this.log.error(var12, var12);
	                        }
	                    } else {
	                        IoSession session = connect.getSession();
	                        session.setAttribute("connect-server-id", info.getId());
	                        session.setAttribute("connect-server-ip", info.getIp());
	                        session.setAttribute("connect-server-port", info.getPort());
	                        this.add(session, info.getId(), 1);
	                        this.register(session, 1);
	                        ++connected;
	                    }
	                }
	            }

	            if (config.getPublicServer() != null) {
	                connected = 0;

	                while(connected < publicSessionNumber) {
	                    connect = this.socket.connect(new InetSocketAddress(config.getPublicServer().getIp(), config.getPublicServer().getPort()));
	                    connect.awaitUninterruptibly(60000L);
	                    if (!connect.isConnected()) {
	                        try {
	                            Thread.sleep(5000L);
	                        } catch (Exception var11) {
	                            this.log.error(var11, var11);
	                        }
	                    } else {
	                        session = connect.getSession();
	                        session.setAttribute("connect-server-id", config.getPublicServer().getId());
	                        session.setAttribute("connect-server-ip", config.getPublicServer().getIp());
	                        session.setAttribute("connect-server-port", config.getPublicServer().getPort());
	                        this.add(session, config.getPublicServer().getId(), 4);
	                        this.register(session, 4);
	                        ++connected;
	                    }
	                }
	            }
	        }

	        this.connectComplete();
	    }

	    protected void reconnectPublic(int id, String ip, int port) {
	        int connected = 0;

	        while(connected < publicSessionNumber) {
	            ConnectFuture connect = this.socket.connect(new InetSocketAddress(ip, port));
	            connect.awaitUninterruptibly(60000L);
	            if (!connect.isConnected()) {
	                try {
	                    Thread.sleep(5000L);
	                } catch (Exception var7) {
	                    this.log.error(var7, var7);
	                }
	            } else {
	                IoSession session = connect.getSession();
	                session.setAttribute("connect-server-id", id);
	                session.setAttribute("connect-server-ip", ip);
	                session.setAttribute("connect-server-port", port);
	                this.register(session, 4);
	                ++connected;
	            }
	        }

	    }

	    protected abstract void connectComplete();

	    protected void removeSession(IoSession session, int id, int type) {
	        if (type == 1) {
	            synchronized(this.gateSessions) {
	                List<IoSession> sessions = (List)this.gateSessions.get(id);
	                if (sessions != null) {
	                    sessions.remove(session);
	                }
	            }
	        } else if (type == 3) {
	            synchronized(this.worldSessions) {
	                this.worldSessions.remove(session);
	            }
	        }

	    }

	    public List<IoSession> getWorldSessions() {
	        return this.worldSessions;
	    }

	    public HashMap<Integer, List<IoSession>> getGateSessions() {
	        return this.gateSessions;
	    }

	    public void add(IoSession session, int id, int type) {
	        if (type == 1) {
	            synchronized(this.gateSessions) {
	                List<IoSession> sessions = (List)this.gateSessions.get(id);
	                if (sessions == null) {
	                    sessions = new ArrayList();
	                    this.gateSessions.put(id, sessions);
	                }

	                ((List)sessions).add(session);
	            }
	        } else if (type == 3) {
	            synchronized(this.worldSessions) {
	                this.worldSessions.add(session);
	            }
	        }

	    }

	    public abstract void register(IoSession var1, int var2);

}
