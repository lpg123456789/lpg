package com.lpg.myFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 可以copy文件或者jar包，方便开发
 * @author lpg
 * 2018年11月10日
 */
public class CopyDemoTwo {

	public static void main(String[] args) throws Exception {
		String fileFile = "E:/SmallRedIconConst.java";
		File oldFile = new File(fileFile);
		String newPath = "E:/copy/";
		Files.copy(Paths.get(oldFile.toURI()), new FileOutputStream(newPath + oldFile.getName()));
	}

}
