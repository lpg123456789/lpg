package com.lpg.moudle.skill.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.lpg.moudle.buff.BuffInfo;
import com.lpg.moudle.skill.IRole;
import com.lpg.moudle.skill.config.ActiveSKillTemplate;
import com.lpg.moudle.skill.config.SkillConfig;
import com.lpg.utils.DateUtils;

/**
 * 主动技能
 */
public class ActiveSkill implements IActiveSkill {
	
	protected int id;
	protected ActiveSKillTemplate template;
	protected int openLevel;
	protected IRole user;
	protected long lastCastTime;
	
	private int level = 1;
	private boolean isTriggered = false;
	
	public ActiveSkill() {
	}
	
	public ActiveSkill(int id) {
		this.id = id;
		this.openLevel = SkillConfig.getInstance().getSkillOpenLevel(id);
	}
	
	public ActiveSkill(int id, int level) {
		this.id = id;
		this.openLevel = 0;
		this.level = level;
		this.isTriggered = true;
	}
	
	public ActiveSkill(IActiveSkill skill) {
		this.id = skill.getId();
		this.setLevel(skill.getLevel());
		this.openLevel = skill.getOpenLevel();
	}
	
	@Override
	public boolean isTriggered() {
		return this.isTriggered;
	}
	
	@Override
	public long getLastCastTime() {
		return this.lastCastTime;
	}
	
	@Override
	public void setLastCastTime(long lastCastTime) {
		this.lastCastTime = lastCastTime;
	}
	
	@Override
	public boolean inCd(long currentTime) {
		if(currentTime <= 0) {
			currentTime = DateUtils.getCurrentMillis();
		}
		return currentTime - getLastCastTime() < getCd();
	}
	
	@Override
	public int getOpenLevel() {
		return this.openLevel;
	}
	
	@Override
	public void setLevel(int value) {
		this.level = value;
		updateTempate();
	}
	
	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public ActiveSKillTemplate getTemplate() {
		if(null == template) {
			updateTempate();
		}
		return template;
	}
	
	/**
	 * 更新技能模版
	 */
	protected void updateTempate() {
		template = SkillConfig.getInstance().getActiveSkillTemplate(getId(), getLevel());
	}

	@Override
	public String getName() {
		return getTemplate().getName();
	}

	@Override
	public String getDesc() {
		return getTemplate().getDescribe();
	}

	@Override
	public int getType() {
		return getTemplate().getType();
	}
	
	/**
	 * 技能作用类型
	 * ProW: 1:伤害技能 2:回复技能 0:无攻击效果的辅助技能
	 */
	@Override
	public int getEffect() {
		return getTemplate().getEffect();
	}

	@Override
	public int getIcon() {
		return getTemplate().getIcon();
	}

	@Override
	public int getPvePer() {
		return getTemplate().getPvePer();
	}

	@Override
	public int getPvpPer() {
		return getTemplate().getPvpPer();
	}

	@Override
	public int getPveHurt() {
		return getTemplate().getPveHurt();
	}

	@Override
	public int getPvpHurt() {
		return getTemplate().getPvpHurt();
	}
	
	@Override
	public int getPveMPer() {
		return getTemplate().getPveMPer();
	}
	
	@Override
	public int getPvpMPer() {
		return getTemplate().getPvpMPer();
	}

	@Override
	public int getTarget() {
		return getTemplate().getTarget();
	}

	@Override
	public int getCastRange() {
		return getTemplate().getCastRange();
	}

	@Override
	public int getAtkRange() {
		return getTemplate().getAtkRange();
	}

	@Override
	public int getPara1() {
		return getTemplate().getPara1();
	}

	@Override
	public int getPara2() {
		return getTemplate().getPara2();
	}

	@Override
	public int getDeviation() {
		return getTemplate().getDeviation();
	}

	@Override
	public int getNum() {
		return getTemplate().getNum();
	}

	@Override
	public int getGcd() {
		return getTemplate().getGcd();
	}

	@Override
	public int getCd() {
		return getTemplate().getCd();
	}

	@Override
	public ArrayList<BuffInfo> getBuffInfos() {
		return getTemplate().getBuffs();
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}
}
