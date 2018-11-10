package com.lpg.thread.testLoop;

import java.util.HashMap;
import java.util.Map;

/**
 * 遍历中移除相关的数据 ConcurrentModificationException
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
				functionOne();
			}
		}, "线程2" ).start();
		
	}
	
	public static void functionOne() {
		for(int key:map.keySet()) {
			MyObject myObject=map.get(key);
			myObject.toMyString();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.remove(key);
		}
	}
	
	public static void functionTwo() {
		for(int key:map.keySet()) {
			MyObject myObject=map.get(key);
			myObject.toMyString();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.remove(key);
		}
	}
	
	
}
