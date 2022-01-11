package com.game;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import game.utils.json.JsonUtil;

/**
 * lpg
 * 2021年9月10日
 */
public class FastJsonTwo {

	public static void main(String[] args) {
		a();
	}

	public static void test() {
		/*		String code = "{\r\n" + "	\"code\": 0,\r\n" + "	\"list\": [{\r\n" + "			\"utc\": [\"GMT+04:30\", \"GMT+04:30\"],\r\n" + "			\"name\": \"欧美\",\r\n" + "			\"serverIdStartWith\": \"1\",\r\n" + "			\"url\": \"http: xxx.ccc\",\r\n"
		+ "			\"key\": \"NA\",\r\n" + "			\"payCallbackUrl\": \"http: public-sdk-dev.guangkatf.com/paycheck/confirm/ces/xindongom/V2_0\"\r\n" + "		},\r\n" + "		{\r\n" + "			\"utc\": [\"GMT+04:30\", \"GMT+04:30\"],\r\n" + "			\"name\": \"亚洲\",\r\n"
		+ "			\"serverIdStartWith\": \"1\",\r\n" + "			\"url\": \"http: xxx.ccc\",\r\n" + "			\"key\": \"AS\",\r\n" + "			\"payCallbackUrl\": \"http: public-sdk-dev.guangkatf.com/paycheck/confirm/ces/xindongom/V2_0\"\r\n" + "		}\r\n" + "	],\r\n"
		+ "	\"match\": {\r\n" + "		\"utc\": [\"GMT+04:30\", \"GMT+04:30\"],\r\n" + "		\"name\": \"欧美\",\r\n" + "		\"serverIdStartWith\": \"1\",\r\n" + "		\"url\": \"http: xxx.ccc\",\r\n" + "		\"key\": \"NA\",\r\n"
		+ "		\"payCallbackUrl\": \"http: public-sdk-dev.guangkatf.com/paycheck/confirm/ces/xindongom/V2_0\"\r\n" + "	}\r\n" + "}";
		
		UToken uToken2 = new UToken();
		uToken2.setParam("a", "12345");
		uToken2.setParam("areaList", code);
		
		String str2 = new Gson().toJson(uToken2);
		System.out.println(str2);
		
		String str3=JSON.toJSONString(uToken2);
		System.out.println(str3);
		
		String str4=JsonUtil.toJson(uToken2);
		System.out.println(str4);
		
		UToken u = new Gson().fromJson(str2, UToken.class);*/

		Obj obj = new Obj();
		String str2 = new Gson().toJson(obj);
		System.out.println(str2);

		String str3 = JSON.toJSONString(obj);
		System.out.println(str3);

		String str4 = JsonUtil.toJson(obj);
		System.out.println(str4);
	}
	
	public static void a() {
		Node node=new Node("节点1","测试","名字",true);
		String aString = JSON.toJSONString(node);
		System.out.println(aString);
	}

}
