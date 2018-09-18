package com.lpg.moudle.skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.lpg.moudle.skill.base.IPassiveSkillProvider;
import com.lpg.moudle.skill.base.IPassiveSkillSupervisor;

/**
 * 被动技能刷新器
 */
public class PassiveSkillRefresher {

	private static PassiveSkillRefresher instance;
	public static PassiveSkillRefresher getInstance() {
		if (null != instance) {
			return instance;
		}
		return instance = new PassiveSkillRefresher();
	}

	/**
	 * 被动技能刷新器集合
	 */
	private HashMap<Integer, IPassiveSkillProvider> systemToProvider = new HashMap<>();

	/**
	 * 在这里注册被动技能提供者模块
	 */
	private PassiveSkillRefresher() {
		
		
//		// 徽章
//		systemToProvider.put(PassiveSkillSystem.BADGE, EquipBadge.getInstance());
//		// 创世神装
//		systemToProvider.put(PassiveSkillSystem.GENESIS_EQUIP, EquipGenesis.getInstance());
//		// 魔戒（未实现接口）
//		systemToProvider.put(PassiveSkillSystem.LORDRINGS, LordRingsModule.getInstance());
//		// 器灵（未实现接口）
//		systemToProvider.put(PassiveSkillSystem.WEAPONSOUL, WeaponSoulModule.getInstance());
//		// 翅膀（未实现接口）
//		systemToProvider.put(PassiveSkillSystem.WING, WindModule.getInstance());
//		// 辅助装备
//		systemToProvider.put(PassiveSkillSystem.EQUIPAUXILIARY, EquipAuxiliary.getInstance());
//		// 战翼
//		systemToProvider.put(PassiveSkillSystem.ZY, ZYModule.getInstance());
//		// 圣典
//		systemToProvider.put(PassiveSkillSystem.SACREDBOOK, SacredBookModule.getInstance());
//		//臂环
//		systemToProvider.put(PassiveSkillSystem.ARMBAND, ArmBandModule.getInstance());
		
	}
	
	/**
	 * 刷新角色被动技能
	 * 
	 * @param mate 要刷新被动技能的角色
	 * @param update 指示是否广播个客户端
	 */
	public void updatePassiveSKills(IRole mate, boolean update) {
		IPassiveSkillSupervisor passiveSkillSupervisor = mate.getPassiveSkillSupervisor();
		Iterator<Entry<Integer, IPassiveSkillProvider>> iterator = systemToProvider.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<Integer, IPassiveSkillProvider> next = iterator.next();
			int system = next.getKey();
			IPassiveSkillProvider provider = next.getValue();
			try {
				ArrayList<Integer> passiveSkills = provider.getPassiveSkills(mate);
				passiveSkillSupervisor.setPassiveSkills(system, passiveSkills, false);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		passiveSkillSupervisor.updatePassiveSkills();
	}
	
	/**
	 * 设置角色被动技能
	 * 
	 * @param mate
	 * @param system
	 * @param update
	 */
	public void updateRolePassiveSKills(IRole mate, int system, boolean update) {
		IPassiveSkillProvider provider = systemToProvider.get(system);
		if(null == provider) {
			return;
		}
		IPassiveSkillSupervisor passiveSkillSupervisor = mate.getPassiveSkillSupervisor();
		passiveSkillSupervisor.setPassiveSkills(system, provider.getPassiveSkills(mate), true);
	}
}
