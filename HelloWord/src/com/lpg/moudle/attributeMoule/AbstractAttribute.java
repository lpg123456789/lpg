package com.lpg.moudle.attributeMoule;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 属性数据
 * 
 * @author 李俊豪、Brant
 * @mail   brtcoder@163.com
 * @date   2018年1月17日 上午11:45:23
 */
abstract public class AbstractAttribute<T extends Number> {
	
	/**
	 * key：属性类型。常量见 AttributeType
	 * value：该类型的属性值
	 */
	protected ConcurrentHashMap<Integer, T> attrMap = new ConcurrentHashMap<>();
	
	/**
	 * 属性总值---包含buff属性的战力
	 */
	protected T tvalue;
	
	/**
	 * 不包含战力属性的属性总值
	 */
	protected T noBuffValue;
	
	/**
	 * 片段上限。出于特殊显示需求或者因为客户端不能表达服务端数据类型的长度时需要将数据进行分段切割。
	 * 此属性指明每段的最大值
	 */
	protected int fragmentMax = Integer.MAX_VALUE;
	
	/**
	 * 分段切割时每段的上限。出于特殊显示需求或者因为客户端不能表达服务端数据类型的长度时需要将数据进行分段切割。
	 * 此属性指明每段的最大值
	 * 
	 * @return
	 */
	public int getFragmentMax() {
		return this.fragmentMax;
	}
	
	public void setFragmentMax(int fragmentMax) {
		this.fragmentMax = fragmentMax;
	}
	
	/**
	 * 属性变更
	 * @param value
	 * @return
	 */
	public T setValue(int kind, T value) {
		attrMap.put(kind, value);
		return updateValue();
	}
	
	/**
	 * 删除指定类型的属性值
	 * @param kind
	 * @return 返回删除属性后的剩余属性值
	 */
	public T delValue(int kind) {
		attrMap.remove(kind);
		return updateValue();
	}
	
	/**
	 * 获取指定类型的属性值
	 * 
	 * @param type
	 * @return
	 */
	abstract public T getValue(int kind);
	
	/**
	 * 获取属性最终值，即所有类型的属性值的和。如果设置了max，属性受max限制
	 * 
	 * @return
	 */
	abstract public T getValue();
	
	/**
	 * 获取属性最终值---不包含buff属性，用于计算战力
	 * 
	 * @return
	 */
	abstract public T getNotBuffValue();
	
	/**
	 * 更新tvalue
	 * 
	 * @return
	 */
	abstract public T updateValue();
	
	/**
	 * 克隆属性
	 * 
	 * @param attr
	 */
	public void copyTo(AbstractAttribute<T> attr) {
		attr.tvalue = this.tvalue;
		attr.fragmentMax = this.fragmentMax;
		
		ConcurrentHashMap<Integer, T> toMap = attr.attrMap;
		Iterator<Entry<Integer, T>> iterator = attrMap.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<Integer, T> next = iterator.next();
			Integer kind = next.getKey();
			T value = next.getValue();
			toMap.put(kind, value);
		}
	}

}