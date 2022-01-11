package com.lpg.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author lpg 2021年8月9日 字符串替换
 * 
 */
public class JsonReplace {

	/**
	 * @author itmyhome
	 */
	public static void main(String[] args) {
		File f = new File("E:\\server\\trunk\\logic\\src\\com\\game\\func\\newshow\\manager\\NewShowManager.java");
		print(f);
	}

	/**
	 * 遍历目录
	 *
	 * @param f
	 * @param len
	 */
	public static void print(File f) {
		File[] file = f.listFiles();
	
		for (int i = 0; i < file.length; i++) {
			if (file[i].isDirectory()) { //推断是否目录
				print(file[i]);
			}
	
			// 为防止输出文件覆盖源文件，所以更改输出盘路径 也可自行设置其它路径
			File readfile = new File(file[i].getAbsolutePath());
			File outPath = new File(file[i].getAbsolutePath());
			if (!readfile.isDirectory()) {
				String filename = readfile.getName(); // 读到的文件名称
				String absolutepath = readfile.getAbsolutePath(); // 文件的绝对路径
				readFile(absolutepath, filename, outPath); // 调用 readFile
			}
			
		}
		System.out.println("替换结束");
	}

	/**
	 * 变量单个文件
	 * @param f
	 */
	public static void printFile(File f) {
		// 为防止输出文件覆盖源文件，所以更改输出盘路径 也可自行设置其它路径
		File readfile = new File(f.getAbsolutePath());
		File outPath = new File("E:\\java");
		String filename = readfile.getName(); // 读到的文件名称
		String absolutepath = readfile.getAbsolutePath(); // 文件的绝对路径
		readFile(absolutepath, filename, outPath); // 调用 readFile
	}

	/**
	 * 读取目录下的文件
	 *
	 * @return
	 */
	public static void readFile(String absolutepath, String filename, File outPath) {
		try {
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(absolutepath), "utf-8")); // 数据流读取文件

			StringBuffer strBuffer = new StringBuffer();
			String oldStr1 = "JSON.toJSONString";String newStr1 = "JsonUtil.toJson";
			String oldStr2 = "JSON.parseObject";String newStr2 = "JsonUtil.toObject";
			boolean flag=false;
			for (String temp = null; (temp = bufReader.readLine()) != null; temp = null) {
				if (temp.indexOf(oldStr1) != -1) { // 推断当前行是否存在想要替换掉的字符
					temp = temp.replace(oldStr1, newStr1); // 此处进行替换
					flag=true;
				}
				if (temp.indexOf(oldStr2) != -1) { // 推断当前行是否存在想要替换掉的字符
					temp = temp.replace(oldStr2, newStr2); // 此处进行替换
					flag=true;
				}	
				strBuffer.append(temp);
				strBuffer.append(System.getProperty("line.separator")); // 换行符
			}
			bufReader.close();

			if(!flag) {
				return;
			}
			if (outPath.exists() == false) { // 检查输出目录是否存在，若不存在先创建
				outPath.mkdirs();
				System.out.println("已成功创建输出目录：" + outPath);
			}
			PrintWriter printWriter = new PrintWriter(outPath + "\\" + filename, "utf-8"); // 替换后输出文件路径
			printWriter.write(strBuffer.toString().toCharArray()); //又一次写入
			printWriter.flush();
			printWriter.close();
			System.out.println("文件   " + absolutepath + "  已成功输出到    " + outPath + "\\" + filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
