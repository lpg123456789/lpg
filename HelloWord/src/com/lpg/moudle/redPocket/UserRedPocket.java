package com.lpg.moudle.redPocket;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserRedPocket {
	
	/**
	 * key：红包id value钻石数值
	 */
	private Map<Integer,Integer> diamondMap=new HashMap<>();
	
	/**
	 * 总砖石
	 */
	private int totalDiamond=0;
	
	public void addData(int id,int diamond){	
		diamondMap.put(id, diamond);
		totalDiamond+=diamond;
	}

	public int getTotalDiamond() {
		return totalDiamond;
	}
	
	public int getNum() {
		return diamondMap.size();
	}

	public Map<Integer, Integer> getDiamondMap() {
		return diamondMap;
	}
	
	public Set<Integer> getMapSet(){
		return diamondMap.keySet();
	}
	
	

}
