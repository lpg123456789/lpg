package com.lpg.test.testTryCatch;

public class MyTryCatch {

	public static void main(String[] args) {
		int i=test();
		System.out.println(i);
	}
	
	public static int test() {
		try {
			int a=1/0;
			return 1;
		} catch (Exception e) {
			return 2;
		}finally {
			System.out.println("aaaaaaaaaaa");
		}
	}
}
