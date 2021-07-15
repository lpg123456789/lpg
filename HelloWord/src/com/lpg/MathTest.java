/**
 * admin
 */
package com.lpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * lpg
 * 2020年1月9日
 */
public class MathTest {

	
	public static void main(String[] args) {
		
		com.lpg.zone.Zone a=new com.lpg.zone.Zone();
		
		/*	ImmutableMap<String,String> immutableMap1 = ImmutableMap.of("a","a","b","b","c","c","f","f");
			ImmutableMap<String,String> immutableMap2 = ImmutableMap.of("c","c","d","d","e","e","f","f");
		
			MapDifference<String,String> difference = Maps.difference(immutableMap1,immutableMap2);*/
	
		//MapDifference<String,String> difference = Maps.difference(immutableMap1,immutableMap2);
	
		
		/*Map<String,Integer> map1=new HashMap<String, Integer>();
		map1.put("16", 1);
		map1.put("2", 2);
		map1.put("3", 3);
		Map<String,Integer> map2=new HashMap<String, Integer>();
		map2.put("1", 14);
		map2.put("15", 15);
		map2.put("16", 16);
		
		map1.keySet().retainAll(map2.keySet());//交集 只能做一次
		
		map2.forEach((key, value) -> {
		        System.out.println(key + ":" + value);
		    });
		
		
		Test t1=new Test();
		Map<Test,Integer> map=new HashMap<Test, Integer>();
		map.put(t1, 1);*/
		
		
		/* HashMap<String,Integer> map = new HashMap<>();
		    map.put("1",1);
		    map.put("2",2);
		    map.put("3",3);
		    //只对map中存在的key对应的value进行操作
		    Integer integer = map.computeIfPresent("6", (k,v) -> v+4 );
		    Integer integer1 = map.computeIfPresent("4", (k,v) -> {
		        if (v==null)return 0;
		        return v+1;
		    } );
		    System.out.println(integer);
		    System.out.println(integer1);
		    System.out.println(map.toString());*/
	        
		/*  
		  HashMap<String,Integer> map = new HashMap<>();
		  map.put("1",1);
		  map.put("2",2);
		  map.put("3",3);
		  Integer integer = map.compute("3", (k,v) -> v+1 );
		  //key不管存在不在都会执行后面的函数，并保存到map中
		  Integer integer1 = map.compute("5", (k,v) -> {
		      if (v==null)return 1;
		      return v+1;
		  } );
		  System.out.println(integer);
		  System.out.println(integer1);
		  System.out.println(map.toString());*/
	        
	        
		/*  HashMap<String,Integer> map = new HashMap<>();
		  map.put("a", 1);
		  
		  ConcurrentMap<String,Integer> map2=new HashMap<String,Integer>();
		  ConcurrentMap<String,Integer> map3= (ConcurrentMap<String, Integer>) map.clone();
		 System.out.println(map2.put("a", 0));
		  System.out.println(map2.get("a")+" "+map.get("a"));*/
		
		Map<String,Integer> mapaaa = new HashMap<String,Integer>();
		mapaaa.put("a", 1);
        
        Map<String,Integer> mapbbb=new HashMap<String,Integer>();
        mapbbb.putAll(mapaaa);
        
        mapbbb.put("a", 2);
        System.out.println(mapaaa.get("a")+" "+mapbbb.get("a"));

		
	        
	        
		ConcurrentMap<String,Integer> map = new ConcurrentHashMap<String,Integer>();
	        map.put("a", 1);
	        
	        ConcurrentMap<String,Integer> map2=new ConcurrentHashMap<String,Integer>();
	        map2.putAll(map);
	        
	        map2.put("a", 2);
	        System.out.println(map2.get("a")+" "+map.get("a"));

	}
	
}
