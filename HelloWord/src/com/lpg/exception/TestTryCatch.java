package com.lpg.exception;

public class TestTryCatch {
	
	public static void main(String[] args) {
		//test(null);
		testTryCatch(null);
	}

	public static void test(String str) {
		System.out.println(str);
		if (str.equals("")) {
			System.out.println("进入equals aaaaaa");
		} else {
			System.out.println("进入equals bbbbbb");
		}
		System.out.println("最美的时光test");

	}

	public static void testTryCatch(String str) {
		System.out.println(str);
		try {
			if (str.equals("")) {
				System.out.println("进入equals aaaaaa");
			} else {
				System.out.println("进入equals bbbbbb");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("最美的时光test");
	}
	
}
