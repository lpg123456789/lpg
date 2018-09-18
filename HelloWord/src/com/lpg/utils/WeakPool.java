package com.lpg.utils;

import java.util.ArrayList;
import java.util.WeakHashMap;

/**
 * 弱引用对象池
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2017年12月16日 下午4:35:05
 */
public class WeakPool {
	private static WeakHashMap<String, ArrayList<Object>> nameToInstances = new WeakHashMap<>();
	
	/**
	 * 获取指定类型的弱引用缓存Key。注意，弱引用的缓存key不能是字符串字面值常量，因为字面值常量是不会销毁的，用它做弱引用的key
	 * 会造成对象无法被回收
	 * @param cls
	 * @return
	 */
	private static <T> String getWeakKey(Class<T> cls){
		return new String(cls.getName());
	}
	
	/**
	 * 获取一个指定类的实例。返回一个实例对象
	 * @param cls
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	synchronized public static <T> T getOne(Class<T> cls) throws InstantiationException, IllegalAccessException {
		ArrayList<Object> cachedList = nameToInstances.get(cls.getName());
		if(null == cachedList || cachedList.isEmpty()){
			return cls.newInstance();
		}
		return (T)cachedList.remove(cachedList.size() - 1);
	}
	
	/**
	 * 获取多个指定类的实例。返回一个ArrayList
	 * @param cls
	 * @param n
	 * @return
	 */
	@SuppressWarnings("unchecked")
	synchronized public static <T> ArrayList<T> getMultiple(Class<T> cls, int n)  throws InstantiationException, IllegalAccessException {
		if(n <= 0){
			throw new UnsupportedOperationException("The n must great zero!");
		}
		
		ArrayList<T> resultList = new ArrayList<>();
		ArrayList<Object> cachedList = nameToInstances.get(cls.getName());
		if(null == cachedList || cachedList.isEmpty()){
			for(int i = 0; i < n; ++i){
				resultList.add(cls.newInstance());
			}
		}
		else if(cachedList.size() >= n){
			while(n-- > 0){
				T t = (T)cachedList.remove(cachedList.size() - 1);
				resultList.add(t);
			}
		}
		else{
			int d = n - cachedList.size();
			while(!cachedList.isEmpty()){
				T t = (T)cachedList.remove(cachedList.size() - 1);
				resultList.add(t);
			}
			
			for(int i = 0; i < d; ++i){
				resultList.add(cls.newInstance());
			}
		}
		return resultList;
	}
	
	/**
	 * 将实例对象存入对象池中
	 * @param t
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	synchronized public static <T> void put(T...args){
		if(args.length == 0){
			return;
		}
		
		Class<?> cls = args[0].getClass();
		ArrayList<Object> cachedList = nameToInstances.get(cls.getName());
		if(null == cachedList){
			nameToInstances.put(getWeakKey(cls), cachedList = new ArrayList<>());
		}
		
		for(T t : args){
			cachedList.add(t);
		}
	}
}
