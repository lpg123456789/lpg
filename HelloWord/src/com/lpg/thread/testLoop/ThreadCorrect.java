package com.lpg.thread.testLoop;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lpg
 * 2018年11月10日
 */
public class ThreadCorrect {

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
				functionOne();
			}
		}, "线程2" ).start();
		
	}
	
	public static void functionOne() {
		for(Integer key:map.keySet()) {
			synchronized (key) {
				MyObject myObject=map.get(key);
				if(myObject!=null) {
					myObject.toMyString();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				map.remove(key);
			}
		}
	}
	
	public static void functionTwo() {
		for(Integer key:map.keySet()) {
			synchronized (key) {
				MyObject myObject=map.get(key);
				if(myObject!=null) {
					myObject.toMyString();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				map.remove(key);
			}
		}
	}
}
