package com.lpg.moudle.attributeMoule;

import java.util.HashMap;

import com.lpg.moudle.attributeMoule.attribute.role.IMateRole;


/**
 * 属性提供者
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年3月29日 下午5:38:39
 */
public interface IAttributeProvider {
	
	/**
	 * 获取指定伙伴的属性
	 * 
	 * @param mate
	 * @return
	 */
	public HashMap<String, Number> getAttributes(IMateRole mate);
}
