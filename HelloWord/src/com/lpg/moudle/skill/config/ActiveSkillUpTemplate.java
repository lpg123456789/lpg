package com.lpg.moudle.skill.config;

import org.json.JSONObject;

/**
 * 技能升级配置模版
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年1月22日 下午4:59:28
 */
public class ActiveSkillUpTemplate {
	
	/**
	 * 原始模版
	 */
	//private SkillUpConfigTemplate rawTemplate;
	
	/**
	 * 技能升级所需的等级类型。1：普通等级；2：转生等级
	 */
	private int levelType;
	
	/**
	 * 技能升级所需的等级。如果type为1，此值表示普通等级。type为2表示转生等级
	 */
	private int needLevel;
	
	public ActiveSkillUpTemplate(JSONObject data) {
//		this.rawTemplate = new SkillUpConfigTemplate(data);
//		int[] arr = StringUtils.toIntArray(rawTemplate.getLevelLimit(), "\\|");
//		this.levelType = arr[0];
//		this.needLevel = arr[1];
	}
	
//	public SkillUpConfigTemplate getRawTemplate() {
//		return rawTemplate;
//	}
	
	/**
	* 获取技能等级
	*/
	public int getLevel(){
		return 0;
	}
	
	/**
	* 获取升级所需金币
	* 作者: 当前等级→下一等级所需的熟练度
	*/
	public int getLevelUpGold(){
		return 0;
	}

	/**
	 * 技能升级所需的转生等级。1：普通等级；2：转生等级
	 * 
	 * @return
	 */
	public int getLevelType() {
		return this.levelType;
	}

	/**
	 * 技能升级所需的等级。如果type为1，此值表示普通等级。type为2表示转生等级
	 * 
	 * @return
	 */
	public int getNeedLevel() {
		return this.needLevel;
	}
	
	/**
	 * 获取技能升级所需的转生等级
	 * 
	 * @return
	 */
	public int getUpgradeNeedRein() {
		if(getLevelType() == 2) {
			return getNeedLevel();
		}
		return 0;
	}
	
	/**
	 * 获取技能升级所需的普通等级
	 * 
	 * @return
	 */
	public int getUpgradeNeedLevel() {
		if(getLevelType() == 1) {
			return getNeedLevel();
		}
		return 0;
	}
}
