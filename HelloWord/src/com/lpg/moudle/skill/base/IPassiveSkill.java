package com.lpg.moudle.skill.base;

import com.lpg.moudle.skill.config.PassiveSkillTemplate;

/**
 * 被动技能接口
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年5月2日 下午6:33:10
 */
public interface IPassiveSkill {
	
	/**
	 * 被动技能id
	 * 
	 * @return
	 */
	public int getId();
	
	/**
	 * 获取被动技能配置模版
	 * 
	 * @return
	 */
	public PassiveSkillTemplate getTemplate();
	
	/**
	 * 获取被动技能名
	 */
	public String getSkillName();
	
	/**
	 * 获取触发间隔
	 * 作者: 单位，毫秒 触发后，在此时间内，不能再次触发
	 */
	public int getCd();
	
	/**
	 * 指示被动技能是否在触发CD中
	 * @param currentTime 当前时间（毫秒）
	 * @return
	 */
	public boolean inCd(long currentTime);
	
	/**
	 * 获取技能最后一次施放的时间（毫秒单位）
	 * 
	 * @return
	 */
	public long getLastCastTime();
	
	/**
	 * 设置技能最后一次施放的的时间（毫秒单位）
	 * 
	 * @param lastCastTime
	 */
	public void setLastCastTime(long lastCastTime);
	
	/**
	 * 获取触发几率（万分比）
	 */
	public int getTriggerProbability();
	
	/**
	 * 获取触发方式
	 * 作者: 1=攻击时有几率触发（本次攻击前触发，AOE技能只做一次几率判断） 2=受攻击时有几率触发（受到本次攻击前触发）
	 */
	public int getTriggerMode();
	
	/**
	 * 获取被动技能描述
	 */
	public String getDesc();
	
	/**
	 * 获取触发效果生效目标
	 * 作者: 跟触发方式关联 1、攻击时触发：      1：自己      2：攻击对象  2、受击时触发：      1：自己      2：攻击自己的对象
	 */
	public int getTarget();
	
	/**
	 * 获取触发效果类型
	 * 作者: 1：触发一个buff/debuff 2：本次攻击伤害加成 3：触发一个技能
	 */
	public int getTriggeType();
	
	/**
	 * 获取参数1
	 * 作者: 1：触发一个buff/debuff    参数1：buff id    参数2：buff 时间（毫秒）  2：本次攻击伤害加成    参数1：伤害加成比例，万分比值  5：触发一个技能    参数1：技能id  参数2：技能等级
	 */
	public int getParameter1();
	
	/**
	 * 获取参数2
	 */
	public int getParameter2();
	
	/**
	 * 获取参数3
	 */
	public int getParameter3();
}
