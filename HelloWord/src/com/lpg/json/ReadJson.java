package com.lpg.json;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReadJson {

	
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
	
	
}
