package com.lpg.xinhaiTool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 查找数据库某一张表
 * @author lpg
 * 2018年11月28日
 */
public class DBConfigData {
	
	public static String dbBase="xx_config_1";
	public static String userName="xianxia";
	public static String password="xianxia_gg^";
	public static String tableName="active";
	
	public static void main(String[] args) throws Exception {
		SelectConfig();
	}
	
	public static void SelectConfig() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.3:3306/"+dbBase, userName, password);
		Statement state = conn.createStatement();
		//String sql = "mysqldump -uroot -p123456 aaaa > test.sql";
		String sql = "select * from "+tableName;
		ResultSet re = state.executeQuery(sql);
		//用while而不是用if
		while (re.next()) {
			System.out.println(re.getString("id")+" "+re.getString("configData"));
		}
		conn.close();
	}
}
