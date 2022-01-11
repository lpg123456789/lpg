package com.lpg.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * lpg
 * 2021年8月13日
 */
public class Test {
	
	

	public static void main(String[] args) {
		test();
	}
	
	public static void test() {
		
		Gson gson=new Gson();
		
		ConcurrentHashMap<String, String> map=new ConcurrentHashMap<String, String>();
		map.put("11", "111");
		String jsonString=JsonUtil.toJson(map);
		System.out.println(jsonString);
	
		System.out.println("*************************jackson");
		//jackson序列化
		try {
			Map map1=JsonUtil.toObject(jsonString, new TypeReference<Map<String, String>>(){});
			System.out.println("map "+map1.getClass());
			
			Map map2=JsonUtil.toObject(jsonString, new TypeReference<HashMap<String, String>>(){});
			System.out.println("HashMap "+map2.getClass());
			
			Map map3=JsonUtil.toObject(jsonString, new TypeReference<ConcurrentHashMap<String, String>>(){});
			System.out.println("ConcurrentHashMap "+map3.getClass());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("*************************fastJson");
		//fastJson
		try {
			Map map1=JSON.parseObject(jsonString,new com.alibaba.fastjson.TypeReference<Map<String, String>>(){});
			System.out.println("map "+map1.getClass());
			
			Map map2=JSON.parseObject(jsonString, new com.alibaba.fastjson.TypeReference<HashMap<String, String>>(){});
			System.out.println("HashMap "+map2.getClass());
			
			Map map3=JSON.parseObject(jsonString,new com.alibaba.fastjson.TypeReference<ConcurrentHashMap<String, String>>(){});
			System.out.println("ConcurrentHashMap "+map3.getClass());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("*************************gson");
		//gson
		try {
			Map map1=gson.fromJson(jsonString,new TypeToken<Map<String, String>>(){}.getType());
			System.out.println("map "+map1.getClass());
			
			Map map2=JSON.parseObject(jsonString,new TypeToken<HashMap<String, String>>(){}.getType());
			System.out.println("HashMap "+map2.getClass());
			
			Map map3=JSON.parseObject(jsonString,new TypeToken<ConcurrentHashMap<String, String>>(){}.getType());
			System.out.println("ConcurrentHashMap "+map3.getClass());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
