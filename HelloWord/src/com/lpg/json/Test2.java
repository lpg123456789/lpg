package com.lpg.json;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;

/**
 * lpg
 * 2021年8月13日
 */
public class Test2 {

	public Map<String, String> map1=new HashMap<String, String>();
	
	public Map<String, String> map2=new ConcurrentHashMap<String, String>();
	
	public TData tData=new TData();
	
	public static void main(String[] args) {
		
		Test2 test=new Test2(); 
		test.doSomeThing2();
	}
	
	
	public void doSomeThing2() {
		
		Gson gson=new Gson();
		
		String jsonString=JsonUtil.toJson(tData);
		System.out.println(jsonString);
		
		//jackson
		
		System.out.println("*******************jackson");
		
		try {
			tData = JsonUtil.toObject(jsonString, TData.class);
			System.out.println(tData.getTempMap().getClass());
			System.out.println(tData.getConcurrentHashMap().getClass());	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("*******************fastjson");
		
		//fastjson
		tData = JSON.parseObject(jsonString, TData.class);
		System.out.println(tData.getTempMap().getClass());
		System.out.println(tData.getConcurrentHashMap().getClass());
		
		System.out.println("*******************gson");
		//gson
		tData = gson.fromJson(jsonString, TData.class);
		System.out.println(tData.getTempMap().getClass());
		System.out.println(tData.getConcurrentHashMap().getClass());
	}
	
	public void doSomeThing() {
		ConcurrentHashMap<String, String> map=new ConcurrentHashMap<String, String>();
		map.put("11", "111");
		String jsonString=JsonUtil.toJson(map);
		System.out.println(jsonString);
		
		try {
			map1 = JsonUtil.toObject(jsonString, new TypeReference<Map<String, String>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("map1 "+map1.getClass());
		
		
		try {
			map2 = JsonUtil.toObject(jsonString, new TypeReference<ConcurrentHashMap<String, String>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("map2 "+map2.getClass());
		
	}
	
	
	
}
