package com.lpg.test.testShutDown;


public class Main {
	
	public static void main(String[] args) {

		System.out.println("press ENTER to call System.exit() and run the shutdown routine.");
				
		Runtime.getRuntime().addShutdownHook(new ShutDownHook());
		
		System.exit(0);
		
		Main.print();
		
		System.out.println("bb2");
	}
	
	public static void print() {
		
		while(true) {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("内部打印");
			
		}
		
	}

}
