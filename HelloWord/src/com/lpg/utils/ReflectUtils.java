package com.lpg.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 * 反射工具类
 * 
 * @author Brant
 * @mail brtcoder@163.com
 * @date 2017年11月8日 下午3:17:38
 */
public class ReflectUtils {

	/**
	 * Java基础数据类型列表
	 */
	public static final Class<?>[] BASE_TYPES = { Integer.class, Long.class, Boolean.class, Short.class, Character.class, Byte.class, Float.class, Double.class, String.class };

	/**
	 * 获取指定对象中的序号属性。所谓序号属性是只属性名有统一的前缀，并用整形后缀区别。例如：attr_1, attr_2, attr_3。
	 * 
	 * @param src 要获取属性的对象。当src为null时返回结果为null
	 * @param prefix 属性名的前缀。当属性的前缀为null时返回结果为null
	 * @param begin 后缀数字编号的起始数字。当起始编号大于终止编号时返回null。当起始编号和终止编号相同时只返回一个属性
	 * @param end 后缀数字编号的终止数字。当起始编号大于终止编号时返回null。当起始编号和终止编号相同时只返回一个属性
	 * @return 一个HashMap，其key为属性名，value为属性值
	 */
	public static HashMap<String, Object> getSerialProperties(Object src, String prefix, int begin, int end) {
		if (src == null || prefix == null || begin > end) {
			return null;
		}

		HashMap<String, Field> nameToField = getFields(src.getClass());
		HashMap<String, Object> map = new HashMap<>();
		for (int i = begin; i <= end; ++i) {
			String fieldName = prefix + i;
			try {
				Field f = nameToField.get(fieldName);
				f.setAccessible(true);
				Object p = f.get(src);
				map.put(fieldName, p);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map.isEmpty() ? null : map;
	}

	/**
	 * 根据属性名获取属性值。可以获取无参数方法的返回值。例如，propertyName传入
	 * "getName()"，就可以获取对象的getName()返回的值
	 *
	 * @param src
	 * @param propertyName
	 * @param isGetter 指示该属性是否是getter
	 * @return
	 */
	public static Object getProperty(Object src, String propertyName) {
		propertyName = StringUtils.omitWhitespace(propertyName);
		if (src == null || StringUtils.isEmpty(propertyName)) {
			return null;
		}

		int index = propertyName.length() - 2;
		if (propertyName.charAt(index) == '(') {
			propertyName = propertyName.substring(0, index);
			return getProperty(src, propertyName, true);
		}
		return getProperty(src, propertyName, false);
	}

	/**
	 * 根据属性名获取属性值。isGetter参数传入true可以获取无参数方法的返回值。例如，propertyName传入
	 * "getName"，就可以获取对象的getName()返回的值
	 *
	 * @param src
	 * @param propertyName
	 * @param isGetter 指示该属性是否是getter
	 * @return
	 */
	public static Object getProperty(Object src, String propertyName, boolean isGetter) {
		if (src == null || StringUtils.isEmpty(propertyName)) {
			return null;
		}

		Object p = null;
		if (!isGetter) {
			try {
				Field f = getField(src.getClass(), propertyName);
				f.setAccessible(true);
				p = f.get(src);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				Method method = src.getClass().getMethod(propertyName);
				method.setAccessible(true);
				p = method.invoke(src);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return p;
	}

	/**
	 * 设置对象属性。如果propertyName 传入类似 setName() 这样字符串，可以调用set方法设置其属性
	 * 
	 * @param src 要设置属性的对象
	 * @param propertyName 要设置的属性名。也可以是只接受一个参数的方法名。注意，方法名必须带有小括号才会判定为setter
	 * @param value 要设置的属性值。注意：如果设置的值为null，则必须为parameterTypes指定一个参数类型
	 * @param parameterTypes
	 *            在调用setter方法时，如果value为null，则必须为parameterTypes指定一个参数类型
	 */
	public static void setProperty(Object src, String propertyName, Object value, Class<?>... parameterTypes) {
		propertyName = StringUtils.omitWhitespace(propertyName);
		if (src == null || StringUtils.isEmpty(propertyName)) {
			return;
		}

		int index = propertyName.length() - 2;
		if (propertyName.charAt(index) == '(') {
			setProperty(src, propertyName.substring(0, index), true);
		}
		else {
			setProperty(src, propertyName, false);
		}
	}

	/**
	 * 设置对象属性。
	 * 
	 * @param src 要设置属性的对象
	 * @param propertyName 要设置的属性名。也可以是只接受一个参数的方法名
	 * @param value 要设置的属性值。注意：如果设置的值为null，则必须为parameterTypes指定一个参数类型
	 * @param isSetter 指示是否是setter方法。
	 * @param parameterTypes
	 *            在调用setter方法时，如果value为null，则必须为parameterTypes指定一个参数类型
	 */
	public static void setProperty(Object src, String propertyName, Object value, boolean isSetter, Class<?>... parameterTypes) {
		if (src == null || StringUtils.isEmpty(propertyName)) {
			return;
		}

		if (!isSetter) {
			try {
				Field f = getField(src.getClass(), propertyName);
				f.setAccessible(true);
				f.set(src, value);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				Method method;
				if (null != value) {
					method = src.getClass().getMethod(propertyName, value.getClass());
				}
				else {
					method = src.getClass().getMethod(propertyName, parameterTypes);
				}

				method.setAccessible(true);
				method.invoke(src, value);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 调用对象方法。注意：使用本方法调用方法时，方法参数中不能有值为null的参数。如果有值为null的参数，请使用
	 * invoke(Object src, String methodName, Object[] params, Class<?>[] parameterTypes)
	 * 在parameterTypes中为所有参数指明类型
	 * 
	 * @param src 要调用的方法所属的对象
	 * @param methodName 方法名称
	 * @param params 参数列表
	 * @return
	 */
	public static Object invoke(Object src, String methodName, Object... params) throws NoSuchMethodException, SecurityException, InvocationTargetException, IllegalAccessException {
		return invoke(src, methodName, params, null);
	}

	/**
	 * 调用对象方法。注意：使用本方法调用方法时，方法参数中如果有值为null的参数。则必须使用parameterTypes为所有参数指明类型
	 * 
	 * @param src 要调用的方法所属的对象
	 * @param methodName 方法名称
	 * @param params 参数列表
	 * @param parameterTypes 要调用的方法的参数类型列表
	 * @return
	 */
	public static Object invoke(Object src, String methodName, Object[] params, Class<?>[] parameterTypes) throws NoSuchMethodException, SecurityException, InvocationTargetException, IllegalAccessException {
		// 如果传入的参数类型列表为null，则使用隐式参数类型去查找方法
		boolean isImplicitParamType = (null == parameterTypes);
		if (isImplicitParamType) {
			parameterTypes = new Class<?>[params.length];
			int i = 0;
			for (Object p : params) {
				// 对于int、long等基础类型，先用int.class、long.class等基础类型做参数列表去匹配方法。
				// 如果匹配不到，再尝试用他们的包装类型去匹配方法。
				parameterTypes[i++] = getBaseType(p);
			}
		}

		Method method = null;
		try {
			// 尝试用基础类型去匹配方法。
			method = src.getClass().getMethod(methodName, parameterTypes);
		}
		catch (Exception e) {
			// 用基础类型没有匹配到（NoSuchMethodException），尝试用他们的包装类型再匹配一次
			if(isImplicitParamType) {
				int i = 0;
				for (Object p : params) {
					parameterTypes[i++] = p.getClass();
				}
				method = src.getClass().getMethod(methodName, parameterTypes);
			}
		}

		if (null != method) {
			if (method.getReturnType() != Void.class) {
				return method.invoke(src, params);
			}
			method.invoke(src, params);
		}
		return null;
	}

	/**
	 * 指示传入的对象是否是一个Class类型。 <listing> Class<?> cls = Cat.class; Cat cat = new
	 * Cat();
	 * 
	 * System.out.println(ReflectUtils.isClass(cls)); //true
	 * System.out.println(ReflectUtils.isClass(cat)); //false
	 * 
	 * </listing>
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isClass(Object obj) {
		return obj.getClass().getName().equals("java.lang.Class");
	}

	/**
	 * 检查传入的类型是否基础类型。
	 * 
	 * @param obj
	 */
	public static boolean isBaseType(Object obj) {
		if (isClass(obj)) {
			return false;
		}

		Class<?> cls = obj.getClass();
		for (Class<?> c : BASE_TYPES) {
			if (c == cls) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取基础类型的Class
	 * 
	 * @param obj
	 * @return
	 */
	public static Class<?> getBaseType(Object obj) {
		Class<?> cls = obj.getClass();
		if (cls == Integer.class) {
			return int.class;
		}

		if (cls == Long.class) {
			return long.class;
		}

		if (cls == Double.class) {
			return double.class;
		}

		if (cls == Boolean.class) {
			return boolean.class;
		}

		if (cls == Short.class) {
			return short.class;
		}

		if (cls == Character.class) {
			return char.class;
		}

		if (cls == Byte.class) {
			return byte.class;
		}

		if (cls == Float.class) {
			return float.class;
		}

		return cls;
	}

	/**
	 * 将src中的属性复制到dest中。注意，本方法不会复制静态属性和final属性，但是可以复制非public属性。
	 * 
	 * @param src 属性来源对象
	 * @param dest 要将属性复制到的目标对象
	 * @param onlyPublic 指示是否仅仅复制公共属性，而不复制私有、保护和包属性
	 * @return
	 */
	public static <T> T copyProperties(Object src, T dest, boolean onlyPublic) {
		if(onlyPublic) {
			return copyProperties(src, dest);
		}
		
		if (null == src || null == dest) {
			return dest;
		}

		HashMap<String, Field> sFieldMap = getFields(src.getClass());
		HashMap<String, Field> dFieldMap = getFields(dest.getClass());

		HashMap<String, Field> fieldMap = (sFieldMap.size() < dFieldMap.size()) ? sFieldMap : dFieldMap;
		Iterator<String> iterator = fieldMap.keySet().iterator();
		String fieldName;
		Field sField, dField;
		while (iterator.hasNext()) {
			try {
				fieldName = iterator.next();
				sField = sFieldMap.get(fieldName);
				dField = dFieldMap.get(fieldName);
				
				if(null == sField || null == dField) {
					continue;
				}

				// 如果是 final 修饰的属性，忽略处理
				if (Modifier.isFinal(dField.getModifiers())) {
					continue;
				}

				// 如果当前属性是静态属性，忽略处理
				if (Modifier.isStatic(dField.getModifiers())) {
					continue;
				}

				// 如果当前属性是非public修饰的，且onlyPublic参数为true（表示只复制public属性），忽略处理
				if (!Modifier.isPublic(dField.getModifiers()) && onlyPublic) {
					continue;
				}
				
				sField.setAccessible(true);
				dField.setAccessible(true);
				dField.set(dest, sField.get(src));
			}
			catch (IllegalArgumentException | IllegalAccessException e) {
				// Logger.logDebug(e);
			}
		}
		return dest;
	}
	
	/**
	 * 将src中的属性复制到dest中。注意，本方法不会复制静态属性、final属性和非public属性。
	 * 
	 * @param src
	 * @param dest
	 * @return
	 */
	public static <T> T copyProperties(Object src, T dest) {
		if (null == src || null == dest) {
			return dest;
		}

		Class<?> sCls = src.getClass();
		Class<?> dCls = src.getClass();
		
		Field[] sFields = sCls.getFields();
		Field[] dFields = dCls.getFields();

		Field[] fields = (sFields.length < dFields.length) ? sFields : dFields;
		
		String fieldName;
		Field sField, dField;
		for(Field f : fields) {
			try {
				fieldName = f.getName();
				sField = sCls.getField(fieldName);
				dField = dCls.getField(fieldName);

				// 如果是 final 修饰的属性，忽略处理
				if (Modifier.isFinal(dField.getModifiers())) {
					continue;
				}

				// 如果当前属性是静态属性，忽略处理
				if (Modifier.isStatic(dField.getModifiers())) {
					continue;
				}
				
				sField.setAccessible(true);
				dField.setAccessible(true);
				dField.set(dest, sField.get(src));
			}
			catch (Exception e) {
				// Logger.logDebug(e);
			}
		}
		return dest;
	}

	/**
	 * 获取类中的所有字段，public、private、protected、default、static、final等修饰的属性都可获取到（包括继承的）。
	 * 由于Class.getFields() 方法只能获取public修饰的属性字段，而Class.getDeclaredFields()又不能获取继承
	 * 自父类的字段，在需要处理所有字段时无论用这两个方法的哪个都不行，使用起来不方便，因此才实现本方法，用于
	 * 
	 * @param cls
	 * @return 返回一个属性名到Field映射的HashMap
	 */
	public static <T> HashMap<String, Field> getFields(Class<T> cls) {
		HashMap<String, Field> nameToField = new HashMap<>();
		Field[] fields = cls.getFields();
		for (Field f : fields) {
			nameToField.put(f.getName(), f);
		}

		fields = cls.getDeclaredFields();
		for (Field f : fields) {
			nameToField.putIfAbsent(f.getName(), f);
		}
		return nameToField;
	}

	/**
	 * 获取类型中的指定字段
	 * 
	 * @param cls
	 * @param fieldName
	 * @return
	 */
	public static <T> Field getField(Class<T> cls, String fieldName) {
		return getFields(cls).get(fieldName);
	}

	/**
	 * 获取类中定义的所有常量值
	 * 
	 * @param cls
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> ArrayList<T> getConstValues(Class<?> src, Class<T> type) {
		ArrayList<T> list = new ArrayList<>();
		Field[] declaredFields = src.getDeclaredFields();
		for (Field f : declaredFields) {
			if (Modifier.isStatic(f.getModifiers()) && Modifier.isFinal(f.getModifiers()) && f.getType() == type) {
				try {
					list.add((T)f.get(src));
				}
				catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
