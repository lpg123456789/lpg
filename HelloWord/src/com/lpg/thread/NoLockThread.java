package com.lpg.thread;

/**
 * 无法锁住
 * 
 * @author lpg 2018年11月10日
 */
public class NoLockThread {
	
	private static Long lock=10L;

	public static void main(String[] args) {

		// 线程1
		new Thread(new Runnable() {
			@Override
			public void run() {
				functionOne();
			}
		}, "线程1").start();

		// 线程2
		new Thread(new Runnable() {
			@Override
			public void run() {
				functionTwo();
			}
		}, "线程2").start();

	}
	
	/**
	 * 
	 */
	public static void functionOne() {
		synchronized (lock) {
			System.out.println("进入functionOne");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("退出functionOne");
		}
	}
	
	
	/**
	 * 
	 */
	public static void functionTwo() {
		synchronized (lock) {
			System.out.println("进入functionTwo");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("退出functionTwo");
		}
	}
	

}
