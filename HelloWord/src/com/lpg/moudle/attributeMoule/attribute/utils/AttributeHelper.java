package com.lpg.moudle.attributeMoule.attribute.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lpg.moudle.attributeMoule.AbstractAttribute;
import com.lpg.moudle.attributeMoule.IBattleAttributes;
import com.lpg.moudle.attributeMoule.IntegerAttribute;
import com.lpg.moudle.attributeMoule.LongAttribute;
import com.lpg.moudle.attributeMoule.attribute.consts.AttributeName;
import com.lpg.moudle.attributeMoule.attribute.consts.AttributeSystem;
import com.lpg.moudle.attributeMoule.attribute.role.IBattleRole;
import com.lpg.utils.StringUtils;

/**
 * 属性相关工具类
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年2月5日 上午11:24:53
 */
public class AttributeHelper {
	/**
	 * 将如下格式的字符串解析为属性map
	 * atk,100|pdef,30|mdef,30|hpMax,2000|speed,1000
	 * 
	 * @param str
	 * @return
	 */
	public static HashMap<String, Integer> parseAttributes(String str) {
		HashMap<String, Integer> attrMap = new HashMap<>();
		str = StringUtils.omitWhitespace(str);
		String[] arr1 = str.split("\\|");
		for (String s : arr1) {
			String[] arr2 = s.split(",");
			attrMap.put(arr2[0], Integer.parseInt(arr2[1]));
		}
		return attrMap;
	}
	
	/**
	 * 将图鉴属性解析到已存在的属性map中，如果属性已经存在，则使用加法相加
	 * 
	 * @param str
	 * @param attrMap
	 * @return
	 */
	public static HashMap<String, Number> parseAttributes(String str, HashMap<String, Number> attrMap) {
		str = StringUtils.omitWhitespace(str);
		String[] arr1 = str.split("\\|");
		for (String s : arr1) {
			String[] arr2 = s.split(",");
			String name = arr2[0];
			int value = Integer.parseInt(arr2[1]);
			if (attrMap.containsKey(name)) {
				value += attrMap.get(name).intValue();
			}
			attrMap.put(name, value);
		}
		return attrMap;
	}

	/**
	 * 使用属性配置设置角色属性
	 * 
	 * @param role 要设置属性的角色
	 * @param str 属性配置字符串
	 * @param kind 属性种类。见AttributeKind类的常量
	 * @param update 指示是否将属性变更通知给客户端
	 * @see AttributeSystem
	 */
	public static void setRoleAttributes(IBattleRole role, String str, int kind, boolean update, boolean updatePower) {
		setRoleAttributes(role, parseAttributes(str), kind, update, updatePower);
	}
	
	/**
	 * 默认刷新战力的设置属性
	 * 
	 * @param role
	 * @param attrMap
	 * @param kind
	 * @param update
	 */
	public static void setRoleAttributes(IBattleRole role, HashMap<String, ? extends Number> attrMap, int kind, boolean update) {
		setRoleAttributes(role, attrMap, kind, update, true);
	}

	/**
	 * 使用属性map设置角色属性
	 * 
	 * @param role 要设置属性的角色
	 * @param attrMap 属性配置map
	 * @param kind 属性种类。见AttributeKind类的常量
	 * @param update 指示是否将属性变更通知给客户端
	 * @param updatePower 指示是否更新战斗力
	 */
	public static void setRoleAttributes(IBattleRole role, HashMap<String, ? extends Number> attrMap, int kind, boolean update, boolean updatePower) {
		if(null == role) {
			return;
		}
		
		IBattleAttributes attributes = role.getAttributes();
		attrMap = getChangeMap(attributes, attrMap, kind);
		Set<String> keySet = attrMap.keySet();
		int attrTypes[] = new int[attrMap.size()];
		int i = 0;
		for(String key : keySet) {
			setAttribute(attributes, key, attrMap.get(key), kind);
			attrTypes[i++] = AttributeName.getAttributeType(key);
		}
		
		if (updatePower) {
			updatePower(role);
		}
		
		if(update) {
			updateAttribute(role, attrTypes);
		}
	}
	
	
	/**
	 * 刷新角色战斗力到数据库
	 * 
	 * @param role
	 */
	public static void updatePower(IBattleRole role) {
		
	}
	
	/**
	 * 用于计算buff属性变更
	 * @param role
	 * @param attrChangeMap
	 */
	public static void setRoleBuffAttributes(IBattleRole role, HashMap<String, ? extends Number> attrChangeMap) {
		if(null == role) {
			return;
		}
		IBattleAttributes attributes = role.getAttributes();
//		if (attributes instanceof MirrorAttributes) {
//			return ;
//		}
		Set<String> keySet = attrChangeMap.keySet();
		int attrTypes[] = new int[attrChangeMap.size()];
		int i = 0;
		for(String key : keySet) {
			Number v = attributes.getAttribute(key, AttributeSystem.BUFF);
			Number newValue = v.longValue()+attrChangeMap.get(key).longValue();
			setAttribute(attributes, key, newValue, AttributeSystem.BUFF);
			attrTypes[i++] = AttributeName.getAttributeType(key);
		}
		//属性变更回调
		updateBuffAttribute(role, attrTypes);
	}
	
	/**
	 * 设置属性值
	 * 
	 * @param attributes 战斗属性对象
	 * @param attrName 属性名
	 * @param attrValue 属性值
	 * @param kind 属性种类。见AttributeKind类的常量
	 * @see AttributeSystem
	 */
	private static void setAttribute(IBattleAttributes attributes, String attrName, Number attrValue, int kind) {
		try {
			Method setter = attributes.getSetter(attrName);
			if (setter == null) {
				System.out.println("未实现的属性："+attrName);
				return ;
			}
			if(setter.getParameterCount() == 2) {
				if(setter.getParameterTypes()[1] == int.class) {
					setter.invoke(attributes, kind, attrValue.intValue());
				}
				else {
					setter.invoke(attributes, kind, attrValue.longValue());
				}
			}
			else if(setter.getParameterCount() == 1){
				if(setter.getParameterTypes()[0] == int.class) {
					setter.invoke(attributes, attrValue.intValue());
				}
				else {
					setter.invoke(attributes, attrValue.longValue());
				}
			}
		}
		catch(Exception e) {
			System.out.println("错误"+attrName);
		}
	}
	
	/**
	 * 刷新角色属性（将角色属性变更广播给客户端）。刷新的对象可以是玩家、伙伴和怪物
	 * 
	 * @param role
	 * @param attrTypes
	 */
	public static void updateAttribute(IBattleRole role, int... attrTypes) {
		//广播信息
	}
	
	/**
	 * 刷新角色属性（将角色属性变更广播给客户端）。刷新的对象可以是玩家、伙伴
	 * 用于buff属性，不用刷新战力
	 * @param role
	 * @param attrTypes
	 */
	public static void updateBuffAttribute(IBattleRole role, int... attrTypes) {
		//广播信息
	}
	
	/**
	 * 封装属性变化map。例如，装备模块在换装前提供的属性map为{atk:100, hpMax:1000, mdef:800}，换装后提供的属性map为
	 * {shield:200, speed:400, hpMax:300}，那么通过本方法封装之后的属性变化map为：
	 * {atk:0, hpMax:300, mdef:0, shield:200, speed:400}。然后setRoleAttributes方法使用封装后的属性变化map去
	 * 设置角色属性才能将atk和mdef清零
	 * 
	 * @param newMap
	 * @param kind
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, Number> getChangeMap(IBattleAttributes attributes, HashMap<String, ? extends Number> newMap, int kind) {
		Class<? extends IBattleAttributes> cls = attributes.getClass();
		HashMap<String, Number> result = new HashMap<>(); 
		HashSet<String> names = attributes.getAttributeNames();
		for(String name : names) {
			if(null != newMap && newMap.containsKey(name)) {
				result.put(name, newMap.get(name));
			}
			else {
				try {
					Field f = cls.getDeclaredField(name);
					Class<?> t = f.getType();
					if(t == IntegerAttribute.class || t == LongAttribute.class) {
						f.setAccessible(true);
						AbstractAttribute<Number> a = (AbstractAttribute<Number>) f.get(attributes);
						if(a.getValue(kind).longValue() != 0) {
							result.put(name, 0);
						}
					}
				}
				catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
					System.out.println("错误");
				}
			}
		}
		return result;
	}

}
