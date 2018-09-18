package com.lpg.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库相关工具类
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年2月2日 下午5:24:06
 */
public class DataBaseUtils {
	
	/**
	 * 将查询结果中的数据库类型转换为Java类型值
	 * 
	 * @param value
	 * @return
	 */
	public static Object cellToValue(Object value) {
		if (value instanceof String) {
			return value.toString();
		}
		if (value instanceof Integer) {
			return (Integer)value;
		}
		if (value instanceof BigInteger ) {
			return ((BigInteger)value).longValue();
		}
		if (value instanceof Short) {
			return ((Short)value).intValue();
		}
		if(value instanceof Boolean){
			return (Boolean)value;
		}
		return value;
	}
	
	/**
	 * 将SQL结果集写入到指定的对象中。
	 * 
	 * @param rs
	 * @param t 一个POJO或者Map对象
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T rsToObject(ResultSet rs, T t) throws IllegalArgumentException, IllegalAccessException, SQLException, SecurityException, NoSuchFieldException, InstantiationException {
		if(null == t) {
			return null;
		}
		
		ResultSetMetaData metaData = rs.getMetaData();
		int numColumn = metaData.getColumnCount();
		if(!(t instanceof Map)) {
			HashMap<String, Field> nameToField = ReflectUtils.getFields(t.getClass());
			for(int i = 1; i <= numColumn; ++i){
				String label = metaData.getColumnLabel(i);
				// 注意这里，用列索引而不是列名（label）去获取数据，这是因为如果结果集中存在同名的列，用列名获取数据
				// 会有问题（始终只返回第一个同名的列的值。在联合查询情况下经常会出现同名列的问题）。
				Object value = cellToValue(rs.getObject(i));
				try{
					Field f = nameToField.get(label);
					if(Modifier.isFinal(f.getModifiers()) || Modifier.isStatic(f.getModifiers())) {
						continue;
					}
					f.setAccessible(true);
					f.set(t, value);
				}
				catch(Exception e) {
				}
			}
		}
		else {
			Map<String, Object> map = (Map<String, Object>) t;
			for(int i = 1; i <= numColumn; ++i){
				String label = metaData.getColumnLabel(i);
				Object value = cellToValue(rs.getObject(i));
				map.put(label, value);
			}
		}
		return t;
	}
}
