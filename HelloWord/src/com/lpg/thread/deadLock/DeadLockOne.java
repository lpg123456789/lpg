package com.lpg.thread.deadLock;

public class DeadLockOne {

	private final Object left = new Object();
	private final Object right = new Object();

	public static void main(String[] args) {
		DeadLockOne d = new DeadLockOne();
		new Thread(new Runnable() {
			@Override
			public void run() {
				d.leftRight();
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				d.Rightleft();
			}
		}).start();
	}

	public void leftRight() {
		synchronized (left) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (right) {
				System.out.println("aaaaaaaaaa");
			}
		}
	}

	public void Rightleft() {
		synchronized (right) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (left) {
				System.out.println("bbbbbbbbbbb");
			}
		}
	}

}
