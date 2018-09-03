package com.lpg.test.testReflect;

public class Logger {

	public void show(){
        System.out.println("hello world");
    }
    
    public int cal(int a,int b){
        return a + b;
    }
    
    public String multi(String... args){
        
        return args.length+";";
    }
	
}
