package com.lpg.moudle.skill.base;

import java.util.ArrayList;

import com.lpg.moudle.skill.IRole;

/**
 * 技能增强提供者系统。所有实现技能增强的系统都需要实现此接口
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年5月18日 下午11:06:11
 */
public interface ISkillHeightenProvider {
	/**
	 * 获取技能增强的id列表
	 * 
	 * @param role
	 * @return
	 */
	public ArrayList<Integer> getSkillHeightens(IRole role);
}
