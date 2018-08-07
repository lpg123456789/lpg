package com.lpg.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConnect {

	public static void main(String[] args) throws Exception {
		
		testSelect();
		
	}
	
	public static void testSelect() throws Exception {

		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/h5_game", "root", "123456");
		Statement state = conn.createStatement();
		String sql = "select * from test";
		ResultSet re = state.executeQuery(sql);
		//用while而不是用if
		while (re.next()) {
			System.out.println(re.getInt(1)+" "+re.getString(2)+" "+re.getInt(3));
		}
		conn.close();

	}
	
	
	
	
	/**
	 * 测试增长
	 * @throws ClassNotFoundException 
	 */
	public static void testInsert() throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");// 加载驱动
		String jdbc = "jdbc:mysql://127.0.0.1:3306/h5_game?characterEncoding=GBK";
		Connection conn = DriverManager.getConnection(jdbc, "root", "123456");// 链接到数据库
		Statement state = conn.createStatement(); // 容器
		//测试
		
		//Exception in thread "main" java.sql.SQLException: Column count doesn't match value count at row 1
		//String sql = "insert into test values('赵',30)";// SQL语句
		
		//ok
		//String sql = "insert into test(name,age) values('赵',30)";// SQL语句
		
		//ok(id，自增长，name可以为null，age不能为空)，执行完毕name为Null
		//String sql = "insert into test(age) values(30)";
		
		//ok(name可以为null，age不能为空)。执行完毕age为0
		//String sql = "insert into test(name) values('钱')";
		
		//ok(id是自增)
		String sql = "insert into test(id,name,age) values(211,'孙',100)";
		
		state.executeUpdate(sql); // 将sql语句上传至数据库执行
		conn.close();// 关闭通道
		System.out.println("执行完毕");
		
	}
	

}
