package com.lpg.moudle.skill.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.lpg.moudle.skill.config.PassiveSkillTemplate;
import com.lpg.moudle.skill.config.SkillConfig;

/**
 * 角色被动技能管理器
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年5月3日 下午8:59:59
 */
public class PassiveSkillSupervisor implements IPassiveSkillSupervisor {

	/**
	 * 被动技能系统id到该系统提供的被动技能列表的映射表
	 * key：被动技能系统id。@see PassiveSkillSystem
	 * value：被动技能列表
	 */
	private ConcurrentHashMap<Integer, ArrayList<Integer>> systemToIds = new ConcurrentHashMap<>();
	
	/**
	 * 有效的被动技能列表
	 */
	private ConcurrentHashMap<Integer, IPassiveSkill> idToPassvieSkill = new ConcurrentHashMap<>();
	
	@Override
	public void setPassiveSkills(int system, ArrayList<Integer> ids, boolean update) {
		if(null == ids) {
			ids = new ArrayList<>();
		}
		
		systemToIds.put(system, ids);
		if(update) {
			updatePassiveSkills();
		}
	}
	
	@Override
	public void updatePassiveSkills() {
		HashSet<Integer> idSet = new HashSet<>();
		Collection<ArrayList<Integer>> values = systemToIds.values();
		// 生成新的被动技能id列表
		for(ArrayList<Integer> ids : values) {
			idSet.addAll(ids);
		}
		
		// 找出原来的别动技能中需要移除的被动技能的id（在原来的被动技能中，不在新的id列表中）
		HashSet<Integer> rmSet = new HashSet<>();
		Iterator<Entry<Integer, IPassiveSkill>> iterator = idToPassvieSkill.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<Integer, IPassiveSkill> next = iterator.next();
			int id = next.getKey();
			if(!idSet.contains(id)) {
				rmSet.add(id);
			}
		}
		
		// 移除需要移除的被动技能
		for(int id : rmSet) {
			idToPassvieSkill.remove(id);
		}
		
		for(int id : idSet) {
			// 原来已经生成过的被动技能不再重新生成
			if(idToPassvieSkill.containsKey(id)) {
				continue;
			}
			
			PassiveSkillTemplate t = SkillConfig.getInstance().getPassiveSKillTemplate(id);
			if (null != t) {
				idToPassvieSkill.put(id, new PassiveSkill(t));
			}
		}
	}
	
	@Override
	public Collection<IPassiveSkill> getSkills() {
		return idToPassvieSkill.values();
	}
	
	@Override
	public IPassiveSkill getKill(int id) {
		return idToPassvieSkill.get(id);
	}
}
