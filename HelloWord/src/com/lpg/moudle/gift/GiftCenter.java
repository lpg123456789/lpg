package com.lpg.moudle.gift;

import java.util.HashMap;
import java.util.Map;

/**
 * 不同的gift判断不一样
 * @author lpg
 * @date 2018年8月3日 
 */
public class GiftCenter {

	public static Map<Integer, AGift> myMap=new HashMap<>();
	
	static {
		myMap.put(GiftType.newPlayerGift, NewPlayerGift.instance);
	}
	
	public static void main(String[] args) {
		long userId=1;
		String giftId="12";
		AGift gift=myMap.get(GiftType.newPlayerGift);
		gift.canGetGift(userId, giftId);
	}
}
