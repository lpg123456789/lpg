package com.lpg.test.testTime;

import java.util.concurrent.TimeUnit;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

public class TestDelay {

	public static void main(String[] args) {
		test2();
	}
	
	public static void test1(){
		System.out.println(System.currentTimeMillis());
		HashedWheelTimer timer = new HashedWheelTimer();// 时间轮的度刻
		TimerTask task = new TimerTask() {
			@Override
			public void run(Timeout timeout) throws Exception {
				System.out.println("任务执行");
				System.out.println(System.currentTimeMillis());
			}
		};
		timer.newTimeout(task, 10, TimeUnit.SECONDS);
	}
	
	public static void test2(){
		System.out.println(System.currentTimeMillis());
		HashedWheelTimer timer = new HashedWheelTimer();// 时间轮的度刻
		TimerTask task = new TimerTask() {
			@Override
			public void run(Timeout timeout) throws Exception {
				TestDelay.me();
				System.out.println(System.currentTimeMillis());
			}
		};
		timer.newTimeout(task, 10, TimeUnit.SECONDS);
	}
	
	public static void me() {
		System.out.println("aaaaaaaaaaaaaaaa");
	}
}
