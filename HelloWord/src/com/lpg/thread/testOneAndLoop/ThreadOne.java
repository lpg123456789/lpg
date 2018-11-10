package com.lpg.thread.testOneAndLoop;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author lpg
 * 2018年11月10日
 */
public class ThreadOne {

	/**
	 * 用map来修改
	 */
	public static Map<Integer,MyObject> map=new HashMap<>();
	
	public static void main(String[] args) {
		//map循环
		for (int i = 0; i < 10; i++) {
			MyObject object=new MyObject(i);
			map.put(i, object);
		}
		
		//线程1
		new Thread(new Runnable() {
			@Override
			public void run() {
				functionOne();
			}
		}, "线程1" ).start();
		
		//线程2
		new Thread(new Runnable() {
			@Override
			public void run() {
				functionTwo(1);
			}
		}, "线程2" ).start();
		
	}
	
	/**
	 * 一个线程在遍历
	 */
	public static void functionOne() {
		for(int key:map.keySet()) {
			System.out.println("集合的key "+key);
			MyObject myObject=map.get(key);
			myObject.toMyString();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myObject.toMyString();
		}
	}
	
	/**
	 * 一个线程在执行
	 * @param key
	 */
	public static void functionTwo(int key) {
		MyObject myObject=map.get(key);
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		myObject.setAge(1111);
		
	}
	
	
}
