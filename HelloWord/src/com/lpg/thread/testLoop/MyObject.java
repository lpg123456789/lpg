package com.lpg.thread.testLoop;

public class MyObject {
	
	private int age;
	
	public MyObject(int age) {
		this.age=age;
	}

	public void toMyString() {
		System.out.println("打印 [age=" + age + "]");
	}
	
}
