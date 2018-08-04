package com.lpg.thread.threadTest;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发不保证多个原子状态变量的同一状态
 * @author lpg
 * @date 2018年8月4日 
 */
public class Counter {

	// public static int count = 0;
	public static AtomicInteger count = new AtomicInteger(0);
	public static AtomicInteger result = new AtomicInteger(0);

	public static void inc() {
		try {
			Thread.sleep(1);
		} catch (Exception e) {
		}
		int random = new Random().nextInt(1000);
		count.set(random);
		result.set(random);
		if (count.get() != result.get()) {
			System.out.println(Thread.currentThread().getName() + " " + count.get() + " " + result.get());
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Counter.inc();
				}
			}, "线程" + i).start();
		}
		try {
			Thread.sleep(5000); // 休眠5秒，确保线程执行完毕
		} catch (Exception e) {
		}
	}
}
