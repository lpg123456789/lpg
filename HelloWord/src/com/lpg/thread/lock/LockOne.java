package com.lpg.thread.lock;

/**
 * 一般来说，我们可以锁Integer，
 * 但是Integer类型可能其它地方被使用了，所以将Integer转了一下，转成
 * @author lpg
 * 2018年11月12日
 */
public class LockOne {
		
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
	 * 常量池一开始只会创建 -128到127，所以1233434不能实现 同步   	1233434创建后也不能放进去常量池
	 *  127可以实现同步，因为该值一开始就创建了
	 * 打印两次
	 */
	public static void myPrint() {
		Integer hashCode=127;
		synchronized (hashCode) {
			for (int i = 0; i <3000; i++) {
				System.out.println("线程名 "+Thread.currentThread().getName()+" "+i);
			}
		}
	}
	
	
}
