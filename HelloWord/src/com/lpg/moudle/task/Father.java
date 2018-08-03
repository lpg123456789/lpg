package com.lpg.moudle.task;

import java.util.HashMap;
import java.util.Map;

public abstract class Father {
	
	public abstract void getArgs(User user,Object... args);
		
	public abstract int getType();
		
	public static Map<Integer ,Father> map=new HashMap<>();
	
	static{
		map.put(1, new C1());
		map.put(2, new C2());
	}
	
	public static void main(String[] args) {
		doSomeThing(1);
	}
	
	public static void doSomeThing(int type) {
		User user =new User();
		map.get(type).getArgs(user);
	}
	
}