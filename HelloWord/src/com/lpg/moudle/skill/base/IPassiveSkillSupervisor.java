package com.lpg.moudle.skill.base;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 被动技能管理器接口
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年5月7日 下午3:38:30
 */
public interface IPassiveSkillSupervisor {
	
	/**
	 * 设置被动技能
	 * 
	 * @param system 提供被动技能的模块
	 * @param passiveSkillIds 模块提供的被动技能的id列表
	 * @param update 指示是否立刻刷新被动技能
	 */
	public void setPassiveSkills(int system, ArrayList<Integer> ids, boolean update);
	
	/**
	 * 更新有效被动技能列表
	 */
	public void updatePassiveSkills();
	
	/**
	 * 获取生效的被动技能列表
	 * 
	 * @return
	 */
	public Collection<IPassiveSkill> getSkills();
	
	/**
	 * 根据被动技能id获取被动技能
	 * 
	 * @param id
	 * @return
	 */
	public IPassiveSkill getKill(int id);
}
