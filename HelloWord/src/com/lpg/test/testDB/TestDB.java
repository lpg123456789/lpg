package com.lpg.test.testDB;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 测试数据库只开启不关闭
 * @author lpg
 * @date 2018年9月19日 
 */
public class TestDB {
	
	public static void main(String[] args) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");// 加载驱动
		String jdbc = "jdbc:mysql://127.0.0.1:3306/h5_game?characterEncoding=GBK";
		
		for (int i = 0; i <1000 ; i++) {
			Connection conn = DriverManager.getConnection(jdbc, "root", "123456");// 链接到数据库
			System.out.println("i的值是 "+i);
		}
		
	}
	
}
