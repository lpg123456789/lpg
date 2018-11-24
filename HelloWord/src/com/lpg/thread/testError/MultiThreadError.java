package com.lpg.thread.testError;

import java.util.HashMap;
import java.util.Map;

/**
 * 多线程常见(在一条线程执行过程中，另一条线程改了数据)
 * @author lpg 2018年11月23日
 */
public class MultiThreadError {

	public static void main(String[] args) {

		Map<Integer, data> hm = new HashMap<>();
		data d1 = new data(1, 100);
		hm.put(1, d1);
		data d2 = new data(2, 100);
		hm.put(2, d2);

		// 该线程读
		new Thread(new Runnable() {
			@Override
			public void run() {
				data d = hm.get(1);
				d.printAge();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				d.printAge();
			}
		}).start();
		
		// 该线程改
		new Thread(new Runnable() {
			@Override
			public void run() {
				data d = hm.get(1);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				d.printAge();
				d.setAge();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				d.printAge();
			}
		}).start();

	}

}

class data {

	public int id = 0;

	public int age = 0;

	public data(int id, int age) {
		this.id = id;
		this.age = age;
	}

	public void printAge() {
		System.out.println(Thread.currentThread().getName() + " 打印数据 " + age);
	}

	public void setAge() {
		this.age = 1000000;
	}
}
