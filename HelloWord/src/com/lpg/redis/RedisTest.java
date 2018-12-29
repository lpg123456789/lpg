package com.lpg.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * redis测试java
 * @author lpg
 * 2018年12月29日
 */
public class RedisTest {

	public static void main(String[] args) {
		testTwo();
	}
	
	
	public static void testOne() {
		// 连接本地的 Redis 服务
		Jedis jedis = new Jedis("localhost");
		System.out.println("连接成功");
		// 设置 redis 字符串数据
		jedis.set("runoobkey", "www.runoob.com");
		// 获取存储的数据并输出
		System.out.println("redis 存储的字符串为: " + jedis.get("runoobkey"));
	}
	
	/**
	 * 测试排序
	 */
	public static void testTwo() {
		Jedis jedis = new Jedis("localhost");
		System.out.println("连接成功");
		
		jedis.zadd("mysort", 1.0, "三国演义");
		jedis.zadd("mysort", 2.0, "西游记");
		jedis.zadd("mysort", 3.0, "水浒传");
		jedis.zadd("mysort", 4.0, "红楼梦");
		Map<String, Double> map = new HashMap<>();
		map.put("mutouliu", 5.0);
		jedis.zadd("mysort", map);
		// 正序排列
//		Set<String> mysort = jedis.zrange("mysort", 0, -1);
//		System.out.println(mysort);
		// 倒序排列
		Set<String> mysort1 = jedis.zrange("mysort", -1, 0);
		System.out.println(mysort1);
		
	}
}
