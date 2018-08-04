package com.lpg.thread.deadLock;

/**
 * 动态死锁
 * 
 * @author lpg
 * @date 2018年8月4日
 */
public class DeadLockTwo {

	public void transferMoney(Account a, Account b) {
		
		synchronized (a) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (b) {
				System.out.println("线程没锁住 " + Thread.currentThread().getName() + "打印信息");
			}
		}
		
	}

	public static void main(String[] args) {

		Account a = new Account();
		Account b = new Account();
		DeadLockTwo d = new DeadLockTwo();

		new Thread(new Runnable() {
			@Override
			public void run() {
				d.transferMoney(a, b);
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				d.transferMoney(b, a);
			}
		}).start();

		d.transferMoney(a, b);
		d.transferMoney(b, a);
		
	}
}
