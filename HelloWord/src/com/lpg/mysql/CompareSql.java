package com.lpg.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 计算sql查询语句
 * @author lpg
 * @date 2018年8月23日 
 */
public class CompareSql {

	
	public static void main(String[] args) throws Exception {
		//testSql1();
		testSql2();
	}
	
	/**
	 * 全部查出
	 */
	public static void testSql1() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/xx_lpg_game", "root", "123456");
		long beginTime=System.currentTimeMillis();
		Statement state = conn.createStatement();
		String sql = "select * from user";
		ResultSet re = state.executeQuery(sql);
		//用while而不是用if
		while (re.next()) {
			 System.out.println(re.getString("id")+" "+re.getString("name"));
		}
		long endTime=System.currentTimeMillis();
		System.out.println(endTime-beginTime);
		conn.close();
	}
	
	/**
	 * 一条条查出
	 */
	public static void testSql2() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/xx_lpg_game", "root", "123456");
		long beginTime=System.currentTimeMillis();
		int index=1;
		while(index<=542) {
			Statement state = conn.createStatement();
			String sql = "select * from user where id="+index;
			ResultSet re = state.executeQuery(sql);
			if(re.next()) {
				 System.out.println(re.getString("id")+" "+re.getString("name"));
			}
			index++;
		}
		long endTime=System.currentTimeMillis();
		System.out.println(endTime-beginTime);
		conn.close();
	}
	
}
