package com.lpg.observer;

public class Main {
	
	public static void main(String[] args) {
		
		 Subject subject=new Subject();
		 LogObservers observer=new LogObservers(); 
		 MessageObservers observers=new MessageObservers();
		 
		 subject.addObservers(observer);
		 subject.addObservers(observers);
		 
		 subject.change("ok");	 
	}

}