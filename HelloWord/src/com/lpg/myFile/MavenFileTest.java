package com.lpg.myFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MavenFileTest {

	public static void main(String[] args) throws Exception {

		String path = "E:/test.txt";
		String newPath = "E:/copy/";
		List<String> resultList = readFileByLines(path);
		List<File> FileList = getAllFile(resultList);

		for (File file : FileList) {
			Files.copy(Paths.get(file.toURI()), new FileOutputStream(newPath + file.getName()));
		}

	}

	public static List<String> readFileByLines(String fileName) {
		List<String> result = new ArrayList<>();
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				line++;

				result.add(tempString);
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
		return result;
	}

	private static List<File> getAllFile(List<String> list) {
		List<File> result = new ArrayList<>();
		for (String string : list) {
			result.add(new File(string));
		}
		return result;
	}

}
