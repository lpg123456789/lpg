package com.lpg.thread.deadLock;

public class DeadLockThreeB {

	private DeadLockThreeA one;
	
	public synchronized void twoThreadA() {
		System.out.println("twoThreadA");
	}
	
	public synchronized void twoThreadB() {
		System.out.println("twoThreadB");
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		one.oneThreadB();
	}

	public DeadLockThreeA getOne() {
		return one;
	}

	public void setOne(DeadLockThreeA one) {
		this.one = one;
	}
}
