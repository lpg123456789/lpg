package com.lpg.test;

import java.util.HashMap;
import java.util.Map;

public enum Color {
    //定义枚举中的常量
    RED(1,"hongse") {
		@Override
		public String getUserMobile() {
			// TODO Auto-generated method stub
			return "aaaaaaaaaaaaaaaa";
		}
	}, 
    
    
    GREEN(2,"qingse") {
		@Override
		public String getUserMobile() {
			// TODO Auto-generated method stub
			return "bbbbbbbbbbbbbbbb";
		}
	},
    
    
    BLACK(3,"heise") {
		@Override
		public String getUserMobile() {
			// TODO Auto-generated method stub
			return "cccccccccccccccccc";
		}
	};
     
    private int code;
    private String name;
     
    public int getCode() {
        return code;
    }
 
    public void setCode(int code) {
        this.code = code;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    private Color(int code, String name) {
   
        this.code = code;
        this.name = name;
    }
    
    public static Map<Integer,Color> Mycoler=new HashMap();
    
    
    static {
    	   Color[] color = Color.values();
           
           for(Color c : color){
        	   Mycoler.put(c.code, c);
           }
    }
    
    public static void main(String[] args) {
    	System.out.println(Mycoler.get(2).getUserMobile());
	}
    
    public abstract String getUserMobile();
    
    //在枚举列类中定义一个自定义方法，但如果要想能够被外面访问，需要定义成static类型。
    public static void containval(){
         
        Color[] color = Color.values();
         
        for(Color c : color){
            System.out.println(c.getCode() + ":" + c.getName());
        }
    }
     
}