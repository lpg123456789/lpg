package com.lpg.thread;

public class MultiThread {

	public static void main(String[] args) {
		
		new Thread(new Runnable() {		
			@Override
			public void run() {
				// TODO Auto-generated method stub
				myPrint();
			}
		}).start();
		myPrint();
		new Thread(new Runnable() {		
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		}).start();
		
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
