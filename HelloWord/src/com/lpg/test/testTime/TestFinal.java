package com.lpg.test.testTime;

import io.netty.util.Timeout;
import io.netty.util.TimerTask;

public class TestFinal {
	
	private static TestFinal instance;
	public static TestFinal getInstance() {
		if (null != instance) {
			return instance;
		}
		return instance = new TestFinal();
	}
	
	public static void main(String[] args) {
		TimerTask t1=new TaskOne("me");
		System.out.println(System.currentTimeMillis());
		MyHashedWheelTimer.getInstance().newTimeout(t1, 20);
		TimerTask t2=new TaskOne("you");
		MyHashedWheelTimer.getInstance().newTimeout(t2, 10);
	}
	
	public void one(String one) {
		System.out.println("one到时间打印 "+one);
		System.out.println(System.currentTimeMillis());
	}
	
	public void two(int two) {
		System.out.println("two到时间打印 "+two);
		System.out.println(System.currentTimeMillis());
	}
	
}

class TaskOne implements TimerTask{
	
	public String taskOne;
	
	public TaskOne(String one) {
		this.taskOne=one;
	}
	
	@Override
	public void run(Timeout arg0) throws Exception {	
		TestFinal.getInstance().one(taskOne);
	}
	
}

class TaskTwo implements TimerTask{
	
	public int taskTwo;
	
	public TaskTwo(int two) {
		this.taskTwo=two;
	}

	@Override
	public void run(Timeout arg0) throws Exception {	
		TestFinal.getInstance().two(taskTwo);
	}
	
}

