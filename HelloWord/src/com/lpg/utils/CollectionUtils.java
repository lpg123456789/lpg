package com.lpg.utils;

import java.util.AbstractList;

/**
 * 集合工具类。
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2017年11月17日 下午4:27:48
 */
public class CollectionUtils {
	
	/**
	 * 在ArrayList、Vector等由AbstractList派生的列表的随机位置插入一个元素
	 * @param list 要插入元素的列表
	 * @param e 要插入的元素
	 */
	public static <E, T extends AbstractList<E>> void randomInsert(T list, E e){
		int index = (int)(Math.random() * (list.size() + 1));
		list.add(index, e);
	}
	
	/**
	 * 添加一个元素到限定长度的队列中。所谓限定长度的队列是指限制队列中元素的个数，如果超出限定个数，再往里面添加元素时将挤出
	 * 最早添加的元素。
	 * @param list 限定长度的队列
	 * @param limit 限定的长度数值
	 * @param e 要新添加到队列末尾的元素
	 * @return 被挤出的最早添加的元素。如果调用此方法时队列的长度小于限定，则没有元素被挤出，此时返回值为null
	 */
	public static <E, T extends AbstractList<E>> E addToLimitQueue(T list, int limit, E e){
		E removedElement = null;
		if(list.size() > limit){
			removedElement = list.remove(0);
		}
		
		list.add(e);
		return removedElement;
	}
	
	
}
