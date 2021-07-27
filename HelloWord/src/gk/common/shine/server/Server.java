package gk.common.shine.server;

import gk.common.shine.server.config.ServerConfig;

public abstract class Server implements Runnable {
	protected ServerConfig serverConfig;

	protected Server(ServerConfig serverConfig) {
		this.serverConfig = serverConfig;
	}

	public ServerConfig getServerConfig() {
		return serverConfig;
	}

	public void run() {
		Runtime.getRuntime().addShutdownHook(new Thread(new CloseByExit()));
	}

	protected abstract void stop();

	private class CloseByExit implements Runnable {
		public CloseByExit() {
		}

		public void run() {
			Server.this.stop();
		}
	}
}
