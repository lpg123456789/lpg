package com.lpg.thread;

/**
 * 双重sleep
 * @author lpg
 * 2018年12月19日
 */
public class ThreadSleep {

	public static void main(String[] args) {
		long beign = System.currentTimeMillis();
		System.out.println("开始时间 " + beign);
		while (true) {
			System.out.println("线程     "+Thread.currentThread().getName()+"  mainmainmainmainmainmain " + System.currentTimeMillis());
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			test();
		}
	}
	
	public static void test() {
		System.out.println("线程     "+Thread.currentThread().getName()+"    testtesttesttesttest  "+System.currentTimeMillis());
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
