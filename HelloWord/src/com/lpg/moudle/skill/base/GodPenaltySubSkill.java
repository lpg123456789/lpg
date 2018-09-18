package com.lpg.moudle.skill.base;


/**
 * 神罚子技能，即神罚状态的buff技能
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年3月8日 下午10:01:17
 */
public class GodPenaltySubSkill extends ActiveSkill {
	
//	public GodPenaltySubSkill(IUserRole user, int id) {
//		this.user = user;
//		this.id = id;
//		this.template = SkillConfig.getInstance().getActiveSkillTemplate(id, 1);
//	}

	@Override
	public boolean isOpen() {
		return true;
	}
	
	@Override
	public int getOpenLevel() {
		return 0;
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public void setLevel(int value) {
	}

	@Override
	public int getGcd() {
		return 0;
	}

//	@Override
//	public int getCd() {
//		return template.getCd();
//	}
}
