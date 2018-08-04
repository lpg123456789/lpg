package com.lpg.thread.deadLock;

public class DealDeadLockTwo {
	
	public void transferMoney(Account a, Account b) {
		synchronized (a) {
			// 模拟测试
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

	public void change(Account a, Account b) {

		int fromHash = System.identityHashCode(a);
		int toHash = System.identityHashCode(b);

		if (fromHash > toHash) {
			synchronized (a) {
				synchronized (b) {
					transferMoney(a, b);
				}
			}
		} else if (fromHash < toHash) {
			synchronized (b) {
				synchronized (a) {
					transferMoney(a, b);
				}
			}
		} else {
			synchronized (DealDeadLockTwo.class) {
				synchronized (a) {
					synchronized (b) {
						transferMoney(a, b);
					}
				}
			}
		}
	}

	public static void main(String[] args) {

		Account a = new Account();
		Account b = new Account();
		DealDeadLockTwo d = new DealDeadLockTwo();

		new Thread(new Runnable() {
			@Override
			public void run() {
				d.change(a, b);
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				d.change(b, a);
			}
		}).start();
	}
	
}
