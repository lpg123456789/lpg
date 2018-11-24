package com.lpg.thread.testError;

import java.util.HashMap;
import java.util.Map;

public class MultiThread {

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
				synchronized (d) {
					d.printAge();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					d.printAge();
				}

			}
		}).start();

		// 该线程改
		new Thread(new Runnable() {
			@Override
			public void run() {
				data d = hm.get(1);
				synchronized (d) {
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
			}
		}).start();

	}

}
