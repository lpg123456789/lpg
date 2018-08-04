package com.lpg.string;

public class TestNull {

	public static void main(String[] args) {
		test(null);
	}
	
	public static void test(String str) {
		System.out.println(str);

		//进入== bbbbbb
		if(str=="") {
			System.out.println("进入== aaaaaa");
		}else {
			System.out.println("进入== bbbbbb");
		}
		

		//java.lang.NullPointerException 空指针异常
//		if(str.equals("")) {
//			System.out.println("进入equals aaaaaa");
//		}else {
//			System.out.println("进入equals bbbbbb");
//		}
		
	}
	
}
