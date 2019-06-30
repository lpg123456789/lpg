package com.lpg.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;


public class Test {

	private static final float MOVE_BASE_SPEED = 1000f;
	private static final int MOVE_BASE_TIME = 288;
	
	/**
	 * 测试时区
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("测试");
		
		Map<Integer, Cat> map = new ConcurrentHashMap<Integer, Cat>();
		
		
		
		for (int i = 1; i <=2000 ; i++) {
			
			Cat c=new Cat("猫"+i);
			map.put(i, c);
		}
		
//		
//		Cat c1=new Cat("猫"+1);
//		Cat c2=new Cat("猫"+2);
//		Cat c3=new Cat("猫"+3);
//		Cat c4=new Cat("猫"+4);
//		
//		Cat c5=new Cat("猫"+5);
//		
//		Cat c6=new Cat("猫"+6);
//		Cat c7=new Cat("猫"+7);
//		Cat c8=new Cat("猫"+8);
//		map.put(1, c1);
//		map.put(2, c2);
////		
////		map.put(3, c3); // 没有第三名，因为机器人没生成
////		
////		
//		map.put(4, c4);
////		map.put(5, c5);
//////		
////		map.put(6, c6);
//		map.put(8, c8);
//		map.put(7, c7);
//		
		
		//4
		
		for (Integer key : map.keySet()) {
			System.out.println(key+" 值"+map.get(key).name);
		}
		
//		System.out.println(map.get(8));
//		System.out.println(map.size());
		
		
		//防止出现错误
//		for (int key = 0; key <= map.size();key++) {
//			Cat cat=map.get(key);
//			if(cat!=null) {
//				System.out.println("键"+key);	
//			}	
//		}
		
		//4名挑战第2名
		
		test(999, 2, map);
		 
	}
	
	public static void test2(int myRankPos,int challengRank,Map<Integer,Cat> map) {
		
		System.out.println("**************************");
		// 倒序
		long begin=System.currentTimeMillis();
		
		Cat tempCat = map.get(myRankPos);
		//HashSet<Integer> record=new HashSet<>();
		for (int i = myRankPos-1; i >= challengRank; i--) {
			Cat cat = map.remove(i);
			if(cat==null) {
				continue;
			}
			map.put(i + 1, cat);
			//record.remove(i + 1);
		}
		map.put(challengRank, tempCat);
		long end=System.currentTimeMillis();
		
		

		for (Integer key : map.keySet()) {
			System.out.println(key+" 值"+map.get(key).name);
		}
		
		System.out.println("*******************"+(end-begin)+"**"+map.size());
	}
	
	public static void test(int myRankPos,int challengRank,Map<Integer,Cat> map) {
		// 倒序
		long begin=System.currentTimeMillis();
		
		Cat tempCat = map.get(myRankPos);
		HashSet<Integer> record=new HashSet<>();
		for (int i = myRankPos-1; i >= challengRank; i--) {
			Cat cat = map.get(i);
			if(cat==null) {
				record.add(i+1);
				continue;
			}
			map.put(i + 1, cat);
			//record.remove(i + 1);
		}
		map.put(challengRank, tempCat);
		for (Integer integer : record) {
			map.remove(integer);
		}
//		
		long end=System.currentTimeMillis();
		
		

		for (Integer key : map.keySet()) {
			System.out.println(key+" 值"+map.get(key).name);
		}
		
		System.out.println("*******************"+(end-begin)+"**"+map.size());
	}
	
	
	public static class Cat{
		
		public Cat(String name) {
			this.name=name;
		}
		
		public String name;
		
	}
}
