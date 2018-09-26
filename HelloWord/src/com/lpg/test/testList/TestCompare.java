package com.lpg.test.testList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestCompare {

	/**
	 * https://www.zhihu.com/question/58735131/answer/307771944 不符合 因为 JVM
	 * 本身有运行时优化，后面运行的效率会提高 可以调换顺序进行测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		test2();
	}

	/**
	 * 测试1
	 */
	public static void test1() {
		// 链表
		// 数组链表
		List<Integer> list = new ArrayList<>(10000);
		for (int i = 0; i < 10000; i++) {
			list.add(i);
		}
		long begin1 = System.nanoTime();
		System.out.println(list.get(9999));
		long end1 = System.nanoTime();
		System.out.println("时间 " + (end1 - begin1));

		// 数组
		int[] a = new int[10000];
		long begin = System.nanoTime();
		System.out.println(a[9999]);
		long end = System.nanoTime();
		System.out.println("时间 " + (end - begin));

		// LinkedList
		List<Integer> list2 = new LinkedList<Integer>();
		for (int i = 0; i < 10000; i++) {
			list2.add(i);
		}
		long begin2 = System.nanoTime();
		System.out.println(list2.get(9999));
		long end2 = System.nanoTime();
		System.out.println("时间 " + (end2 - begin2));
	}
	
	/**
	 * 测试2
	 */
	public static void test2() {
				
		int b=0;
		long begin2 = System.nanoTime();
		for (int i = 0; i < 1000; i++) {
			b++;
		}
		long end2 = System.nanoTime();
		System.out.println("时间  " + (end2 - begin2)+ " " +b);
		
		System.out.println("*******************************");
		
		int a=0;
		long begin1 = System.nanoTime();
		for (int i = 0; i < 1000; i++) {
			a++;
		}
		long end1 = System.nanoTime();
		System.out.println("时间  " + (end1 - begin1)+ " " +a);
		
	}

}
