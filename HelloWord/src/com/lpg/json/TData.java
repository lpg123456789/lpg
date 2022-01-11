package com.lpg.json;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * lpg
 * 2021年8月13日
 */
public class TData {

	public final Map<String,String> tempMap=new ConcurrentHashMap<String,String>();
	
	public ConcurrentHashMap<String,String> concurrentHashMap=new ConcurrentHashMap<String,String>();
	
	public int a=0;

	public Map<String, String> getTempMap() {
		return tempMap;
	}

	public Map<String, String> getConcurrentHashMap() {
		return concurrentHashMap;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}
	
}
