package com.lpg.myFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 复制文件夹下整个目录
 * @author lpg
 * 2018年11月10日
 */
public class CopyDemo {

	public static void main(String[] args) throws Exception {
		
		String path="E:/book";
		String newPath="E:/copy/";
		
		File dir = new File(path);
		List<File> list1=new ArrayList<>();//装目录下所有文件路径
		List<File> list2=new ArrayList<>();//装指定文件路径
		getAllFile(dir,list1);
		
		//过滤得到指定文件
//		for (int i = 0; i < list1.size(); i++) {
//			if (list1.get(i).getName().endsWith(".xls")||list1.get(i).getName().endsWith(".xlsx")) {
//				list2.add(list1.get(i));
//			}
//		}
		
		//将指定文件复制到指定目录
		for (File file : list1) {
			Files.copy(Paths.get(file.toURI()),new FileOutputStream(newPath+file.getName()));
		}
	}
	
	//public void 
	
	//列出当前目录所有文件
	private static void getAllFile(File f,List<File> list) {
		File[] fs = f.listFiles();
		for (File file : fs) {
			list.add(file);
			if (file.isDirectory()) {
				getAllFile(file,list);//递归操作
			}
		}
	}
	
}
