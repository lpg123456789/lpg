package com.lpg.moudle.skill.config;

import org.json.JSONObject;

/**
 * 被动技能模版
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年5月3日 下午12:00:51
 */
public class PassiveSkillTemplate {
	/**
	 * 原始模版
	 */
	//private SkillPassiveConfigTemplate rawTemplate;
	
	public PassiveSkillTemplate(JSONObject data) {
		//this.rawTemplate = new SkillPassiveConfigTemplate(data);
	}
	
	/**
	 * 获取被动技能名
	 */
	public String getSkillName(){
		return "";
	}
	
	/**
	 * 获取触发间隔
	 * 作者: 单位，毫秒 触发后，在此时间内，不能再次触发
	 */
	public int getCd(){
		return 0;
	}
	
	/**
	 * 获取图标id
	 */
	public int getIcon(){
		return 0;
	}
	
	/**
	 * 获取被动技能id
	 * ProW: 与主动技能ID不能冲突
	 */
	public int getId(){
		return 0;
	}
	
	/**
	 * 获取触发几率（万分比）
	 */
	public int getTriggerProbability(){
		return 0;
	}
	
	/**
	 * 获取触发方式
	 * 作者: 1=攻击时有几率触发（本次攻击前触发，AOE技能只做一次几率判断） 2=受攻击时有几率触发（受到本次攻击前触发）
	 */
	public int getTriggerMode(){
		return 0;
	}
	
	/**
	 * 获取被动技能描述
	 */
	public String getDesc(){
		return "";
	}
	
	/**
	 * 获取触发效果生效目标
	 * 作者: 跟触发方式关联 1、攻击时触发：      1：自己      2：攻击对象  2、受击时触发：      1：自己      2：攻击自己的对象
	 */
	public int getTarget(){
		return 0;
	}
	
	/**
	 * 获取触发效果类型
	 * 作者: 1：触发一个buff/debuff 2：本次攻击伤害加成 3：触发一个技能
	 */
	public int getTriggeType(){
		return 0;
	}
	
	/**
	 * 获取参数1
	 * 作者: 1：触发一个buff/debuff    参数1：buff id    参数2：buff 时间（毫秒）  2：本次攻击伤害加成    参数1：伤害加成比例，万分比值  5：触发一个技能    参数1：技能id  参数2：技能等级
	 */
	public int getParameter1(){
		return 0;
	}
	
	/**
	 * 获取参数2
	 */
	public int getParameter2(){
		return 0;
	}
	
	/**
	 * 获取参数3
	 */
	public int getParameter3(){
		return 0;
	}
}
