package com.lpg.mysql;

import java.io.File;
import java.io.IOException;

/**
 * 远程copy
 * @author lpg
 * 2018年12月28日
 */
public class DbCopyTwo {

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
		fileName="D:\\two.sql";
		// 拼接命令行的命令
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("mysqldump").append(" --opt").append(" -h").append("192.168.1.35");
		stringBuilder.append(" --user=").append("root").append(" --password=").append("xianxia_gg^").append(" --skip-lock-tables");
		stringBuilder.append(" --result-file=").append(fileName).append(" --default-character-set=utf8 ").append("xx_s1_game");
		System.out.println(stringBuilder.toString());
		try {
			// 调用外部执行exe文件的javaAPI
			Process process = Runtime.getRuntime().exec(stringBuilder.toString());
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
}
