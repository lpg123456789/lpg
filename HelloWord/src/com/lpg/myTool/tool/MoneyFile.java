package com.lpg.myTool.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class MoneyFile {

	public final static String FileDire = "F:\\李培光的管理\\moneyData";
	
	static{
		File dir = new File(FileDire);
		if(!dir.exists()){
			dir.mkdirs();
		}
	}

	public static void writeInFileByfb(MoneyData moneyData) {
		String todayStr = MoneyFile.getTodayStr();
		File f = new File(MoneyFile.FileDire+"\\" + todayStr + ".txt");
		String content = moneyData.toFileData();
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			fw = new FileWriter(f.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printFile(File file, List<File> File) {
		if (file.isFile()) {
			System.out.println("您给定的是一个文件"); // 判断给定目录是否是一个合法的目录，如果不是，输出提示
		} else {
			File[] fileLists = file.listFiles(); // 如果是目录，获取该目录下的内容集合
			Arrays.sort(fileLists, new Comparator<File>() {
				public int compare(File f1, File f2) {
					long diff = f1.lastModified() - f2.lastModified();
					if (diff > 0)
						return 1;
					else if (diff == 0)
						return 0;
					else
						return -1;// 如果 if 中修改为 返回-1 同时此处修改为返回 1 排序就会是递减
				}
				public boolean equals(Object obj) {
					return true;
				}
			});
			for (int i = 0; i < fileLists.length; i++) { // 循环遍历这个集合内容
				File.add(fileLists[i]);
			}
			
		}
	}
	
	public static List<MoneyData> readFileByLine() {
		Vector<MoneyData> moneyDataList = new Vector<>();
		File dir = new File(FileDire);
		List<File> list = new ArrayList<>();
		printFile(dir, list);
		for (File file : list) {
			//System.out.println(file.getName());
			BufferedReader br = null;
			try {
				String name=file.getName().split("\\.")[0];
				MoneyData moneyData = new MoneyData(name);
				br = new BufferedReader(new FileReader(file.getAbsolutePath()));
				String temp;
				while ((temp = br.readLine()) != null) {
					moneyData.initData(temp);
				}
				moneyDataList.add(moneyData);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return moneyDataList;
	}

	public static String getTodayStr() {
		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
		int d = cal.get(Calendar.DATE);
		String str = y + "年" + (m+1) + "月" + d + "日";
		return str;
	}
}
