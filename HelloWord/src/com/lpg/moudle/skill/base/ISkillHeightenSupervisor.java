package com.lpg.moudle.skill.base;

import java.util.ArrayList;

/**
 * 技能增强接口
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年5月18日 下午10:54:59
 */
public interface ISkillHeightenSupervisor {
	
	/**
	 * 设置技能增强
	 * 
	 * @param system
	 * @param ids
	 */
	public void setSkillHeightens(int system, ArrayList<Integer> ids);
	
	/**
	 * 根据技能id获取技能增强效果
	 * 
	 * @param skillId
	 * @return
	 */
	public SKillHeightenEffect getSkillHeightenEffect(int skillId);
}
