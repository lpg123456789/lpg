package com.lpg.moudle.skill.base;

import com.lpg.moudle.skill.config.PassiveSkillTemplate;
import com.lpg.moudle.skill.config.SkillConfig;
import com.lpg.utils.DateUtils;

/**
 * 被动技能
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年5月2日 下午6:33:43
 */
public class PassiveSkill implements IPassiveSkill {

	/**
	 * 被动技能id
	 */
	private int id;
	
	/**
	 * 最后一次触发的时间
	 */
	private long lastCastTime;
	
	/**
	 * 被动技能模版配置
	 */
	private PassiveSkillTemplate template;
	
	/**
	 * 被动技能
	 * 
	 * @param id
	 */
	public PassiveSkill(int id) {
		this.id = id;
		this.template = SkillConfig.getInstance().getPassiveSKillTemplate(id);
	}
	
	public PassiveSkill(PassiveSkillTemplate template) {
		this.id = template.getId();
		this.template = template;
	}
	
	@Override
	public int getId() {
		return this.id;
	}
	
	@Override
	public PassiveSkillTemplate getTemplate() {
		return this.template;
	}
	
	@Override
	public String getSkillName(){
		return template.getSkillName();
	}
	
	@Override
	public int getCd(){
		return template.getCd();
	}
	
	@Override
	public boolean inCd(long currentTime) {
		if(currentTime <= 0) {
			currentTime = DateUtils.getCurrentMillis();
		}
		return currentTime - getLastCastTime() < getCd();
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
	public int getTriggerProbability(){
		return template.getTriggerProbability();
	}
	
	@Override
	public int getTriggerMode(){
		return template.getTriggerMode();
	}
	
	@Override
	public String getDesc(){
		return template.getDesc();
	}
	
	@Override
	public int getTarget(){
		return template.getTarget();
	}
	
	@Override
	public int getTriggeType(){
		return template.getTriggeType();
	}
	
	@Override
	public int getParameter1(){
		return template.getParameter1();
	}
	
	@Override
	public int getParameter2(){
		return template.getParameter2();
	}
	/**
	 * 获取参数3
	 */
	public int getParameter3() {
		return this.template.getParameter3();
	}
}
