package com.lpg.battle.battleOne;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPool {

	
	private static ExecutorService es = Executors.newCachedThreadPool();
	
	/**
	 * 执行线程任务
	 * 
	 * @param command
	 */
	public static void execute(Runnable command) {
		es.execute(command);
	}
	
	
}
