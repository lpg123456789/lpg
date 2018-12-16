package com.lpg.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 测试循环上的效率
 * @author lpg
 * 2018年11月27日
 */
public class TestLoop {

	public static void main(String[] args) throws Exception{
		
		testTwo();
		
	}
	
	/**
	 * 测试一条数据
	 * @throws Exception
	 */
	public static void testOne() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/a", "root", "123456");
		long beginTime = System.currentTimeMillis();
		Statement state = conn.createStatement();
		String sql = "insert into test(endTime,bbb,ccc) values(123,'张伟','汉企')"; // SQL语句
		state.executeUpdate(sql); // 将sql语句上传至数据库执行
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-beginTime);
		conn.close();// 关闭通道
	}
	
	
	public static void testTwo() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/a", "root", "123456");
		long beginTime = System.currentTimeMillis();	
		for (int i = 0; i < 100; i++) {
			Statement state = conn.createStatement();
			String sql = "insert into test(endTime,bbb,ccc) values(123,'张伟','汉企')"; // SQL语句
			state.executeUpdate(sql); // 将sql语句上传至数据库执行
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-beginTime);
		conn.close();// 关闭通道
	}
	
	
}
