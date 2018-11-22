package com.lpg.test.testTime;

import java.util.concurrent.TimeUnit;

import io.netty.util.HashedWheelTimer;
import io.netty.util.TimerTask;

public class MyHashedWheelTimer {

	private static MyHashedWheelTimer instance;
	public static MyHashedWheelTimer getInstance() {
		if (null != instance) {
			return instance;
		}
		return instance = new MyHashedWheelTimer();
	}
	
	HashedWheelTimer timer = new HashedWheelTimer();// 时间轮的度刻
	
	/**
	 * 设置任务和时间，以秒为单位
	 * @param task
	 * @param time
	 */
	public void newTimeout(TimerTask task,int time) {
		timer.newTimeout(task, time, TimeUnit.SECONDS);
	}
	
	
}
