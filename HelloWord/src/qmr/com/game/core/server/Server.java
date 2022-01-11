package qmr.com.game.core.server;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import qmr.com.game.core.server.config.ServerConfig;

public abstract class Server implements Runnable{

	private String server_name;
    public static int server_id;
    private String server_web;
    protected ServerConfig serverConfig;
    public static final String DEFAULT_MAIN_THREAD = "Main";

    protected Server(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
        if (this.serverConfig != null) {
            this.init();
        }

    }
    
    protected void init() {
        this.server_name = this.serverConfig.getName();
        server_id = this.serverConfig.getId();
        this.server_web = this.serverConfig.getWeb();
    }

    public void run() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Server.CloseByExit(this.server_name)));
    }

    public String getServerName() {
        return this.server_name;
    }

    public int getServerId() {
        return server_id;
    }

    public String getServerWeb() {
        return this.server_web;
    }

    protected abstract void stop();

    private class CloseByExit implements Runnable {
        private Logger log = LogManager.getLogger(Server.CloseByExit.class);
        private String server_name;

        public CloseByExit(String server_name) {
            this.server_name = server_name;
        }

        public void run() {
            Server.this.stop();
            this.log.info(this.server_name + " Stop!");
        }
    }
}
