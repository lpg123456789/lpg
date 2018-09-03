package com.lpg.test.testOverStep;

/**
 * 测试二进制
 * @author lpg
 * @date 2018年8月31日 
 */
public class testBinary {

	public static void main(String[] args) {
		
		test5();
		
	}
	
	public static void test1() {
		int i = 1;
        //使用包装类的toBinaryString转换成二进制
        System.out.println(Integer.toBinaryString(i));
	}
	
	public static void test2() {
		int a=1&2;
		System.out.println(a);
	}
	
	public static void test3() {
		int a=1|2;
		System.out.println(a);
	}
	
	public static void test4() {
		int a=~0;
		System.out.println(a);
	}
	
	public static void test5() {
		
		System.out.println(3<<2);//3左移2位
	    System.out.println(-3<<2);//-3左移2位

	    System.out.println(6>>2);//6右移2位
	    System.out.println(-6>>2);//-6右移2位
		
	}
	
	
	
}
