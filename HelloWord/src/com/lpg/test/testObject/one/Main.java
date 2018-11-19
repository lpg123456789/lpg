package com.lpg.test.testObject.one;

public class Main {

	public static void main(String[] args) {
		
		MyObject myObject=new MyObject();	
		
		System.out.println(myObject.toString());
		
		ClassOne one=new ClassOne(myObject);
		one.calc();
		one.print();
		
		ClassTwo two=new ClassTwo(myObject);
		two.calc();
		two.print();
		
		ClassThree three=new ClassThree(myObject);
		three.calc();
		three.print();
		
	}
	
}
