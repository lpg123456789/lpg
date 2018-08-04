package com.lpg.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {

	private  List<ITicketObserver> list = new ArrayList<>();
	
	public void addObservers(ITicketObserver object) {
		list.add(object);
	}
	
	public void removeObservers(ITicketObserver object) {
		list.remove(object);
	}
	
	public void change(String state) {
		System.out.println("观察者改变了！");
		for(ITicketObserver object : list ) {
			object.doWork(state);
		}
	}
}
