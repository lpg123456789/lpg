package com.lpg.test.testTime;

import java.util.concurrent.TimeUnit;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

public class TestDelayTwo {
	
	public static TestDelayTwo two=new TestDelayTwo();
	
	public static TestDelayTwo getInstance() {
		return two;
	}

	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		HashedWheelTimer timer = new HashedWheelTimer();// 时间轮的度刻
		TimerTask task = new myTimerTask();
		timer.newTimeout(task, 20, TimeUnit.SECONDS);	
	}

	public void test2(){
		System.out.println("TestDelayTwo测试");
	}

}

class myTimerTask implements TimerTask{

	@Override
	public void run(Timeout arg0) throws Exception {	
		TestDelayTwo.getInstance().test2();
		System.out.println(System.currentTimeMillis());
	}
	
}
