package gk.common.shine.server.loader;


import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.log4j.Logger;

import gk.common.shine.server.config.ServerConfig;

/**
 * 服务端配置读取
 * 
 * @author oyxz
 * @since 2018-09-07
 * @version 1.0
 */
public class ServerConfigLoader {
	private Logger log = Logger.getLogger(ServerConfigLoader.class);

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public ServerConfig load(Properties properties, boolean isGate) {
		try {
			if (properties == null) {
				return null;
			}

			ServerConfig config = new ServerConfig();
			if (isGate) {
				config.setHostName(properties.getProperty("gate.server.host").trim());
				config.setPort(Integer.parseInt(properties.getProperty("gate.server.port").trim()));
			} else {
				config.setId(Integer.parseInt(properties.getProperty("game.server.id").trim()));
				config.setHostName(properties.getProperty("game.server.host").trim());
				config.setPort(Integer.parseInt(properties.getProperty("game.server.port").trim()));
				config.setOpen(this.format.parse(properties.getProperty("game.server.open").trim()));
				String serverStr = properties.getProperty("game.server.list");
				String[] servers = serverStr.split(",");
				for (String server : servers) {
					config.getServers().add(Integer.valueOf(Integer.parseInt(server)));
				}
			}

			// 读取ssl
			String sslUse = properties.getProperty("ssl.use");
			if (sslUse == null) {
				config.setUseSsl(false);
			} else {
				config.setUseSsl(Boolean.parseBoolean(sslUse.trim()));
			}

			String sslCert = properties.getProperty("ssl.cert");
			if (sslCert == null) {
				config.setSslCert("");
			} else {
				config.setSslCert(sslCert.trim());
			}

			String sslKey = properties.getProperty("ssl.key");
			if (sslKey == null) {
				config.setSslKey("");
			} else {
				config.setSslKey(sslKey.trim());
			}

			return config;
		} catch (Exception e) {
			this.log.error(e, e);
		}
		return null;
	}
}