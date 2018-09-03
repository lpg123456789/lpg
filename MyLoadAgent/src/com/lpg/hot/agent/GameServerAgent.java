package com.lpg.hot.agent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;

public class GameServerAgent {

	public static void agentmain(String args, Instrumentation inst) throws Exception {
		
		System.out.println("我的 agent 启动成功,开发重定义对象....");
		System.out.println(args);

		Class<?>[] allClass = inst.getAllLoadedClasses();
		for (Class<?> c : allClass) {
			
			System.out.println(c.getName());
			
			if (c.getName().endsWith("TestHot")) {
				String pathname = "F:\\TestHot.class";
				//String pathname = "config\\TestHot.class";
				File file = new File(pathname);
				try {
					byte[] bytes = fileToBytes(file);
					System.out.println("文件大小：" + bytes.length);
					ClassDefinition classDefinition = new ClassDefinition(c, bytes);
					inst.redefineClasses(classDefinition);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("转换代码。。。");
			}
		}
		System.out.println("热更新成功....");

	}
	
	public static void main(String[] args) {
		File file =new File("F:\\TestHot.class"); 
		if  (!file.exists())      
		{       
		    System.out.println("//不存在");    
		} else   
		{  
		    System.out.println("//目录存在");  
		}  
	}
	
	public static byte[] fileToBytes(File file) throws IOException {
		FileInputStream in = new FileInputStream(file);
		byte[] bytes = new byte[in.available()];
		in.read(bytes);
		in.close();
		return bytes;
	}
}
