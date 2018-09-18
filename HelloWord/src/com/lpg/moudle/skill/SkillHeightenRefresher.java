package com.lpg.moudle.skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.lpg.moudle.skill.base.ISkillHeightenProvider;
import com.lpg.moudle.skill.base.ISkillHeightenSupervisor;

/**
 * 技能增强刷新器
 */
public class SkillHeightenRefresher {

	private static SkillHeightenRefresher instance;
	public static SkillHeightenRefresher getInstance() {
		if (null != instance) {
			return instance;
		}
		return instance = new SkillHeightenRefresher();
	}

	/**
	 * 被动技能刷新器集合
	 */
	private HashMap<Integer, ISkillHeightenProvider> systemToProvider = new HashMap<>();

	/**
	 * 在这里注册被动技能提供者模块
	 */
	private SkillHeightenRefresher() {
		//圣器
//		systemToProvider.put(SkillHeightenSystem.HALLOWS, Hallows.getInstance());
//		//神魂（套装效果）
//		systemToProvider.put(SkillHeightenSystem.SH_SUIT, ShSuitModule.getInstance());
	}
	
	/**
	 * 刷新角色的技能增强
	 * 
	 * @param mate 要刷新被动技能的角色
	 * @param update 指示是否要将技能增强广播给客户端
	 */
	public void updateSKillHeightens(IRole mate, boolean update) {
		ISkillHeightenSupervisor skillHeightenSupervisor = mate.getSkillHeightenSupervisor();
		Iterator<Entry<Integer, ISkillHeightenProvider>> iterator = systemToProvider.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<Integer, ISkillHeightenProvider> next = iterator.next();
			int system = next.getKey();
			ISkillHeightenProvider provider = next.getValue();
			try {
				skillHeightenSupervisor.setSkillHeightens(system, provider.getSkillHeightens(mate));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 设置角色技能增强
	 * 
	 * @param mate 增强的角色
	 * @param system 增强系统。常量见SkillHeightenSystem类
	 * @param ids 增强id列表
	 */
	public void updateRolePassiveSKills(IRole mate, int system, ArrayList<Integer> ids) {
		ISkillHeightenSupervisor skillHeightenSupervisor = mate.getSkillHeightenSupervisor();
		if (ids == null) {
			ISkillHeightenProvider provider = systemToProvider.get(system);
			if(null == provider) {
				return;
			}	
			try {
				skillHeightenSupervisor.setSkillHeightens(system, provider.getSkillHeightens(mate));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			skillHeightenSupervisor.setSkillHeightens(system, ids);
		}
	}
}
