package com.lpg.thread.testOneAndLoop;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadThree {

	/**
	 * 用map来修改
	 */
	public static Map<Integer,MyObject> map=new ConcurrentHashMap<>();
	
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
		map.remove(2);
		map.remove(6);
		map.remove(7);
	}
	
}
