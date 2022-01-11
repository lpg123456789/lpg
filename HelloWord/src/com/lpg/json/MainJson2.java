package com.lpg.json;

import org.json.JSONObject;

import com.google.gson.Gson;

public class MainJson2 {

	
	public static void main(String[] args) {
		String code="{\r\n" + 
				"	\"code\": 0,\r\n" + 
				"	\"list\": [{\r\n" + 
				"			\"utc\": [\"GMT+04:30\", \"GMT+04:30\"],\r\n" + 
				"			\"name\": \"欧美\",\r\n" + 
				"			\"serverIdStartWith\": \"1\",\r\n" + 
				"			\"url\": \"http: xxx.ccc\",\r\n" + 
				"			\"key\": \"NA\",\r\n" + 
				"			\"payCallbackUrl\": \"http: public-sdk-dev.guangkatf.com/paycheck/confirm/ces/xindongom/V2_0\"\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"utc\": [\"GMT+04:30\", \"GMT+04:30\"],\r\n" + 
				"			\"name\": \"亚洲\",\r\n" + 
				"			\"serverIdStartWith\": \"1\",\r\n" + 
				"			\"url\": \"http: xxx.ccc\",\r\n" + 
				"			\"key\": \"AS\",\r\n" + 
				"			\"payCallbackUrl\": \"http: public-sdk-dev.guangkatf.com/paycheck/confirm/ces/xindongom/V2_0\"\r\n" + 
				"		}\r\n" + 
				"	],\r\n" + 
				"	\"match\": {\r\n" + 
				"		\"utc\": [\"GMT+04:30\", \"GMT+04:30\"],\r\n" + 
				"		\"name\": \"欧美\",\r\n" + 
				"		\"serverIdStartWith\": \"1\",\r\n" + 
				"		\"url\": \"http: xxx.ccc\",\r\n" + 
				"		\"key\": \"NA\",\r\n" + 
				"		\"payCallbackUrl\": \"http: public-sdk-dev.guangkatf.com/paycheck/confirm/ces/xindongom/V2_0\"\r\n" + 
				"	}\r\n" + 
				"}";
		/*	JSONObject json=new JSONObject(code);
			System.out.println(json);*/
		
		JSONObject json=new JSONObject(code);
			UToken uToken=new UToken();
			uToken.setParam( "a", "12345");
			uToken.setParam( "areaList", json);
			String str=new Gson().toJson(uToken) ;
			UToken u1=new Gson().fromJson(str, UToken.class);
			System.out.println(str);
		
		
		UToken uToken2=new UToken();
		uToken2.setParam( "a", "12345");
		uToken2.setParam( "areaList", code);
		String str2=new Gson().toJson(uToken2) ;
		UToken u2=new Gson().fromJson(str2, UToken.class);
		System.out.println(str2);
		
		
		
		
		 
		/*UToken uToken=new UToken();
		uToken.setJson(json);
		uToken.toString();
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(uToken));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
