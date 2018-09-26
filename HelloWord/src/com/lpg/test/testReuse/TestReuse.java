package com.lpg.test.testReuse;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Administrator
 * 多线程+队列 测试对象复用
 */
public class TestReuse {

	private static BlockingQueue<Integer> testReuse = new LinkedBlockingQueue<Integer>(20);
	
	private static BlockingQueue<Integer> tempReuse = new LinkedBlockingQueue<Integer>(20);
	
	public static void main(String[] args) {
		
			try {
				testReuse.put(1);
				testReuse.put(2);
				testReuse.put(3);
				testReuse.put(4);
				testReuse.put(5);
				testReuse.put(6);
				testReuse.put(7);
				testReuse.put(8);
				testReuse.put(9);
				testReuse.put(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//出队
			new Thread(new Runnable() {		
				@Override
				public void run() {
					try {
						while(true) {
							int temp=testReuse.take();
							tempReuse.put(temp);
							System.out.println("测试队列111 出队 "+temp+" 进入临时队列");
							Thread.sleep(1000);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			},"线程1").start();
			
			//入队
			new Thread(new Runnable() {		
				@Override
				public void run() {
					try {
						while(true) {
							int temp=tempReuse.take();
							testReuse.put(temp);
							System.out.println("临时队列222 出队 "+temp+" 进入测试队列");
							Thread.sleep(1500);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			},"线程2").start();
		
	}
	
	
	
	
}
