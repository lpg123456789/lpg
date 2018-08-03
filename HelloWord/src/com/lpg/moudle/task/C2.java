package com.lpg.moudle.task;

public class C2 extends Father {

	@Override
	public void getArgs(User user,Object... args) {
		System.out.println("c2 "+args[0]);
	}

	@Override
	public int getType() {
		return 2;
	}

}
