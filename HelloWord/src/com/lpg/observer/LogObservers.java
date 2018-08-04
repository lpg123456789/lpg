package com.lpg.observer;

public class LogObservers implements ITicketObserver {
	 
		@Override
		public void doWork(String state) {
			 System.out.println("日志文件操作开始！更新状态"+state);
		}
	 
}