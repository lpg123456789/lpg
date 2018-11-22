package com.lpg.thread.lock;

public class LockTwo {

	
	public static void main(String[] args) {
		
		new Thread(new Runnable() {		
			@Override
			public void run() {
				// TODO Auto-generated method stub
				myPrint();
			}
		},"线程1").start();
		
		new Thread(new Runnable() {		
			@Override
			public void run() {
				myPrint();
			}
		},"线程2").start();
		
	}
	
	/**
	 * 打印两次
	 */
	public static void myPrint() {
//		int hashCode=123;
//		Integer a=127;
		synchronized (1+"121212") {
			for (int i = 0; i <1000; i++) {
				System.out.println("线程名 "+Thread.currentThread().getName()+" "+i);
			}
		}
	}
	
	
}
