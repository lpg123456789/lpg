package com.lpg.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库中删除字段
 * @author lpg 2018年11月5日
 */
public class DbSqlTwo {

	// SQL异常:Operation not allowed after ResultSet closed
	public static void main(String[] args) throws Exception {
		testSelect();
	}

	/**
	 * 有时候是不可以的， 注意库和表名要对的上
	 * 
	 * @throws Exception
	 */
	public static void testSelect() throws Exception {
		String tableSchema="xx_s1_game";
		String columnName="job";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.3:3306/"+tableSchema+"", "xianxia", "xianxia_gg^");
		Statement state = conn.createStatement();
		// String sql = "mysqldump -uroot -p123456 aaaa > test.sql";
		String sql = "select table_name from information_schema.tables where table_schema='"+tableSchema+"' and table_type='base table'";
		ResultSet re = state.executeQuery(sql);
		
		List<String> dropTableList=new ArrayList<>();
		// 用while而不是用ifs
		while (re.next()) {
			Statement state2 = conn.createStatement();
			String tableName = re.getString(1);
			//库名+表名+列名
			String sql2="select count(*) from information_schema.columns where table_schema='"+tableSchema+"' and table_name = '"+tableName+"' and column_name = '"+columnName+"'";
			ResultSet re2 = state2.executeQuery(sql2);
			re2.next();	
			int count=re2.getInt(1);
			if(count>=1) {
				dropTableList.add(tableName);
				System.out.println(tableName + " 表中有改字段");
			}
		}
		
		System.out.println("执行结束");
		//删除字段
//		for (String dropName : dropTableList) {
//			Statement state2 = conn.createStatement();
//			String sql2="ALTER TABLE "+dropName+" DROP COLUMN "+columnName+"";
//			state2.execute(sql2);
//			System.out.println(dropName + " 删除字段 "+columnName);
//		}
		conn.close();
	}

}
