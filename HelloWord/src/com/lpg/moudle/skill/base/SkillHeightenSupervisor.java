package com.lpg.moudle.skill.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

import com.lpg.moudle.skill.config.SkillConfig;
import com.lpg.moudle.skill.config.SkillHeightenTemplate;


/**
 * 技能增强管理器
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年5月18日 下午10:55:11
 */
public class SkillHeightenSupervisor implements ISkillHeightenSupervisor{
	/**
	 * 系统到技能增强id集的映射表
	 */
	private ConcurrentHashMap<Integer, HashSet<Integer>> systemToHeightenIds = new ConcurrentHashMap<>();
	
	/**
	 * 技能id到技能增强效果的映射表
	 */
	private ConcurrentHashMap<Integer, SKillHeightenEffect> skillIdToHeightenEffect = new ConcurrentHashMap<>();
	
	@Override
	public void setSkillHeightens(int system, ArrayList<Integer> ids) {
		if (null == ids || ids.isEmpty()) {
			systemToHeightenIds.remove(system);
			return;
		}
		
		HashSet<Integer> heightenIds = systemToHeightenIds.get(system);
		if (null == heightenIds) {
			systemToHeightenIds.put(system, heightenIds = new HashSet<>());
		}
		else {
			heightenIds.clear();
		}
		heightenIds.addAll(ids);
		
		// 将原增强效果清零，准备重新计算
		for (SKillHeightenEffect effect : skillIdToHeightenEffect.values()) {
			effect.reset();
		}
		
		Collection<HashSet<Integer>> idsList = systemToHeightenIds.values();
		for(HashSet<Integer> idSet : idsList) {
			for(int id : idSet) {
				SkillHeightenTemplate t = SkillConfig.getInstance().getSkillHeightenTemplate(id);
				if (null == t) {
					continue;
				}
				
				for(int skillId : t.getSkillIds()) {
					SKillHeightenEffect effect = skillIdToHeightenEffect.get(skillId);
					if (null == effect) {
						skillIdToHeightenEffect.put(skillId, effect = new SKillHeightenEffect());
					}
					effect.superposition(t);
				}
			}
		}
	}
	
	@Override
	public SKillHeightenEffect getSkillHeightenEffect(int skillId) {
		return skillIdToHeightenEffect.get(skillId);
	}
}
