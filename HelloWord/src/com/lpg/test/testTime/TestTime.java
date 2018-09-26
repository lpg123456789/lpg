package com.lpg.test.testTime;

import java.util.ArrayList;
import java.util.List;

public class TestTime {

	public static void main(String[] args) {
		int size=1000000;
		List<Integer> list=new ArrayList<>();
		for (int i = 0; i < size; i++) {
			list.add(i);
		}
		
		//1秒=1000毫秒 	1毫秒=1000微秒 		1微秒=1000纳秒
		
		long begin=System.nanoTime();    //纳秒
		int a=test1(list);
		long end=System.nanoTime();
		System.out.println("时间 "+(end-begin)+" a的值"+a);
	}
	
	public static int test1(List<Integer> list) {
		int a=0;
		for (Integer integer : list) {
			a+=integer;
		}
		return a;
	}
}
