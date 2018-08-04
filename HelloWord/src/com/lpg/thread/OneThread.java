package com.lpg.thread;

public class OneThread {

	public static void main(String[] args) {
		
		long begin=System.currentTimeMillis();
		myPrint();
		myPrint();
		long end=System.currentTimeMillis();
		System.out.println("运行时间 "+(end-begin));
		
	}
	
	/**
	 * 打印两次
	 */
	public static void myPrint() {
		for (int i = 0; i <1000; i++) {
			System.out.println("线程名 "+Thread.currentThread().getName()+" "+i);
		}
	}
}
