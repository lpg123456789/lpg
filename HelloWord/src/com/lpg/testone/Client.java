/**
 * admin
 */
package com.lpg.testone;

import java.util.Random;

/**
 * lpg
 * 2019年12月14日
 */
public class Client {

	 public static void main(String[] args) {
	        Random r = new Random(111000);
	        for (int i = 1; i < 4; i++) {
	            System.out.println("第" + i + "次：" + r.nextInt());
	        }
	    }
}
