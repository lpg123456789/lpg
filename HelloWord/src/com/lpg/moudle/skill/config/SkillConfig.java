package com.lpg.moudle.skill.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

/**
 * 技能配置
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年1月22日 上午10:23:14
 */
public class SkillConfig {

	private static SkillConfig instance;

	public static SkillConfig getInstance() {
		if (null != instance) {
			return instance;
		}
		return instance = new SkillConfig();
	}

	static {
		try {
			SkillConfig.getInstance().loadAndCache();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 技能最高等级
	 */
	private int maxLevel = 0;

	/**
	 * 技能id到技能等级map的映射表
	 * key：skillId
	 * value：{key:skillLevel, value:ActiveSKillTemplate}
	 */
	private HashMap<Integer, HashMap<Integer, ActiveSKillTemplate>> idToSKills = new HashMap<>();

	/**
	 * 技能id到技能开放等级的映射表
	 * key：skillId
	 * value：技能开放等级
	 */
	private HashMap<Integer, Integer> idToOpenLevel = new HashMap<>();

	/**
	 * 技能等级到升级配置的映射表
	 * key：skillLevel
	 * value：SkillUpTemplate
	 */
	private HashMap<Integer, ActiveSkillUpTemplate> levelToUpTemplate = new HashMap<>();

	/**
	 * 技能id到最高等级的配置表
	 * key：skillId
	 * value：maxLevel
	 */
	private HashMap<Integer, Integer> idToMaxTemplateLevel = new HashMap<>();
	
	/**
	 * 被动技能id到被动技能配置模版的映射表
	 * key：被动技能id
	 * value：PassiveSkillTemplate
	 */
	private HashMap<Integer, PassiveSkillTemplate> idToPassiveTemplate = new HashMap<>();
	
	/**
	 * 技能增强id到模版的配置表
	 * key：技能增强id
	 * SkillHeightenTemplate
	 */
	private HashMap<Integer, SkillHeightenTemplate> idToSkillHeightenTemplate = new HashMap<>();

	/**
	 * 战士神罚主技能id
	 */
	private int warriorGodPenaltyMainSkillId;

	/**
	 * 战士神罚子技能id（buff释放的技能）
	 */
	private int warriorGodPenaltySubSkillId;

	/**
	 * 法师神罚主技能id
	 */
	private int mageGodPenaltyMainSkillId;

	/**
	 * 法师神罚子技能id（buff释放的技能）
	 */
	private int mageGodPenaltySubSkillId;
	
	/**
	 * 牧师神罚主技能id
	 */
	private int priestGodPenaltyMainSkillId;

	/**
	 * 牧师神罚子技能id（buff释放的技能）
	 */
	private int priestGodPenaltySubSkillId;

	
	protected void loadAndCache() {
		
	}
	
	/**
	 * 获取技能的最高模版配置等级
	 * 
	 * @param id
	 * @return
	 */
	public int getMaxTemplateLevel(int id) {
		if (idToMaxTemplateLevel.containsKey(id)) {
			return idToMaxTemplateLevel.get(id);
		}
		return 1;
	}

	/**
	 * 技能最高等级
	 * 
	 * @return
	 */
	public int getMaxLevel() {
		return maxLevel;
	}

	/**
	 * 获取主动技能配置模版
	 * 
	 * @param id 技能ID
	 * @param level 技能等级
	 * @return
	 */
	public ActiveSKillTemplate getActiveSkillTemplate(int id, int level) {
		HashMap<Integer, ActiveSKillTemplate> m = idToSKills.get(id);
		if (null != m) {
			if (m.containsKey(level)) {
				return m.get(level);
			}

			// 如果技能模版配置中的最大等级小于技能升级中的配置，则取最大等级的模版
			int maxTemplateLevel = getMaxTemplateLevel(id);
			if (level > maxTemplateLevel) {
				return m.get(getMaxTemplateLevel(id));
			}
		}
		return null;
	}
	
	/**
	 * 获取被动技能模版
	 * 
	 * @param id 被动技能的模版id
	 * @return
	 */
	public PassiveSkillTemplate getPassiveSKillTemplate(int id) {
		return idToPassiveTemplate.get(id);
	}
	
	/**
	 * 获取技能增强模版
	 * 
	 * @param id 技能增强的模版id
	 * @return
	 */
	public SkillHeightenTemplate getSkillHeightenTemplate(int id) {
		return idToSkillHeightenTemplate.get(id);
	}

	/**
	 * 获取技能开放等级
	 * 
	 * @param id
	 * @return
	 */
	public int getSkillOpenLevel(int id) {
		if (idToOpenLevel.containsKey(id)) {
			return idToOpenLevel.get(id);
		}
		return 0;
	}

	/**
	 * 获取技能升级配置模版
	 * 
	 * @param level
	 * @return
	 */
	public ActiveSkillUpTemplate getSkillUpTemplate(int level) {
		return levelToUpTemplate.get(level);
	}

}
