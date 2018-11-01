package com.lpg.test.testMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * map无序：指的是put和get得到的顺序不一样
 * @author lpg
 * 2018年11月1日
 */
public class TestMap {

	public static void main(String[] args) {
		c();
	}
	
	
	public static void a() {
		Map<Integer,Integer> map=new HashMap<>();
		for (int i = 0; i < 1000; i++) {
			map.put(i, i);
		}	
		for(Integer key:map.keySet()) {
			System.out.println(key);
		}	
	}
	
	public static void c() {
		Map<Integer,Integer> map=new HashMap<>();
		for (int i = 0; i < 10; i++) {
			map.put(i, i);
		}
		for (int i = 40; i < 100; i++) {
			map.put(i, i);
		}
		for (int i = 10; i <= 40; i++) {
			map.put(i, i);
		}
		for(Integer key:map.keySet()) {
			System.out.println(key);
		}	
	}
	
	
	public static void b() {
		Map<Integer,Integer> map=new ConcurrentHashMap<>();
		for (int i = 0; i < 1000; i++) {
			map.put(i, i);
		}	
		for(Integer key:map.keySet()) {
			System.out.println(key);
		}	
	}
	
}
