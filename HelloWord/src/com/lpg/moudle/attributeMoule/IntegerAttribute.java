package com.lpg.moudle.attributeMoule;

import java.util.Iterator;

import com.lpg.moudle.attributeMoule.attribute.consts.AttributeSystem;

/**
 * Integer类型属性
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年1月17日 下午3:15:57
 */
public class IntegerAttribute extends AbstractAttribute<Integer> {

	public IntegerAttribute() {
		tvalue = 0;
		noBuffValue = 0;
	}
	
	@Override
	public Integer getValue() {
		return tvalue;
	}
	
	@Override
	public Integer getNotBuffValue() {
		return this.noBuffValue;
	}
	
	@Override
	public Integer getValue(int kind) {
		if(attrMap.containsKey(kind)) {
			return attrMap.get(kind);
		}
		return 0;
	}
	
	@Override
	public Integer updateValue() {
		tvalue = 0;
		noBuffValue = 0;
//		Iterator<Integer> iterator = attrMap.values().iterator();
//		while(iterator.hasNext()) {
//			tvalue += iterator.next();
//		}
		for(Integer attrKeyType : attrMap.keySet()) {
			Integer v = attrMap.get(attrKeyType);
			if (v == null) {
				continue;
			}
			if (attrKeyType != AttributeSystem.BUFF) {
				noBuffValue += v;
			}
			tvalue += v;
		}
		return tvalue;
	}
}
