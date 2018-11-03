package com.lpg.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 写sql判断库中的表是否有数据
 * @author lpg
 * 2018年11月3日
 * https://blog.csdn.net/dreamjay1997/article/details/80372505
 */
public class DbSql {

	//SQL异常:Operation not allowed after ResultSet closed
	public static void main(String[] args) throws Exception {
		testSelect();
	}
	
	/**
	 * 有时候是不可以的，
	 * 注意库和表名要对的上
	 * @throws Exception
	 */
	public static void testSelect() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/aaaa", "root", "123456");
		Statement state = conn.createStatement();
		String sql = "select table_name from information_schema.tables where table_schema='aaaa' and table_type='base table'";
		ResultSet re = state.executeQuery(sql);
		//用while而不是用if
		while (re.next()) {
			Statement state2 = conn.createStatement();
			String tableName=re.getString(1);
			String sql2="select * from "+tableName;
			ResultSet re2=state2.executeQuery(sql2);
			re2.last(); 
			int rowCount = re2.getRow(); //获得ResultSet的总行数
			System.out.println(tableName+" "+rowCount);
		}
		conn.close();
	}
}
