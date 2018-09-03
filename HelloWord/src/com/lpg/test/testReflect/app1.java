package com.lpg.test.testReflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class app1 {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		Class c = Class.forName("com.lpg.moudle.reflection.Logger");

		Method[] ms = c.getMethods();

		Method m_show = c.getMethod("show", null);

		m_show.invoke(new Logger(), null);

		Method m_cal = c.getMethod("cal", new Class[] { int.class, int.class });

		Object result = m_cal.invoke(new Logger(), 233, 2233);

		new Logger().multi("v", "dd", "ds");

		System.out.println(result);

		Method m_multi = c.getMethod("multi", new Class[] { String[].class });

		result = m_multi.invoke(new Logger(), new Object[] { new String[] { "v", "dd", "ds" } });

		System.out.println(result);
	}

}