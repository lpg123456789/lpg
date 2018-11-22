package com.lpg.thread.lock;

public class LockThree {

	public static void main(String[] args) {

		Integer hashCode = 1233434;
		System.out.println(hashCode);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				myPrint();
			}
		}, "线程1").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				myPrint();
			}
		}, "线程2").start();

	}
	
	/**
	 *
	 * @param id
	 * @return
	 */
	private synchronized static String getRoomId(int id) {
		return "test_"+id;
	}
	

	/**
	 * 常量池一开始只会创建 -128到127，
	 * 所以1233434不能实现 同步 127可以实现同步，
	 * 因为该值一开始就创建了 打印两次
	 */
	public static void myPrint() {
		String roomInfo=getRoomId(1);
		synchronized (roomInfo) {
			for (int i = 0; i < 3000; i++) {
				System.out.println("线程名 " + Thread.currentThread().getName() + " " + i);
			}
		}
	}

}
