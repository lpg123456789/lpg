package com.lpg.myTool.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

public class MoneyFile {

	public final static String FileDire = "E:\\moneyData";

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
			for (int i = 0; i < fileLists.length; i++) { // 循环遍历这个集合内容
				if (fileLists[i].isDirectory()) { // 判断元素是不是一个目录
					printFile(fileLists[i], File); // 如果是目录，继续调用本方法来输出其子目录，因为是其子目录，所以缩进次数 + 1
				} else {
					File.add(fileLists[i]); // 输出元素名称
				}
			}
		}
	}

	public static List<MoneyData> readFileByLine() {
		Vector<MoneyData> moneyDataList = new Vector<>();
		File dir = new File(FileDire);
		List<File> list = new ArrayList<>();
		printFile(dir, list);
		for (File file : list) {
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
		String str = y + "年" + m + "月" + d + "日";
		return str;
	}
}
