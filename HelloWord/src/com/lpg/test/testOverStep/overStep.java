package com.lpg.test.testOverStep;

/**
 * 数组越界
 * @author lpg
 * @date 2018年8月31日 
 */
public class overStep {

	public static void main(String[] args) {
				
		//byte b=(byte) -129; 	//-127~127
		//System.out.println(b);
		
		//short s=32768;	//-32768~32767
		//System.out.println(s);
		
		//int 4个字节  32位     2的32次方是 4294967296    -2147483648~2147483647
		int i=2147483647;
		System.out.println(i);
		
	}
	
}
