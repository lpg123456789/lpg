package com.lpg.exception;

/**
 * 下面的异常被捕获后上面的异常就捕获不到了
 * @author lpg
 * @date 2018年8月27日 
 */
public class TwoException {

	
	public static void main(String[] args) {
		try {
			TwoException.testTryCatch("aaa");
		} catch (Exception e) {
			System.out.println("异常信息");
			e.printStackTrace();
		}
	}
	
	public static void testTryCatch(String str) {
		System.out.println(str);
		try {
			throw new Exception("丢异常");
		} catch (Exception e) {
			System.out.println("异常在这里");
			e.printStackTrace();
		}
		System.out.println("最美的时光test");
	}
}
