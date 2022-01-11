package com.lpg.jsoncompare;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;


/**
 * lpg
 * 2021年9月8日
 */
public class FastJson {

	public static void main(String[] args) throws Exception {
		a();
		//test_OOM();
	}

	// bug1 下划线自动去掉 $开头不转
	public static void a() throws Exception {
		Obj obj = new Obj();
		obj.set_age("https://jieniyimiao.blog.csdn.net/article/details/89164632?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Edefault-6.no_search_link&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7Edefault-6.no_search_link");
		obj.set$a("bbb");
		obj.setA_b("a_b");

		String aString = JSON.toJSONString(obj);
		System.out.println("fastJson str" + aString);
		Obj fastJsonObj = JSON.parseObject(aString, Obj.class);
		System.out.println("fastJson obj2 " + fastJsonObj.get_age() + " " + fastJsonObj.get$a() + " " + fastJsonObj.getA_b());

		System.err.println("***********");

		/*	String bString = JsonUtil.toJson(obj);
			System.out.println("JackJsonObj str" + bString);
			Obj JackJsonObj = JsonUtil.toObject(bString, Obj.class);
			System.out.println("JackJsonObj obj3 " + JackJsonObj.get_age() + " " + JackJsonObj.get$a() + " " + fastJsonObj.getA_b());
		
			System.err.println("***********");*/

		String cString = new Gson().toJson(obj);
		System.out.println("gson str" + cString);
		Obj gsonObj = new Gson().fromJson(cString, Obj.class);
		System.out.println("gson obj3 " + gsonObj.get_age() + " " + gsonObj.get$a() + " " + fastJsonObj.getA_b());

	}

	// $ref格式
	public static void b() throws Exception {
		Obj obj = new Obj();
		obj.set_age("11xx");
		obj.set$a("bbbxx");
		obj.setA_b("a_bxxx");

		String aString = JSON.toJSONString(obj);
		System.out.println("fastJson str" + aString);
		Obj fastJsonObj = JSON.parseObject("{12345x}", Obj.class);
		System.out.println("fastJson obj2 " + fastJsonObj.get_age() + " " + fastJsonObj.get$a() + " " + fastJsonObj.getA_b());
	}

	public static void c() throws Exception {
		Map<Integer, Obj> map = new HashMap<Integer, Obj>();
		Obj obj = new Obj();
		obj.set_age("11xx");
		obj.set$a("bbbxx");
		obj.setA_b("a_bxxx");
		map.put(1, obj);
		map.put(2, obj);
		//String aString = JSON.toJSONString(map);
		String aString = JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect);
		System.out.println("fastJson str" + aString);
	}

	//fastjson的取值有问题
	public static void d() throws Exception {
		Obj obj = new Obj();
		obj.setTemp(0);
		
		String aString = JSON.toJSONString(obj);
		System.out.println("fastJson str" + aString);
		Obj fastJsonObj = JSON.parseObject(aString, Obj.class);
		System.out.println("fastJson obj2 " + fastJsonObj.get_age() + " " + fastJsonObj.get$a() + " " + fastJsonObj.getA_b()+" "+fastJsonObj.getTemp());

		System.err.println("***********");
		
		/*String bString = JsonUtil.toJson(obj);
		System.out.println("JackJsonObj str" + bString);
		Obj JackJsonObj = JsonUtil.toObject(bString, Obj.class);
		System.out.println("JackJsonObj obj3 " + JackJsonObj.get_age() + " " + JackJsonObj.get$a() + " " + JackJsonObj.getA_b()+" "+JackJsonObj.getTemp());*/
		
		System.err.println("***********");
		
		String cString = new Gson().toJson(obj);
		System.out.println("gson str" + cString);
		Obj gsonObj = new Gson().fromJson(cString, Obj.class);
		System.out.println("gson obj3 " + gsonObj.get_age() + " " + gsonObj.get$a() + " " + gsonObj.getA_b()+" "+gsonObj.getTemp());
		
	}

	//以\x结尾会报内存溢出
	public static void test_OOM() throws Exception {
		//String DEATH_STRING="{\"a_b\":\"a_b\",\"age\":\"11\"}";
		String DEATH_STRING = "{\"a\":\"\\x";
		try {
			Object obj = JSON.parse(DEATH_STRING);
			System.out.println(obj);
			obj = JSON.parse(DEATH_STRING);
		} catch (Exception e) {
			System.out.println("fastJson error");
		}
	}
	
	//内部类测试
	//jackson内部类没有static则报错
	public static void e() throws Exception {
		Obj obj = new Obj();
		Obj.Test test=obj.new Test();
		obj.set_age("12&12");
		obj.set$a("bbb");
		obj.setA_b("a_b");
		test.setTestA("测试测试测试");
		obj.setTest(test);
		

		String aString = JSON.toJSONString(obj);
		System.out.println("fastJson str" + aString);
		Obj fastJsonObj = JSON.parseObject(aString, Obj.class);
		System.out.println("fastJson obj2 " + fastJsonObj.get_age() + " " + fastJsonObj.get$a() + " " + fastJsonObj.getA_b()+" "+fastJsonObj.getTest().getTestA());

		System.err.println("***********");
		String cString = new Gson().toJson(obj);
		System.out.println("gson str" + cString);
		Obj gsonObj = new Gson().fromJson(cString, Obj.class);
		System.out.println("gson obj3 " + gsonObj.get_age() + " " + gsonObj.get$a() + " " + gsonObj.getA_b()+ " " + gsonObj.getTest().getTestA());
		
		
		/*	System.err.println("***********");
			String bString = JsonUtil.toJson(obj);
			System.out.println("JackJsonObj str" + bString);
			Obj JackJsonObj = JsonUtil.toObject(bString, Obj.class);
			System.out.println("JackJsonObj obj3 " + JackJsonObj.get_age() + " " + JackJsonObj.get$a() + " " + JackJsonObj.getA_b()+" "+JackJsonObj.getTest().getTestA());*/
		
	}
	
	//数字就是json
	public static void f() throws Exception{
		boolean a=JSON.isValid("111");
		System.out.println(a);
	
	}
	

}
