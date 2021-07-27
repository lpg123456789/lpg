package gk.common.shine.server.config;

import java.util.Date;
import java.util.HashSet;

/**
 * 服务器配置
 * 
 * @author zhangzhen
 * @date 2017年8月14日
 * @version 1.0
 */
public class ServerConfig {
	private int id;
	private String hostName;
	private int port;
	private Date open;
	private boolean useSsl = false;
	private String sslCert;
	private String sslKey;
	private HashSet<Integer> servers = new HashSet<Integer>();

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOpen() {
		return this.open;
	}

	public void setOpen(Date open) {
		this.open = open;
	}

	public HashSet<Integer> getServers() {
		return this.servers;
	}

	public void setServers(HashSet<Integer> servers) {
		this.servers = servers;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isUseSsl() {
		return useSsl;
	}

	public void setUseSsl(boolean useSsl) {
		this.useSsl = useSsl;
	}

	public String getSslCert() {
		return sslCert;
	}

	public void setSslCert(String sslCert) {
		this.sslCert = sslCert;
	}

	public String getSslKey() {
		return sslKey;
	}

	public void setSslKey(String sslKey) {
		this.sslKey = sslKey;
	}

	public String toString() {
		return id + "," + port + "," + hostName;
	}
}