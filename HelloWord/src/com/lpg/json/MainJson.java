package com.lpg.json;

import org.json.JSONObject;

public class MainJson {

	
	public static void main(String[] args) {
		
		
		String json="{\"code\": 0, \r\n" + 
				"									\"list\": [\r\n" + 
				"							 								{\r\n" + 
				"							 									\"utc\": [\"GMT+04:30\", \"GMT+04:30\"],\r\n" + 
				"							 									\"name\": \"欧美\", \"serverIdStartWith\": \"1\",\r\n" + 
				"							 									\"url\": \"http: xxx.ccc\", \"key\": \"NA\",\r\n" + 
				"							 									\"payCallbackUrl\": \"http: public-sdk-dev.guangkatf.com/paycheck/confirm/ces/xindongom/V2_0\"\r\n" + 
				"							 								},\r\n" + 
				"							 								{\r\n" + 
				"							 									\"utc\": [\"GMT+04:30\", \"GMT+04:30\"],\r\n" + 
				"							 									\"name\": \"亚洲\", \"serverIdStartWith\": \"1\",\r\n" + 
				"							 									\"url\": \"http: xxx.ccc\", \"key\": \"AS\",\r\n" + 
				"							 									\"payCallbackUrl\": \"http: public-sdk-dev.guangkatf.com/paycheck/confirm/ces/xindongom/V2_0\"\r\n" + 
				"							 								}\r\n" + 
				"						 					], \r\n" + 
				"			 						\"match\": {\r\n" + 
				"			 								\"utc\": [\"GMT+04:30\", \"GMT+04:30\"],\r\n" + 
				"			 								\"name\": \"欧美\", \"serverIdStartWith\": \"1\",\r\n" + 
				"			 								\"url\": \"http: xxx.ccc\",\r\n" + 
				"			 								\"key\": \"NA\", \r\n" + 
				"			 								\"payCallbackUrl\": \"http: public-sdk-dev.guangkatf.com/paycheck/confirm/ces/xindongom/V2_0\"\r\n" + 
				"			 							}\r\n" + 
				"			 						}";
		
		
		/*JSONObject jsonObj = new JSONObject(json);
		JSONObject match = jsonObj.getJSONObject("aa");
		if(match!=null) {
			String url=match.getString("url");
			System.out.println(url);
		}*/
		
		
		JSONObject jsonObj = new JSONObject(json);
		boolean flag=jsonObj.has("match");
		if(flag) {
			JSONObject match = jsonObj.getJSONObject("match");
			String url=match.getString("url");
			System.out.println(url);
		}
		
		String apiUrl="";
		apiUrl=null;
		System.out.println(apiUrl);
		
		/*String str="";
		boolean flag=str.equals("");
		System.out.println(flag);*/
	}
	
}
