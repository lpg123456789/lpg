package com.lpg.mysql;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

/**
 * sql内网批量执行，防止出现某个库人工漏执行。
 * 因为密码关系，只负责执行内网数据库，不负责执行开发者的数据库。
 * 
 * @author lpg 2021年7月15日
 */
public class BatchSqlUtil {

	public static void main(String[] args) throws Exception {
		String url = "db/1.6.1.sql";
		executeSql(url);
	}
	
	private static void executeSql(String url) throws Exception {
		List<Integer> serverIdList = new ArrayList<Integer>();
		ServerSimpleConfigList configList = new ServerSimpleConfigList();
		for (ServerSimpleConfig config : configList.getServers()) {
			if (config.getGroup().equals("dev")) {
				continue;
			}
			if (executeSql(config.getId(), config.getIp(), url)) {
				serverIdList.add(config.getId());
			}
		}
	}

	private static boolean executeSql(int id, String ip, String url) {
		boolean success = true;
		Connection conn = null;
		try {
			//每次都必须取一次，重复的不能再次使用
			Reader reader = Resources.getResourceAsReader(url);
			// 建立连接
			conn = DriverManager.getConnection("jdbc:mysql://" + ip + ":3336/rs_game" + id, "root", "rose@gk2020");
			// 创建ScriptRunner，用于执行SQL脚本
			ScriptRunner runner = new ScriptRunner(conn);
			runner.setErrorLogWriter(null);
			runner.setLogWriter(null);
			// 执行SQL脚本
			runner.runScript(reader);;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		} finally {
			//关闭链接
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	public static class ServerSimpleConfigList {
		/**
		 * 服务器列表
		 */
		private List<ServerSimpleConfig> servers = new ArrayList<>();

		public List<ServerSimpleConfig> getServers() {
			return servers;
		}

		public void setServers(List<ServerSimpleConfig> servers) {
			this.servers = servers;
		}

	}

	public static class ServerSimpleConfig {
		/**
		 * 服务器id
		 */
		private int id;
		/**
		 * 服务器名
		 */
		private String name;
		/**
		 * ip地址
		 */
		private String ip;
		/**
		 * 分组名
		 */
		private String group;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public String getGroup() {
			return group;
		}

		public void setGroup(String group) {
			this.group = group;
		}
	}

}
