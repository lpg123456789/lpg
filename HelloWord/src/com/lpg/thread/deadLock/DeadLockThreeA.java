package com.lpg.thread.deadLock;

/**
 * 相互引用引发死锁
 * @author lpg
 * @date 2018年8月4日 
 */
public class DeadLockThreeA {

	private DeadLockThreeB two;

	public synchronized void oneThreadA() {
		System.out.println("oneThreadA");
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		two.twoThreadA();
	}
	
	public synchronized void oneThreadB() {
		System.out.println("oneThreadB");
	}
	
	
	public DeadLockThreeB getTwo() {
		return two;
	}

	public void setTwo(DeadLockThreeB two) {
		this.two = two;
	}

	public static void main(String[] args) {
		
		DeadLockThreeA one=new DeadLockThreeA();
		DeadLockThreeB two=new DeadLockThreeB();
		one.setTwo(two);
		two.setOne(one);
		
		new Thread(new Runnable() {		
			@Override
			public void run() {
				// TODO Auto-generated method stub
				one.oneThreadA();
			}
		}).start();
		
		new Thread(new Runnable() {		
			@Override
			public void run() {
				// TODO Auto-generated method stub
				two.twoThreadB();
			}
		}).start();	
	}
	
}
