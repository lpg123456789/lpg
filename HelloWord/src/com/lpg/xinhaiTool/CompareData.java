package com.lpg.xinhaiTool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author lpg
 * 对比文件中的java的字段和数据库中字段
 * 2018年12月5日
 */
public class CompareData {
	
	/**
	 * 数据库数据
	 */
	public static Map<String,List<String>> dataMap=new HashMap<>();
	
	/**
	 * 文件中数据
	 */
	public static Map<String,List<String>> fileMap=new HashMap<>();

	static {
		try {
			//getDataMap();
			getFileMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		for (String tableName : dataMap.keySet()) {
			System.out.println(tableName);
		}
	}
	
	/**
	 * 有时候是不可以的， 注意库和表名要对的上
	 * @throws Exception
	 */
	public static void getDataMap() throws Exception {
		System.out.println("开始获取数据库中的数据");
		String tableSchema="xx_s1_game";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.1.3:3306/"+tableSchema+"", "xianxia", "xianxia_gg^");
		Statement state = conn.createStatement();
		String sql = "select table_name from information_schema.tables where table_schema='"+tableSchema+"' and table_type='base table'";
		System.out.println(sql);
		ResultSet re = state.executeQuery(sql);		
		while (re.next()) {
			Statement state2 = conn.createStatement();
			String tableName = re.getString(1);
			//库名+表名
			String sql2="select * from information_schema.columns where table_schema='"+tableSchema+"' and table_name = '"+tableName+"'";
			ResultSet re2 = state2.executeQuery(sql2);
			while (re2.next()) {
				String colume_name=re2.getString("COLUMN_NAME");
				List<String> list=dataMap.get(colume_name);
				if(list==null) {
					list=new ArrayList<>();
					dataMap.put(tableName, list);
				}
				list.add(colume_name);
			}
		}
		conn.close();
		System.out.println("结束获取数据库中的数据");
	}
	
	/**
	 * 获取文件中的数据
	 */
	public static void getFileMap() {
		String filepath = "F:\\xianxia-workspace\\server\\game\\trunk\\game\\src\\com\\xh\\game\\datas";// D盘下的file文件夹的目录
		File file = new File(filepath);
		File[] fileLists = file.listFiles();
		for (File fileName : fileLists) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(fileName));
				String tempString = null;
				// 一次读入一行，直到读入null为文件结束
				while ((tempString = reader.readLine()) != null) {	
					if(tempString.startsWith("@EntityTable")) {
						getTableName(tempString);
					}
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e1) {
					}
				}
			}
		}
	}
	
	/**
	 * 
	 */
	private static void getTableName(String tempString) {
		String[] str=tempString.split("\\(");
		String[] strOne=str[1].split(",");
		
	}
	
	
}
