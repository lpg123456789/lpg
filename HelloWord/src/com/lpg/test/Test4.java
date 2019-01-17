package com.lpg.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test4 {

	
	
	public static void main(String[] args) {
		
		test5();
		
	}
	
	
	public static void test5() {
		List<Integer> list=new ArrayList<>();
		list.add(1);
		list.add(2);
		
		System.out.println(list.iterator().next());
		System.out.println(list.iterator().next());
		System.out.println(list.iterator().next());
		System.out.println(list.iterator().next());
	}
	
	public static void test1() {
		Map<Integer,Integer> map=new HashMap<>();
		for (int i = 0; i <100; i++) {
			map.put(i, i);
		}
		for(Integer key:map.keySet()) {
			System.out.println(key+" "+map.get(key));
		}
	}
	
	public static void test2() {
		Map<Integer,Map<Integer,Integer>> map=new HashMap<>();
		for (int i = 0; i <10; i++) {
			Map<Integer,Integer> temp=map.get(i);
			if(temp==null) {
				temp=new HashMap<>();
				map.put(i, temp);
			}
			for (int j = 0; j <20; j++) {
				temp.put(j, j);
			}
		} 
		//打印结果
		for(Integer key:map.keySet()) {
			System.out.println("键值 "+key+" ");
			Map<Integer,Integer> temp=map.get(key);
			for(Integer key2:temp.keySet()) {
				System.out.println("键值*************** "+key2+" ");
			}
		}
	}
	
	public static void test3() {
		Map<Integer,Map<Integer,Test>> map=new HashMap<>();
		for (int i = 0; i <10; i++) {
			Map<Integer,Test> temp=map.get(i);
			if(temp==null) {
				temp=new HashMap<>();
				map.put(i, temp);
			}
			for (int j = 0; j <20; j++) {
				Test t=new Test();
				temp.put(j, t);
			}
		} 
		//打印结果
		for(Integer key:map.keySet()) {
			System.out.println("键值 "+key+" ");
			Map<Integer,Test> temp=map.get(key);
			for(Integer key2:temp.keySet()) {
				System.out.println("键值*************** "+key2+" ");
			}
		}
	}
	
	public static void test4() {
		
	}
	
}
