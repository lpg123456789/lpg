package com.lpg.test.testOperation;

public class TestSymbolTwo {

	public static void main(String[] args) {
		
		//靠着单数和双数
		int a=1213;
		int b=1;
		
		String result1=twoFun(a);
		String result2=twoFun(b);
		
		
		System.out.println("结果是"+(a&b));
		
		
		// >>移位
//		int c=64;
//		System.out.println(c>>1); // c/2
//		System.out.println(c>>2); // c/4
//		int d=3;
//		System.out.println(d<<1); // d*2
//		System.out.println(d<<2); // d*4
		
	}
	
	public static String twoFun(int n){
	     String result = Integer.toBinaryString(n);
	     System.out.println(result);
	     return result;
	}
	
}
