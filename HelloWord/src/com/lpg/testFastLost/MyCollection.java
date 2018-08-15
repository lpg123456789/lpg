package com.lpg.testFastLost;

import java.util.ArrayList;
import java.util.List;

public class MyCollection {

	public static void main(String[] args) {

		final List<Integer> list = new ArrayList<>();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (Integer i = 0; i < 1000; i++) {
					System.out.println("移除数据 " + i);
					list.remove(i);
				}
			}
		}).start();
		

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					System.out.println("增加数据 " + i);
					list.add(i);
				}
			}
		}).start();

//		try {
//			Thread.sleep(3);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("list信息是 " + list);
	}
}
