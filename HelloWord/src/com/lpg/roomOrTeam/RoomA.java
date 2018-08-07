package com.lpg.roomOrTeam;

/**
 * 模拟进入房间的人数多于房间要求人数
 * @author lpg
 * @date 2018年8月4日
 */
public class RoomA {

	public static int pNum = 0;

	public static void inc() {
		try {
			Thread.sleep(1);
		} catch (Exception e) {
		}
		pNum++;
	}

	public static void main(String[] args) {

		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					if (pNum >= 900) {
						System.out.println(pNum);
						return;
					}
					RoomA.inc();
				}
			}).start();
		}

		try {
			Thread.sleep(5000); // 休眠5秒，确保线程执行完毕
		} catch (Exception e) {
		}
		// 这里每次运行的值都有可能不同,可能为1000
		System.out.println("运行结果:pNum=" + pNum);
	}

}
