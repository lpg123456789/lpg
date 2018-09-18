package com.lpg.moudle.skill.base;

import java.util.ArrayList;

import com.lpg.moudle.skill.IRole;


/**
 * 被动技能提供者接口
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年5月3日 下午4:15:29
 */
public interface IPassiveSkillProvider {
	
	/**
	 * 获取伙伴或主角的被动技能列表。role.getOwnerId()是角色所属玩家的userId，role.getOwner()是角色所属玩家的UserRole，
	 * role.getJob()是角色的职业。
	 * 
	 * @param role
	 * @return 返回系统所增加的被动技能id列表
	 */
	public ArrayList<Integer> getPassiveSkills(IRole role);
}
