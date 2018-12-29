package com.lpg.test;

import java.util.HashMap;
import java.util.Map;

public class Test3 {

	public static void main(String[] args) {
		
		int cx=0;
		int cy=0;
		
		int x=3;
		int y=3;
		
		double sin = Math.sin(45);
		double cos = Math.cos(45);
		
		double dx = (x - cx) * cos - (y - cy) * sin + cx;
		double dy = (y - cy) * cos + (x - cx) * sin + cy;
		
		int nx = (int) Math.round(dx);
		int ny = (int) Math.round(dy);
		
		System.out.println("x值"+dx+"  ,"+"y值"+dy);
		
		
		Map<Integer,Integer> map=new HashMap<>();
		for (int i = 0; i < 100; i++) {
			map.put(i, i);
		}
		
		for(Integer key:map.keySet()) {
			System.out.println(key);
		}
		
	}
	
}
