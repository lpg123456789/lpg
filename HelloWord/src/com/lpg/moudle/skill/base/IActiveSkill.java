package com.lpg.moudle.skill.base;

import com.lpg.moudle.buff.IBuffGenerator;
import com.lpg.moudle.skill.config.ActiveSKillTemplate;

/**
 * 主动技能接口
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年1月13日 下午2:37:18
 */
public interface IActiveSkill extends IBuffGenerator {
	
	/**
	 * 技能所属角色所属玩家。注意，它不一定是技能拥有者。比如，伙伴的技能，技能拥有者是伙伴对象，而user却是伙伴所属的玩家主角。
	 * 怪物的技能此属性为null
	 * 
	 * @return
	 */
	//public IUserRole getUser();

	/**
	 * 技能ID
	 * 
	 * @return
	 */
	public int getId();
	
	/**
	 * 获取技能的开放等级
	 * 
	 * @return
	 */
	public int getOpenLevel();
	
	/**
	 * 指示技能是否已经开放
	 * 
	 * @return
	 */
	public boolean isOpen();
	
	/**
	 * 技能模版
	 * 
	 * @return
	 */
	public ActiveSKillTemplate getTemplate();
	
	/**
	 * 获取技能DS
	 * 
	 * @return
	 */
	//public SkillDS getDs();
	
	/**
	 * 技能等级
	 * 
	 * @return
	 */
	public int getLevel();
	
	/**
	 * 设置技能等级
	 * 
	 * @param value
	 */
	public void setLevel(int value);
	
	/**
	 * 技能名称
	 * 
	 * @return
	 */
	public String getName();
	
	/**
	 * 技能描述
	 * 
	 * @return
	 */
	public String getDesc();
	
	/**
	 * 技能类型
	 * 
	 * @return
	 */
	public int getType();
	
	/**
	 * 技能作用类型。1:伤害技能 2:回复技能 0:无攻击效果的辅助技能
	 * 
	 * @return
	 */
	public int getEffect();
	
	/**
	 * 技能图标
	 * 
	 * @return
	 */
	public int getIcon();
	
	/**
	 * 技能攻击怪物时攻击伤害百分比取值
	 * 
	 * @return
	 */
	public int getPvePer();
	
	/**
	 * 技能攻击玩家时攻击伤害百分比取值
	 * 
	 * @return
	 */
	public int getPvpPer();
	
	/**
	 * PVE魔法百分比
	 * 
	 * @return
	 */
	public int getPveMPer();
	
	/**
	 * PVP魔法百分比
	 * 
	 * @return
	 */
	public int getPvpMPer();
	
	/**
	 * 技能攻击怪物时固定伤害值
	 * 
	 * @return
	 */
	public int getPveHurt();
	
	/**
	 * 技能攻击玩家时固定伤害值
	 * 
	 * @return
	 */
	public int getPvpHurt();
	
	/**
	 * 技能目标类型。见SkillTarget中的常量
	 * 
	 * @return
	 * @see ActiveSkillTarget
	 */
	public int getTarget();
	
	/**
	 * 释放距离。即目标距离技能释放者多少格可以释放该技能
	 * 
	 * @return
	 */
	public int getCastRange();
	
	/**
	 * 攻击范围类型。见SkillRange中的常量
	 * 
	 * @return
	 * @see ActiveSkillRange
	 */
	public int getAtkRange();
	
	/**
	 * 攻击范围参数1
	 * 
	 * @return
	 */
	public int getPara1();
	
	/**
	 * 攻击范围参数2
	 * 
	 * @return
	 */
	public int getPara2();
	
	/**
	 * 攻击偏移量。技能施放时实际生效攻击范围偏移的格子数，正数为向玩家前方偏移，负数为向玩家后方偏移 
	 * 
	 * @return
	 */
	public int getDeviation();
	
	/**
	 * 技能目标数量。即，技能在攻击范围内最大可对多少目标造成伤害
	 * 
	 * @return
	 */
	public int getNum();
	
	/**
	 * 技能公共CD
	 * 
	 * @return
	 */
	public int getGcd();
	
	/**
	 * 技能CD
	 * 
	 * @return
	 */
	public int getCd();
	
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
	 * 指示技是否在CD中。在CD中返回true，否则返回false
	 * 
	 * @param currentTime 当前时间（毫秒）
	 * @return
	 */
	public boolean inCd(long currentTime);
	
	/**
	 * 指示技能是否由被动技能触发释放
	 * 
	 * @return
	 */
	public boolean isTriggered();
	
}
