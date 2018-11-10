package com.lpg.myFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 复制文件
 * @author lpg
 * 2018年11月10日
 */
public class CopyFile {
	
	public static void main(String[] args) throws IOException {
		String oldFile = "E:/SmallRedIconConst.java";
		String newFile = "E:/test";
		copyFile(oldFile, newFile);
	}
		
	public static void copyFile(String oldPath, String newPath) throws IOException {
        File oldFile = new File(oldPath);
        File file = new File(newPath);
        FileInputStream in = new FileInputStream(oldFile);
        FileOutputStream out = new FileOutputStream(file);;
        byte[] buffer=new byte[2097152];
        int readByte = 0;
        while((readByte = in.read(buffer)) != -1){
            out.write(buffer, 0, readByte);
        }
        in.close();
        out.close();
    }
	
}
