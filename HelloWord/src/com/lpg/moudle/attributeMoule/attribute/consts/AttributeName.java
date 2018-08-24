package com.lpg.moudle.attributeMoule.attribute.consts;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.lpg.moudle.attributeMoule.attribute.pb.AttributeType;
/**
 * 属性名常量
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年1月15日 下午4:13:36
 */
public class AttributeName {
	
	/**等级*/
	public static final String LEVEL = "level";
	
	/**转生等级*/
	public static final String REIN = "rein";
	
	/**经验*/
	public static final String EXP = "exp";
	
	/**经验上限*/
	public static final String EXP_MAX = "expMax";
	
	/**VIP等级*/
	public static final String VIP = "vip";
	
	/**阵营*/
	public static final String CAMP = "camp";
	
	/**血量*/
	public static final String HP = "hp";
	
	/**血量上限*/
	public static final String HP_MAX = "hpMax";
	
	/**生命回复*/
	public static final String HP_REC = "hpRec";
	
	/**攻击力*/
	public static final String ATK = "atk";
	
	/**物理防御*/
	public static final String PDEF = "pdef";
	
	/**魔法防御*/
	public static final String MDEF = "mdef";
	
	/**物理穿透*/
	public static final String PPEN = "ppen";
	
	/**魔法穿透*/
	public static final String MPEN = "mpen";
	
	/**移动速度*/
	public static final String SPEED = "speed";
	
	/**护盾值*/
	public static final String SD = "sd";
	
	/**护盾最大值*/
	public static final String SHIELD = "shield";
	
	/**护盾回复*/
	public static final String SHIELD_REC = "shieldRec";
	
	/**护盾减伤*/
	public static final String SHIELD_RED = "shieldRed";
	
	/**暴击几率*/
	public static final String CRIT = "crit";
	
	/**暴击伤害*/
	public static final String CRIT_DMG = "critDmg";
	
	/**抗暴几率*/
	public static final String CRIT_RES = "critRes";
	
	/**抗暴减少伤害*/
	public static final String CRIT_RED = "critRed";
	
	/**命中率*/
	public static final String HIT = "hit";
	
	/**闪避率*/
	public static final String DODGE = "dodge";
	
	/**伤害加成*/
	public static final String DMG_ADD = "dmgAdd";
	
	/**伤害减免*/
	public static final String DMG_RED = "dmgRed";
	
	/**眩晕几率*/
	public static final String DIZZY = "dizzy";
	
	/**眩晕抵抗*/
	public static final String DIZZY_RES = "dizzyRes";
	
	/**战士增伤*/
	public static final String ZS_ADD = "zsAdd";
	
	/**战士减伤*/
	public static final String ZS_RED = "zsRed";
	
	/**法师增伤*/
	public static final String FS_ADD = "fsAdd";
	
	/**法师减伤*/
	public static final String FS_RED = "fsRed";
	
	/**牧师增伤*/
	public static final String MS_ADD = "msAdd";
	
	/**牧师减伤*/
	public static final String MS_RED = "msRed";
	
	/**玩家增伤*/
	public static final String PLAYER_ADD = "playerAdd";
	
	/**玩家减伤*/
	public static final String PLAYER_RED = "playerRed";
	
	/**神罚值*/
	public static final String GOD_PENALTY = "godPenalty";
	
	/**神罚值上限*/	
	public static final String GOD_PENALTY_MAX = "godPenaltyMax";
	
	/**神罚回复*/	
	public static final String GOD_PENALTY_REC = "godPenaltyRec";

	/**武器部位属性百分比提升*/	
	public static final String WEAPON_ADD = "weaponAdd";
	
	/**衣服部位属性百分比提升*/	
	public static final String CLOTHES_ADD = "clothesAdd";
	
	/**护腕部位属性百分比提升*/	
	public static final String BRACERS_ADD = "bracersAdd";
	
	/**戒指部位属性百分比提升*/	
	public static final String RING_ADD = "ringAdd";
	
	/**头盔部位属性百分比提升*/	
	public static final String HELMET_ADD = "helmetAdd";
	
	/**项链部位属性百分比提升*/	
	public static final String NECKLACE_ADD = "necklaceAdd";
	
	/**腰带部位属性百分比提升*/	
	public static final String BELT_ADD = "beltAdd";
	
	/**鞋子部位属性百分比提升*/	
	public static final String SHOES_ADD = "shoesAdd";
	
	/**关卡收益经验加成*/	
	public static final String EXP_ADD = "expAdd";
	
	/**关卡收益金币加成*/	
	public static final String GOLD_ADD = "goldAdd";
	
	/**固定增伤*/	
	public static final String FDAMAGE_ADD = "fdamageAdd";
	
	/**固定减伤*/	
	public static final String FDAMAGE_REDUCE = "fdamageReduce";
	
	/**固定暴伤*/	
	public static final String CRIT_ADD = "critAdd";
	
	/**固定抗暴*/	
	public static final String CRIT_REDUCE = "critReduce";
	
	/**附加战力（在战斗中不生效，仅用于1:1增加战斗力数据，让战斗力看起来更好看的东西）*/	
	public static final String POW = "pow";
	
	/**血量万分比加成（分子部分）*/	
	public static final String HPMAX_PER = "hpMaxPer";
	
	/**攻击万分比加成（分子部分）*/	
	public static final String ATK_PER = "atkPer";
	
	/**物理防御万分比加成（分子部分）*/	
	public static final String PDEF_PER = "pdefPer";
	
	/**魔法防御万分比加成（分子部分）*/	
	public static final String MDEF_PER = "mdefPer";
	/**神罚对怪物魔焰伤害*/	
	public static final String GOD_PENALTY_ADD_BEAST = "godPenaltyAddBeast";
	/**神罚对玩家魔焰伤害*/	
	public static final String GOD_PENALTY_ADD_PLAYER = "godPenaltyAddPlayer";
	/**神罚神圣伤害*/	
	public static final String GOD_PENALTY_HOLY = "godPenaltyHoly";
	
	/**护盾百分比*/	
	public static final String SHIELD_PER = "shieldPer";
	/**暗影伤害*/	
	public static final String SHADOW_DMG = "shadowDmg";
	/**神圣伤害*/	
	public static final String HOLY_DMG = "holyDmg";
	
	/**
	 * 属性名到属性类型值的映射表
	 */
	private static HashMap<String, Integer> nameToType = new HashMap<>();
	
	/**
	 * 属性类型到属性名的映射表
	 */
	private static HashMap<Integer, String> typeToName = new HashMap<>();
	
	/**
	 * 万分比属性--用于对攻击，物理防御，魔法防御，血量上限的万分比加成
	 */
	private static HashMap<String, Integer> perAttrName = new HashMap<>();
	
	// 初始化映射表
	static {
		// 初始化属性名到属性类型的映射表
		nameToType.put(AttributeName.HP, AttributeType.HP_VALUE);
		nameToType.put(AttributeName.HP_MAX, AttributeType.HPMAX_VALUE);
		nameToType.put(AttributeName.ATK, AttributeType.ATK_VALUE);
		nameToType.put(AttributeName.PDEF, AttributeType.PDEF_VALUE);
		nameToType.put(AttributeName.MDEF, AttributeType.MDEF_VALUE);
		nameToType.put(AttributeName.PPEN, AttributeType.PPEN_VALUE);
		nameToType.put(AttributeName.MPEN, AttributeType.MPEN_VALUE);
		nameToType.put(AttributeName.SD, AttributeType.SD_VALUE);
		nameToType.put(AttributeName.SPEED, AttributeType.SPEED_VALUE);
		nameToType.put(AttributeName.SHIELD, AttributeType.SHIELD_VALUE);
		nameToType.put(AttributeName.CRIT, AttributeType.CRIT_VALUE);
		nameToType.put(AttributeName.HIT, AttributeType.HIT_VALUE);
		nameToType.put(AttributeName.DODGE, AttributeType.DODGE_VALUE);
		nameToType.put(AttributeName.DMG_ADD, AttributeType.DMGADD_VALUE);
		nameToType.put(AttributeName.DIZZY, AttributeType.DIZZY_VALUE);
		nameToType.put(AttributeName.ZS_ADD, AttributeType.ZS_ADD_VALUE);
		nameToType.put(AttributeName.ZS_RED, AttributeType.ZS_RED_VALUE);
		nameToType.put(AttributeName.FS_ADD, AttributeType.FS_ADD_VALUE);
		nameToType.put(AttributeName.FS_RED, AttributeType.FS_RED_VALUE);
		nameToType.put(AttributeName.MS_ADD, AttributeType.MS_ADD_VALUE);
		nameToType.put(AttributeName.MS_RED, AttributeType.MS_RED_VALUE);
		nameToType.put(AttributeName.PLAYER_ADD, AttributeType.PLAYER_ADD_VALUE);
		nameToType.put(AttributeName.PLAYER_RED, AttributeType.PLAYER_RED_VALUE);
		nameToType.put(AttributeName.GOD_PENALTY_MAX, AttributeType.GOD_PENALTY_MAX_VALUE);
		nameToType.put(AttributeName.GOD_PENALTY, AttributeType.GOD_PENALTY_VALUE);
		nameToType.put(AttributeName.GOD_PENALTY_REC, AttributeType.GOD_PENALTY_REC_VALUE);
		nameToType.put(AttributeName.WEAPON_ADD, AttributeType.WEAPON_ADD_VALUE);
		nameToType.put(AttributeName.CLOTHES_ADD, AttributeType.CLOTHES_ADD_VALUE);
		nameToType.put(AttributeName.BRACERS_ADD, AttributeType.BRACERS_ADD_VALUE);
		nameToType.put(AttributeName.RING_ADD, AttributeType.RING_ADD_VALUE);
		nameToType.put(AttributeName.HELMET_ADD, AttributeType.HELMET_ADD_VALUE);
		nameToType.put(AttributeName.NECKLACE_ADD, AttributeType.NECKLACE_ADD_VALUE);
		nameToType.put(AttributeName.BELT_ADD, AttributeType.BELT_ADD_VALUE);
		nameToType.put(AttributeName.SHOES_ADD, AttributeType.SHOES_ADD_VALUE);
		nameToType.put(AttributeName.EXP_ADD, AttributeType.EXP_ADD_VALUE);
		nameToType.put(AttributeName.GOLD_ADD, AttributeType.GOLD_ADD_VALUE);
		nameToType.put(AttributeName.FDAMAGE_ADD, AttributeType.FDAMAGE_ADD_VALUE);
		nameToType.put(AttributeName.FDAMAGE_REDUCE, AttributeType.FDAMAGE_REDUCE_VALUE);
		nameToType.put(AttributeName.CRIT_ADD, AttributeType.CRIT_ADD_VALUE);
		nameToType.put(AttributeName.CRIT_REDUCE, AttributeType.CRIT_REDUCE_VALUE);
		nameToType.put(AttributeName.POW, AttributeType.POW_VALUE);
		nameToType.put(AttributeName.HPMAX_PER, AttributeType.HPMAX_PER_VALUE);
		nameToType.put(AttributeName.ATK_PER, AttributeType.ATK_PER_VALUE);
		nameToType.put(AttributeName.PDEF_PER, AttributeType.PDEF_PER_VALUE);
		nameToType.put(AttributeName.MDEF_PER, AttributeType.MDEF_PER_VALUE);
		nameToType.put(AttributeName.GOD_PENALTY_ADD_BEAST, AttributeType.GOD_PENALTY_ADD_BEAST_VALUE);
		nameToType.put(AttributeName.GOD_PENALTY_ADD_PLAYER, AttributeType.GOD_PENALTY_ADD_PLAYER_VALUE);
		nameToType.put(AttributeName.GOD_PENALTY_HOLY, AttributeType.GOD_PENALTY_HOLY_VALUE);
		nameToType.put(AttributeName.SHADOW_DMG, AttributeType.DMG_SHADOW_VALUE);
		nameToType.put(AttributeName.HOLY_DMG, AttributeType.DMG_HOLY_VALUE);
		nameToType.put(AttributeName.SHIELD_PER, AttributeType.SHIELD_PER_VALUE);
		
		perAttrName.put(AttributeName.HPMAX_PER, AttributeType.HPMAX_PER_VALUE);
		perAttrName.put(AttributeName.ATK_PER, AttributeType.ATK_PER_VALUE);
		perAttrName.put(AttributeName.PDEF_PER, AttributeType.PDEF_PER_VALUE);
		perAttrName.put(AttributeName.MDEF_PER, AttributeType.MDEF_PER_VALUE);
		perAttrName.put(AttributeName.SHIELD_PER, AttributeType.SHIELD_PER_VALUE);
		// 初始化属性类型到属性名的映射表
		Iterator<Entry<String, Integer>> iterator = nameToType.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, Integer> next = iterator.next();
			typeToName.put(next.getValue(), next.getKey());
		}
	}
	
	/**
	 * 检测指定的字段名是否属性名
	 * 
	 * @param attributeName
	 * @return
	 */
	public static boolean isAttibuteName(String attributeName) {
		return nameToType.containsKey(attributeName);
	}
	
	/**
	 * 是否是用于对攻击，物理防御，魔法防御，血量上限的万分比加成的属性
	 * @param attributeName
	 * @return
	 */
	public static boolean isPerAttibuteName(String attributeName) {
		return perAttrName.containsKey(attributeName);
	}

	/**
	 * 通过属性名获取属性类型
	 * 
	 * @param attributeName
	 * @return
	 */
	public static int getAttributeType(String attributeName) {
		if(nameToType.containsKey(attributeName)) {
			return nameToType.get(attributeName);
		}
		return 0;
	}
	
	/**
	 * 通过属性类型获取属性名
	 * 
	 * @param attibuteType
	 * @return
	 */
	public static String getAttributeName(int attributeType) {
		if(typeToName.containsKey(attributeType)) {
			return typeToName.get(attributeType);
		}
		return null;
	}
	
}
