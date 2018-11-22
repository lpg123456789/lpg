package com.lpg.myFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具，获取某一个路径下的所有类,然后类中的判断关键字
 * @author lpg 2018年11月3日
 */
public class FileTool {

	public static void main(String[] args) {
		//String filepath = "F:\\xianxia-workspace\\server\\game\\trunk\\game\\src\\com\\xh\\game\\module";// D盘下的file文件夹的目录
		
		//String filepath = "F:\\xianxia-workspace\\server\\game\\trunk\\game\\src\\com\\xh\\game\\datas";// D盘下的file文件夹的目录
		
		String filepath = "F:\\xianxia-workspace\\interface\\trunk\\protocol";//协议文件
		//文件集合
		List<String> fileList=new ArrayList<>();
		printFile(new File(filepath),fileList);
		
		//输出文件
//		System.out.println("数量 "+fileList.size());
//		for (String fileName : fileList) {
//			System.out.println("文件名 "+fileName);
//		}
		
		//文件信息
		String keyWord="PET_AWAKE";//注意大小写
		for (String fileName : fileList) {
			String[] name=fileName.split("\\.");//需要转义
			//if(name[0].endsWith("DS")) {
				//System.out.println("正在读取文件"+fileName);
				readFileByLines(fileName,keyWord);
			//}
		}
		
	}
	
	 /** 
     * 以行为单位读取文件，常用于读面向行的格式化文件 
     */  
    public static void readFileByLines(String fileName,String keyWord) {  
        File file = new File(fileName);  
        BufferedReader reader = null;  
        try {  
            reader = new BufferedReader(new FileReader(file));  
            String tempString = null;  
            int line = 1;  
            // 一次读入一行，直到读入null为文件结束  
            while ((tempString = reader.readLine()) != null) {  
                // 显示行号  
               // System.out.println("line " + line + ": " + tempString);  
                if(tempString.indexOf(keyWord)!=-1) {
                	System.out.println(fileName+" ------------------ line " + line);
                }
                line++;  
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
	

	/**
	 * 获取路径下的所有文件是（包括文件夹和文件）
	 * @param file
	 * @param list
	 */
	public static void printFile(File file, List<String> list) {
		if (file.isFile()) {
			System.out.println("您给定的是一个文件"); // 判断给定目录是否是一个合法的目录，如果不是，输出提示
		} else {
			File[] fileLists = file.listFiles(); // 如果是目录，获取该目录下的内容集合
			for (int i = 0; i < fileLists.length; i++) { // 循环遍历这个集合内容
				if (fileLists[i].isDirectory()) { // 判断元素是不是一个目录
					printFile(fileLists[i], list); // 如果是目录，继续调用本方法来输出其子目录，因为是其子目录，所以缩进次数 + 1
				} else {
					list.add(fileLists[i].getAbsolutePath()); // 输出元素名称
				}
			}
		}
	}

}
