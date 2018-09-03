package com.lpg.test.testReflect;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionOne {

	public static void main(String[] args) {
		
		gmOrder();
	
	}
	
	/**
	 * 1.反射用全类名
	 * 频繁创建了新对象cl.newInstance()
	 */
	public static void gmOrder() {
		
		String detail = "com.lpg.moudle.reflection.ReflectionOne_one_arg1";
		String[] details = detail.split("_");
		String className = details[0];
		String method = details[1];
		String arg1=details[2];
		// 加载类
		try {
			Class<?> cl = Class.forName(className);
			Object obj = cl.newInstance();
			Method md = obj.getClass().getMethod(method, String.class);
			md.invoke(obj, arg1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void one(String a) {
		System.out.println(a);
	}
	
}
