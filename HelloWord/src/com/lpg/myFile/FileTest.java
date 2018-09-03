package com.lpg.myFile;

import java.io.File;

public class FileTest {

	public static void main(String[] args) {
		
		File f1 = new File("d://book//1");	//对
		
		System.out.println(f1.getName());

		File f2 = new File("d:/book/1");	//对

		File f3 = new File("d:\\book\\1");	//对	

		File f4 = new File("d:\book\1");	//错

		System.out.println(f4.getName());
		
	}
	
	
		
}
