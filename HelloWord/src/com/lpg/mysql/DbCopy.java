package com.lpg.mysql;

import java.io.File;
import java.io.IOException;

/**
 * 搭建自己的数据库环境
 * 
 * @author lpg 2018年12月28日
 */
public class DbCopy {

	public static void main(String[] args) {
		backup("", "", "", "", "", "");
		//recover("", "", "", "", "");
	}

	public static boolean backup(String hostIP, String userName, String password, String savePath, String fileName, String databaseName) {
		fileName += ".sql";
		File saveFile = new File(savePath);
		if (!saveFile.exists()) {// 如果目录不存在
			saveFile.mkdirs();// 创建文件夹
		}
		if (!savePath.endsWith(File.separator)) {
			savePath = savePath + File.separator;
		}
//		fileName="D:\\two.sql";
//		// 拼接命令行的命令
//		StringBuilder stringBuilder = new StringBuilder();
//		stringBuilder.append("mysqldump").append(" --opt").append(" -h").append("192.168.1.3");
//		stringBuilder.append(" --user=").append("xianxia").append(" --password=").append("xianxia_gg^").append(" --lock-all-tables=true");
//		stringBuilder.append(" --result-file=").append(fileName).append(" --default-character-set=utf8 ").append("xx_config_1");
//		System.out.println(stringBuilder.toString());
		
		//String str="mysqldump -uroot -p123456 -h 192.168.1.35 one > D:\\bakcup.sql";
		
		String str="mysqldump -uroot -p123456 -h119.23.208.59 mysql > D:\\bakcup.sql";
		System.out.println(str);
		try {
			// 调用外部执行exe文件的javaAPI
			Process process = Runtime.getRuntime().exec(str);
			if (process.waitFor() == 0) {// 0 表示线程正常终止。
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean recover(String filepath, String ip, String database, String userName, String password) {
		ip="192.168.1.35";
		filepath="D:\\one.sql";
		database="one";
		userName="root";
		password="123456";
		
		String stmt1 = "mysqladmin -h " + ip + " -u " + userName + " -p" + password + " create " + database;
		String stmt2 = "mysql -h " + ip + " -u" + userName + " -p" + password + " " + database + " < " + filepath;

		String[] cmd = { "cmd", "/c", stmt2 };

		try {
			Runtime.getRuntime().exec(stmt1);
			System.out.println(stmt2);
			Runtime.getRuntime().exec(cmd);
			System.out.println("数据已从 " + filepath + " 导入到数据库中");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
