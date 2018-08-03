package com.lpg.moudle.task;

import java.util.HashMap;
import java.util.Map;

/**
 * 不定参解耦系统任务模块
 * @author lpg
 * @date 2018年8月3日 
 */
public class TaskCenter {
	
	/**
	 * Integer 阶段
	 * Map<Integer,Integer> 类型  次数
	 */
	public static Map<Integer ,Map<Integer,Integer>> hm=new HashMap<>();
		
	static {
		
		Map<Integer,Integer> map1=new HashMap<>();
		map1.put(1, 1);//阶段1的任务1需要完成1次
		map1.put(2, 2);//阶段1的任务2需要完成2次
		hm.put(1, map1);//阶段1
		
		Map<Integer,Integer> map2=new HashMap<>();
		map2.put(1, 2);//阶段2的任务1需要完成2次
		map2.put(2, 2);//阶段2的任务2需要完成4次
		
		hm.put(2, map2);
		
	}
	
	public static void isArrive(User user,int taskType) {
		int num=hm.get(user.stage).get(taskType);
	}
	
	
	
}