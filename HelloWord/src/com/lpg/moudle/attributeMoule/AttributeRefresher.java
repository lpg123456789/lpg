package com.lpg.moudle.attributeMoule;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.lpg.moudle.attributeMoule.attribute.consts.AttributeSystem;
import com.lpg.moudle.attributeMoule.attribute.role.IMateRole;
import com.lpg.moudle.attributeMoule.attribute.sacredBook.SacredBookModule;
import com.lpg.moudle.attributeMoule.attribute.utils.AttributeHelper;

/**
 * 属性刷新器
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年5月3日 下午6:13:27
 */
public class AttributeRefresher {
	private static AttributeRefresher instance;
	public static AttributeRefresher getInstance() {
		if(null != instance) {
			return instance;
		}
		return instance = new AttributeRefresher();
	}
	
	/**
	 * 属性提供者
	 */
	private LinkedHashMap<Integer, IAttributeProvider> systemToProvider = new LinkedHashMap<>();
	
	/**
	 * 注册属性提供者
	 */
	private AttributeRefresher() {
		//圣典系统
		systemToProvider.put(AttributeSystem.SACREDBOOK, SacredBookModule.getInstance());
		//化神系统
		//systemToProvider.put(AttributeSystem.BECOMEGOD, BecomeGodModule.getInstance());
		//化神装备
		//systemToProvider.put(AttributeSystem.EQUIPDEITY, EquipDeity.getInstance());
		//神兵
		//systemToProvider.put(AttributeSystem.GODWEAPON, GodWeapon.getInstance());
		
	}
	
	/**
	 * 刷新角色战斗属性
	 * 
	 * @param role 要刷新战斗属性的角色
	 * @param update 指示是否广播给客户端
	 */
	public void updateAttributes(IMateRole role, boolean update) {
		Iterator<Entry<Integer, IAttributeProvider>> iterator = systemToProvider.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<Integer, IAttributeProvider> next = iterator.next();
			int kind = next.getKey();
			IAttributeProvider provider = next.getValue();
			try {
				AttributeHelper.setRoleAttributes(role, provider.getAttributes(role), kind, update);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
