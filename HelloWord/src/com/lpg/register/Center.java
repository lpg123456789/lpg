package com.lpg.register;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册父子类
 * @author lpg
 * @date 2018年8月4日 
 */
public class Center {

	public static Map<Integer, FatherType> huPaiCacheMap = new HashMap<>();

	// 获取
	public static FatherType get(Integer otherId) {
		if (!huPaiCacheMap.containsKey(otherId)) {
			throw new RuntimeException("AHuPaiType没有改注册  :" + otherId);
		}
		return huPaiCacheMap.get(otherId);
	}

	// 注册
	public static FatherType reg(Integer otherId, FatherType fatherType) {
		if (huPaiCacheMap.containsKey(otherId)) {
			throw new RuntimeException("fatherType类注册重复  :" + otherId);
		}
		return huPaiCacheMap.put(otherId, fatherType);
	}
}
