package com.lpg.moudle.attributeMoule;

import java.util.Iterator;

import com.lpg.moudle.attributeMoule.attribute.consts.AttributeSystem;

/**
 * Long类型属性
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年1月17日 下午3:15:41
 */
public class LongAttribute extends AbstractAttribute<Long> {
	
	public LongAttribute() {
		tvalue = 0L;
		noBuffValue = 0L;
	}
	
	@Override
	public Long getValue() {
		return tvalue;
	}
	
	@Override
	public Long getNotBuffValue() {
		return this.noBuffValue;
	}
	
	@Override
	public Long getValue(int kind) {
		if(attrMap.containsKey(kind)) {
			return attrMap.get(kind);
		}
		return 0L;
	}
	
	@Override
	public Long updateValue() {
		tvalue = 0L;
		noBuffValue = 0L;
//		Iterator<Long> iterator = attrMap.values().iterator();
//		while(iterator.hasNext()) {
//			tvalue += iterator.next();
//		}
		for(Integer attrKeyType : attrMap.keySet()) {
			Long v = attrMap.get(attrKeyType);
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
