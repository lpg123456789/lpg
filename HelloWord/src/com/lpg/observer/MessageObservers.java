package com.lpg.observer;

public class MessageObservers implements ITicketObserver {

	@Override
	public void doWork(String state) {
		 System.out.println("短信操作开始！更新状态"+state);
	}
}
