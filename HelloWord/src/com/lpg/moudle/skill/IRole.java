package com.lpg.moudle.skill;

import com.lpg.moudle.skill.base.IPassiveSkillSupervisor;
import com.lpg.moudle.skill.base.ISkillHeightenSupervisor;

public interface IRole {

	public IPassiveSkillSupervisor getPassiveSkillSupervisor();
	
	public ISkillHeightenSupervisor getSkillHeightenSupervisor();
	
}
