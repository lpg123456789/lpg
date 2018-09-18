package com.lpg.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * 概率工具类
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年1月25日 下午6:03:22
 */
public class RateUtils {
	
	/**
	 * 检测是否命中
	 * 
	 * @param rate 概率
	 * @param max 概率上限
	 * @return
	 */
	public static boolean hit(double rate, double max) {
		if(rate == 0) {
			return false;
		}
		
		boolean flag = true;
		if(rate < max) {
			double i = Math.round(Math.random() * max);
			flag = i <= rate;
		}
		return flag;
	}
	
	/**
	 * 获取得某个范围内的随机数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int rangeRandom(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}
	
	/**
	 * 获取随机的刷新范围
	 * 
	 * @param base
	 * @param range
	 * @return
	 */
	public static int baseRandom(int base, int range) {
		if (range == 0) {
			return base;
		}
		return base + rangeRandom(-range, range);
	}

	
	/**
	 * 获取数组中的随机几个值
	 * 
	 * @param array
	 * @param num
	 * @return
	 */
	public static Object[] getRandomElements(Object[] arr, int num) {
		if (num >= arr.length) {
			return arr;
		}
		// 下标
		int endIndex = arr.length - 1;
		// 随机一个下标
		int randIndex = rangeRandom(0, endIndex);
		// 已随机下标为起点获取连续num值，如果下标大于length，那么小标从0开始
		ArrayList<Object> tlist = new ArrayList<Object>();
		// 最大下标
		int t = randIndex + num - 1;
		if (t > endIndex) {
			for (int i = randIndex; i <= endIndex; i++) {
				tlist.add(arr[i]);
			}
			for (int j = 0; j < t - endIndex; j++) {
				tlist.add(arr[j]);
			}
		}
		else {
			for (int i = randIndex; i <= t; i++) {
				tlist.add(arr[i]);
			}
		}
		return tlist.toArray();
	}

	/**
	 * 获取最大最小值之间不重复的几个数值
	 * 
	 * @param min
	 * @param max
	 * @param num
	 * @return
	 */
	public static Integer[] getRandomInteger(int min, int max, int num) {
		int n = max - min;
		if (n == 0) {
			return new Integer[] { min };
		}
		if (n < 0 || n < num) {
			return new Integer[0];
		}
		HashSet<Integer> numSet = new HashSet<>();
		while (numSet.size() < num) {
			numSet.add(rangeRandom(min, max));
		}
		return numSet.toArray(new Integer[] { numSet.size() });
	}

	/**
	 * 根据 权重 获取得到的物品
	 * 
	 * @param rateMap key data value 各项权重的值,必须为>0
	 * @return
	 */
	public static Object getRandomByRate(Map<Object, Integer> rateMap) {
		int rateBase = 0;
		TreeMap<Integer, Object> shotMap = new TreeMap<>();

		for (Object obj : rateMap.keySet()) {
			Integer value = rateMap.get(obj);
			rateBase += value;
			shotMap.put(rateBase, obj);
		}
		
		// 取随机数
		int nextInt = new Random().nextInt(rateBase);
		Integer ceilingKey = shotMap.ceilingKey(nextInt);
		return shotMap.get(ceilingKey);
	}
}
