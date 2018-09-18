package com.lpg.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Test {

	public static void main(String[] args) {
		
		Map<Object, Integer> map=new HashMap<>();
		map.put(1, 50);
		map.put(2, 50);
		
		System.out.println(getRandomByRate(map));
			
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
