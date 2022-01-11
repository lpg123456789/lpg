package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import io.netty.handler.codec.http.HttpResponse;

/**
 * springboot打成包
 * lpg
 * 2020年4月6日
 */
public class Main {

	public static void main(String[] args) {
		
		Float a=1.1f;
		System.out.println(a);
		
		  
		a+=1.2f;
		System.out.println(a);
		
		System.out.println(a.intValue());
		/*	        Calendar ca = Calendar.getInstance();
			        TimeZone tz = ca.getTimeZone();
			        System.out.println(tz.getID()+" "+tz.getDisplayName());*/
		        
		/*   int a=0 | 1 << 0;
		   
		   System.out.println(1<<2);
		   
		   System.out.println(a);*/
		       
		/*int day=5;
		 //int state = 1;
		  int a = (1 << day)-1;
		 System.out.println(Integer.toBinaryString(a)+" "+a);
		 
		 int c=a | a + 1;
		 
		 System.out.println(Integer.toBinaryString(c)+" "+c);
		 
		 int b = (1 << day);
		 System.out.println(Integer.toBinaryString(b)+" "+b);
		 
		 int d=b | b + 1;
		 System.out.println(Integer.toBinaryString(d)+" "+d);*/
		
		/* int e=(1 << 4)-1;
		 System.out.println(Integer.toBinaryString(13)+" "+13);
		 
		 int g=13 | 13 + 1;
		 System.out.println(Integer.toBinaryString(g)+" "+g);*/
		
		
	}

	public static void readFile() {
		// 读取文件
		String filepath = "F:\\common\\trunk\\config\\excel";
		File file = new File(filepath);
		String[] filelist = file.list();
		for (int i = 0; i < filelist.length; i++) {
			File readfile = new File(filepath + "\\" + filelist[i]);
			System.out.println("name=" + readfile.getName());

		}
	}
	
	public static void run_cmd() {
		String strcmd="F:\\common\\trunk\\config\\tool\\a.bat";
		Runtime rt = Runtime.getRuntime(); // Runtime.getRuntime()返回当前应用程序的Runtime对象
		Process ps = null; // Process可以控制该子进程的执行或获取该子进程的信息。
		try {
			System.out.println("111");
			ps = rt.exec(strcmd); // 该对象的exec()方法指示Java虚拟机创建一个子进程执行指定的可执行程序，并返回与该子进程对应的Process对象实例。
			//ps.waitFor(); // 等待子进程完成再往下执行。
			System.out.println("2222");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		int i = ps.exitValue(); // 接收执行完毕的返回值
		if (i == 0) {
			System.out.println("执行完成.");
		} else {
			System.out.println("执行失败.");
		}

		ps.destroy(); // 销毁子进程
		ps = null;
	}
	
	public static void test(){
		String bat = "F:\\common\\trunk\\config\\tool\\config2jsonTest.bat";
		 StringBuilder sb = new StringBuilder();
	        try {
	            Process child = Runtime.getRuntime().exec(bat);
	            InputStream in = child.getInputStream();
	            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));
	            String line;
	            while((line=bufferedReader.readLine())!=null)
	            {
	                sb.append(line + "\n");
	            }
	               in.close();
	               System.out.println("sb:" + sb.toString());
			  try {
			      child.waitFor();
			  } catch (InterruptedException e) {
			      System.out.println(e);
			  }
	           
	            System.out.println("callCmd execute finished");           
	        } catch (IOException e) {
	            System.out.println(e);
	        }
	}
}
