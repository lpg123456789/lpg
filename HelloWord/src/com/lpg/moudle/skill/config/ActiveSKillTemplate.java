package com.lpg.moudle.skill.config;

import java.util.ArrayList;

import org.json.JSONObject;

import com.lpg.moudle.buff.BuffInfo;

/**
 * 主动技能模版。ActiveSkillTemplate是SkillConfigTemplate的封装类
 * 
 */
public class ActiveSKillTemplate {
	
	//private SkillConfigTemplate rawTemplate;
	
	private ArrayList<BuffInfo> buffs = new ArrayList<>();

	public ActiveSKillTemplate(JSONObject data) {
//		this.rawTemplate = new SkillConfigTemplate(data);
//		if(!StringUtils.isEmpty(rawTemplate.getBuffId())) {
//			int[] buffIds = StringUtils.toIntArray(rawTemplate.getBuffId(), ",");
//			int[] buffPros = StringUtils.toIntArray(rawTemplate.getBuffPro(), ",");
//			int[] buffTargets = StringUtils.toIntArray(rawTemplate.getBuffTarget(), ",");
//			int[] buffTimes = StringUtils.toIntArray(rawTemplate.getBuffTime(), ",");
//			
//			int len = buffIds.length;
//			for(int i = 0; i < len; ++i) {
//				buffs.add(new BuffInfo(buffIds[i], buffPros[i], buffTargets[i], buffTimes[i]));
//			}
//		}
	}

	/**
	 * 获取技能配置的原始模版
	 * 
	 * @return
	 */
//	public SkillConfigTemplate getRawTemplate() {
//		return rawTemplate;
//	}

	/**
	 * 技能CD
	 * 作者: 单位毫秒
	 */
	public int getCd() {
		return 0;
	}
	
	/**
	 * 技能GCD
	 * 作者: 技能施放后，角色技能列表中属于该技能组的其他技能均进入的公共冷却时间，单位毫秒 如果某技能已有CD，则判定当前剩余CD与GCD的时长，当前CD＞GCD，取当前CD，否则技能CD改为GCD时长
	 */
	public int getGcd() {
		return 0;
	}

	/**
	 * PVP百分比
	 * ProW: 技能攻击玩家时攻击伤害百分比取值
	 */
	public int getPvpPer() {
		return 0;
	}
	
	/**
	 * 获取PVE魔法百分比
	 * ProW: 技能攻击怪物时攻击造成魔法伤害百分比取值
	 */
	public int getPveMPer() {
		return 0;
	}
	
	/**
	 * 获取PVP魔法百分比
	 * ProW: 技能攻击玩家时攻击造成魔法伤害百分比取值
	 */
	public int getPvpMPer() {
		return 0;
	}

	/**
	 * 技能等级
	 * ProW: 技能等级，同一技能多个等级共用一个技能id
	 */
	public int getLevel() {
		return 0;
	}

	/**
	 * 施法范围
	 * ProW: 角色离目标多少距离可施放该技能 单位：格
	 */
	public int getCastRange() {
		return 0;
	}

	/**
	 * 目标数量
	 * 作者: 技能最多可同时攻击范围内的目标数量 1为单体,大于1为群攻
	 */
	public int getNum() {
		return 0;
	}
	
	/**
	 * 图标ID
	 * @return
	 */
	public int getIcon() {
		return 0;
	}

	/**
	 * PVE固定伤害
	 * ProW: 技能攻击怪物时固定伤害取值
	 */
	public int getPveHurt() {
		return 0;
	}

	/**
	 * 攻击范围
	 * 作者: 1、单体 2、自身方形    参数1:范围(格子)    以自身点为中心的范围，只能配置奇数    3=3*3 5=5*5 以此类推 3、目标方形    参数1:同上 4、身前矩形    攻击自身前1格开始，一定范围的目标    参数1:长度（格子）    参数2:宽度（格子） 5、位移单体技能    跳跃到目标前1格，并造成单体攻击，最大位移范围=施法范围
	 */
	public int getAtkRange() {
		return 0;
	}

	/**
	 * 参数1
	 */
	public int getPara1() {
		return 0;
	}

	/**
	 * 参数2
	 */
	public int getPara2() {
		return 0;
	}

	/**
	 * 攻击偏移量
	 * ProW: 技能施放时实际生效攻击范围偏移的格子数，正数为向玩家前方偏移，负数为向玩家后方偏移
	 */
	public int getDeviation() {
		return 0;
	}

	/**
	 * 技能分类
	 * 1：玩家主动技能 2：玩家怒气技能 3：怪物主动技能 0：其他技能
	 */
	public int getType() {
		return 0;
	}
	
	/**
	 * 技能作用类型
	 * ProW: 1:伤害技能 2:回复技能 0:无攻击效果的辅助技能
	 */
	public int getEffect() {
		return 0;
	}

	/**
	 * 目标选择
	 * 作者: 技能目标选择的对象 1：敌方 2：友方 3：仅自己
	 */
	public int getTarget() {
		return 0;
	}

	/**
	 * PVE百分比
	 * ProW: 技能攻击怪物时攻击伤害百分比取值
	 */
	public int getPvePer() {
		return 0;
	}

	/**
	 * 技能名称
	 * @return
	 */
	public String getName() {
		return "";
	}

	/**
	 * 技能ID
	 * @return
	 */
	public int getId() {
		return 0;
	}

	/**
	 * 技能描述
	 * @return
	 */
	public String getDescribe() {
		return "";
	}

	/**
	 * PVP固定伤害
	 * ProW: 技能攻击玩家时固定伤害取值
	 */
	public int getPvpHurt() {
		return 0;
	}

	/**
	 * 技能相关的BUFF列表
	 * 
	 * @return
	 */
	public ArrayList<BuffInfo> getBuffs() {
		return this.buffs;
	}
}
