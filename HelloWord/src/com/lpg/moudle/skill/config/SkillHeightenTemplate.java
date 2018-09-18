package com.lpg.moudle.skill.config;

import org.json.JSONObject;

/**
 * 技能增强模版
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年5月3日 下午12:00:51
 */
public class SkillHeightenTemplate {
	/**
	 * 原始模版
	 */
//	private SkillHeightenConfigTemplate rawTemplate;
	
	/**
	 * 增强的技能列表
	 */
	private int[] skillIds;
	
	public SkillHeightenTemplate(JSONObject data) {
//		this.rawTemplate = new SkillHeightenConfigTemplate(data);
//		this.skillIds = StringUtils.toIntArray(rawTemplate.getSkillId(), ",");
	}
	

	/**
	 * 获取PVP物理百分比
	 * ProW: 技能攻击玩家时攻击造成物理伤害百分比取值
	 */
	public int getPvpPer(){
		return 0;
	}
	
	/**
	 * 获取PVE魔法百分比
	 * ProW: 技能攻击怪物时攻击造成魔法伤害百分比取值
	 */
	public int getPveMPer(){
		return 0;
	}
	
	/**
	 * 获取PVE固定伤害
	 * ProW: 技能攻击怪物时固定伤害取值
	 */
	public int getPveHurt(){
		return 0;
	}
	
	/**
	 * 获取增强技能id。可能同时增强多个技能
	 */
	public int[] getSkillIds(){
		return this.skillIds;
	}
	
	/**
	 * 获取PVE物理百分比
	 * ProW: 技能攻击怪物时攻击造成物理伤害百分比取值
	 */
	public int getPvePer(){
		return 0;
	}
	
	/**
	 * 获取增强效果ID
	 */
	public int getId(){
		return 0;
	}
	
	/**
	 * 获取PVP固定伤害
	 * ProW: 技能攻击玩家时固定伤害取值
	 */
	public int getPvpHurt(){
		return 0;
	}
	
	/**
	 * 获取PVP魔法百分比
	 * ProW: 技能攻击玩家时攻击造成魔法伤害百分比取值
	 */
	public int getPvpMPer(){
		return 0;
	}
	
	/**
	 * 获取技能增强效果描述
	 */
	public String getDesc(){
		return "";
	}
	
	/**
	 * 获取buffId
	 * 作者: 对应BUFF表的ID
	 */
	public int getBuffId(){
		return 0;
	}
	
	/**
	 * 获取buff几率（万分率）
	 * 作者: buff生效的几率
	 */
	public int getOdds(){
		return 0;
	}
	
	/**
	 * 获取buff生效目标
	 * 作者: 1:自己（技能释放者） 2：技能目标
	 */
	public int getBuffTarget(){
		return 0;
	}
	
	/**
	 * 获取buff持续时间（毫秒）
	 */
	public int getBuffTime(){
		return 0;
	}
}
