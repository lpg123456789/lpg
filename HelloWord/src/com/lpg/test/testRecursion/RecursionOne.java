package com.lpg.test.testRecursion;

public class RecursionOne {

	
	public static void main(String[] args) {
		
		test(10);
		
	}
	
	public static void test(int i){
		i--;
		
		System.out.println("beign "+i);
		if(i<=0){
			return;
		}else{
			test(i);
		}
		
		System.out.println("end "+i);
	
	}
	
}
