package com.lpg.string;

public class myString {

	public static void main(String[] args) {

		String str1 = "abc";
		String str2 = "abc";
		System.out.println(str1 == str2);
		String str3 = new String("abc");
		System.out.println(str3 == str2);
		System.out.println(str1 == str3.intern());

	}

}
