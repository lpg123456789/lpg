package com.lpg.testList;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 遍历并且移除
 * 线程调用list，实现到期移除功能
 */
public class Main {

	public static ScheduledExecutorService executors = Executors.newScheduledThreadPool(5);

	/**
	 * 线程安全类
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		List<TestData> testDataList = new CopyOnWriteArrayList<>();

//		long now = System.currentTimeMillis();	
//		TestData testData3 = new TestData("2222222", now + 20000);
//		TestData testData1 = new TestData("1111111", now + 1000);
//		TestData testData2 = new TestData("2222222", now + 2000);
//		testDataList.add(testData3);
//		testDataList.add(testData1);
//		testDataList.add(testData2);
		
		Main main=new Main();
		main.addData(testDataList);
		main.removeData(testDataList);

	}

	private void removeData(List<TestData> testDataList) {
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for (TestData testData : testDataList) {
						if (testData.getTime() < System.currentTimeMillis()) {
							testDataList.remove(testData);
							executors.submit(new MyThread(testData));
						}
					}
				}
			}
		}.start();
	}

	private void addData(List<TestData> testDataList) {
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					long now = System.currentTimeMillis();
					int temp = new Random().nextInt(10) + 1;
					TestData testData = new TestData(temp + " ", now + 2000);
					System.out.println("添加添加添加数据" + testData.getData());
					testDataList.add(testData);
				}
			}

		}.start();
	}

	public static class MyThread extends Thread {

		public TestData testData;

		public MyThread(TestData testData) {
			this.testData = testData;
		}

		@Override
		public void run() {
//			try {
//				// 模拟线程休眠
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			System.out.println("移除数据" + testData.getData());
		}

	}

}
