/**
 * admin
 */
package com.lpg.gkTest;

import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.util.Date;

/**
 * lpg
 * 2019年12月18日
 */
public class One {
	
	public static class a {
		
	}
	

	public static void main(String[] args) {

		 Class tc = a.class;
		 int a=tc.getModifiers();
		 String s=Modifier.toString(a);
		System.out.println(s);
		 
		int time = 10000000;
		Date begin = new Date();
		ByteBuffer buffer = ByteBuffer.allocate(2*time);
		for(int i=0;i<time;i++){
		    buffer.putChar('a');
		}
		buffer.flip();
		for(int i=0;i<time;i++){
		    buffer.getChar();
		}
		Date end = new Date();
		System.out.println(end.getTime()-begin.getTime());
		begin = new Date();
		ByteBuffer buffer2 = ByteBuffer.allocateDirect(2*time);
		for(int i=0;i<time;i++){
		    buffer2.putChar('a');
		}
		buffer2.flip();
		for(int i=0;i<time;i++){
		    buffer2.getChar();
		}
		end = new Date();
		System.out.println(end.getTime()-begin.getTime());
		
	}
	
}
