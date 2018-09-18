package com.lpg.utils;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 多线程同步锁管理工具
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年1月4日 下午2:48:50
 */
public class LockManager {

	private static ConcurrentHashMap<Long, Object> idToLock = new ConcurrentHashMap<>();
	
	/**
	 * 根据ID获取对象锁
	 * 
	 * @param id
	 * @return
	 */
	public static Object getLock(long id, Object lock) {
		if(idToLock.containsKey(id)) {
			return idToLock.get(id);
		}
		
		if(null == lock) {
			lock = new Object();
			idToLock.put(id, lock);
		}
		return lock;
	}
	
	/**
	 * 根据ID解除对象锁
	 * 
	 * @param id
	 * @return
	 */
	public static Object unlock(long id) {
		Object lock = idToLock.remove(id);
		if(null != lock) {
			lock.notifyAll();
		}
		return lock;
	}
	
	/**
	 * 解除所有同步锁
	 */
	public static void unlock() {
		Collection<Object> list = idToLock.values();
		for (Object lock : list) {
			lock.notifyAll();
		}
		idToLock.clear();
	}
	
	/**
	 * 获取同步锁管理器中持有的对象的数量
	 * 
	 * @return
	 */
	public static int size() {
		return idToLock.size();
	}
}
