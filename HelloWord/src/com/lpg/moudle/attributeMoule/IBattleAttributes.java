package com.lpg.moudle.attributeMoule;

import java.lang.reflect.Method;
import java.util.HashSet;

/**
 * 战斗属性
 * @author Administrator
 *
 */
public interface IBattleAttributes {
	
	/**
	 * 获取所有的属性名
	 * 
	 * @return
	 */
	public HashSet<String> getAttributeNames();
	
	/**
	 * 获取指定属性的getter
	 * 
	 * @param attributeName
	 * @return
	 */
	public Method getGetter(String attributeName);
	
	/**
	 * 获取指定属性的setter
	 * 
	 * @param attributeName
	 * @return
	 */
	public Method getSetter(String attributeName);
	
	/**
	 * 根据属性名获取属性值
	 * 
	 * @param attributeName
	 * @return
	 */
	public Number getAttribute(String attributeName);
	
	/**
	 * 根据属性名获取属性值---不包含buff属性---用于计算战斗力
	 * 
	 * @param attributeName
	 * @return
	 */
	public Number getNoBuffAttribute(String attributeName);
	
	
	/**
	 * 根据属性名获取kind类型属性值
	 * 
	 * @param attributeName
	 * @param kind
	 * @return
	 */
	public Number getAttribute(String attributeName,int kind);
	
	/**
	 * 根据属性类型获取属性值
	 * 
	 * @param attributeType
	 * @return
	 */
	public Number getAttribute(int attributeType);
	
	/**
	 * 检测是否有指定的属性
	 * 
	 * @param attributeName 属性名
	 * @return
	 */
	public boolean hasAttribute(String attributeName);
	
	/**
	 * 检测是否有指定的属性
	 * 
	 * @param attributeType 属性类型
	 * @return
	 */
	public boolean hasAttribute(int attributeType);
	
	/**
	 * 血量
	 * @return
	 */
	public long getHp();
	
	/**
	 * 设置血量
	 * @param hp
	 */
	public void setHp(long value);
	
	/**
	 * 重置血量
	 */
	public void resetHp();
	
	/**
	 * 获取护盾值
	 */
	public int getSd();
	
	/**
	 * 设置护盾值
	 * @param sd
	 */
	public void setSd(int sd);
	
	/**
	 * 重置护盾值
	 */
	public void resetSd();
	
	/**
	 * 血量上限
	 * @return
	 */
	public long getHpMax();
	
	/**
	 * 设置血量上限
	 */
	public void setHpMax(int kind, long value);
	
	/**
	 * 攻击
	 * @return
	 */
	public int getAtk();
	
	/**
	 * 设置攻击力
	 * @param atk
	 */
	public void setAtk(int kind, int value);
	
	/**
	 * 物理防御
	 * @return
	 */
	public int getPdef();
	
	/**
	 * 设置物理防御
	 * @param pdef
	 */
	public void setPdef(int kind, int value);
	
	/**
	 * 魔法防御
	 * @return
	 */
	public int getMdef();
	
	/**
	 * 设置魔法防御
	 * @param mdef
	 */
	public void setMdef(int kind, int value);
	/**
	 * 物理穿透
	 * @return
	 */
	public int getPpen();
	
	/**
	 * 设置物理穿透
	 * @param ppen
	 */
	public void setPpen(int kind, int value);
	
	/**
	 * 魔法穿透
	 * @return
	 */
	public int getMpen();
	
	/**
	 * 设置魔法穿透
	 * @return
	 */
	public void setMpen(int kind, int value);
	
	/**
	 * 生命回复
	 * @return
	 */
	public int getHpRec();
	
	/**
	 * 设置生命回复
	 * @return
	 */
	public void setHpRec(int kind, int value);	
	
	/**
	 * 移动速度
	 * @return
	 */
	public int getSpeed();
	
	/**
	 * 设置移动速度
	 * @return
	 */
	public void setSpeed(int kind, int value);
	
	/**
	 * 护盾值上限。注意，此属性是护盾上限值。当前护盾值是字段名为sd的属性
	 * @return
	 */
	public int getShield();
	
	/**
	 * 设置护盾值
	 */
	public void setShield(int kind, int value);
	
	/**
	 * 护盾回复
	 * @return
	 */
	public int getShieldRec();
	
	/**
	 * 设置护盾回复值
	 * @param shieldRec
	 */
	public void setShieldRec(int kind, int value);
	
	/**
	 * 护盾减伤(万分比)
	 * @return
	 */
	public int getShieldRed();
	
	/**
	 * 设置护盾减伤
	 * @param shieldRed
	 */
	public void setShieldRed(int kind, int value);
	
	/**
	 * 暴击几率(万分比)
	 * @return
	 */
	public int getCrit();
	
	/**
	 * 设置暴击几率(万分比)
	 * @return
	 */
	public void setCrit(int kind, int value);
	
	/**
	 * 暴击伤害(万分比)
	 * @return
	 */
	public int getCritDmg();
	
	/**
	 * 设置暴击伤害(万分比)
	 * @return
	 */
	public void setCritDmg(int kind, int value);
	
	/**
	 * 抗暴几率(万分比)
	 * @return
	 */
	public int getCritRes();
	
	/**
	 * 设置抗暴几率(万分比)
	 * @return
	 */
	public void setCritRes(int kind, int value);
	
	/**
	 * 抗暴伤害(万分比)
	 * @return
	 */
	public int getCritRed();
	
	/**
	 * 设置抗暴伤害(万分比)
	 * @return
	 */
	public void setCritRed(int kind, int value);
	
	/**
	 * 命中率(万分比)
	 * @return
	 */
	public int getHit();
	
	/**
	 * 设置命中率(万分比)
	 * @return
	 */
	public void setHit(int kind, int value);
	
	/**
	 * 闪避率(万分比)
	 * @return
	 */
	public int getDodge();
	
	/**
	 * 设置闪避率(万分比)
	 * @return
	 */
	public void setDodge(int kind, int value);
	
	/**
	 * 伤害加成(万分比)
	 * @return
	 */
	public int getDmgAdd();
	
	/**
	 * 设置伤害加成(万分比)
	 * @return
	 */
	public void setDmgAdd(int kind, int value);
	
	/**
	 * 伤害减免(万分比)
	 * @return
	 */
	public int getDmgRed();
	
	/**
	 * 设置伤害减免(万分比)
	 * @return
	 */
	public void setDmgRed(int kind, int value);
	
	/**
	 * 眩晕几率(万分比)
	 * @return
	 */
	public int getDizzy();
	
	/**
	 * 设置眩晕几率(万分比)
	 * @return
	 */
	public void setDizzy(int kind, int value);
	
	/**
	 * 眩晕抵抗(万分比)
	 * @return
	 */
	public int getDizzyRes();
	
	/**
	 * 眩晕抵抗(万分比)
	 * @return
	 */
	public void setDizzyRes(int kind, int value);
	
	/**
	 * 战士增伤(万分比)
	 */
	public int getZsAdd();
	
	/**
	 * 设置战士增伤
	 * @param kind
	 * @param value
	 */
	public void setZsAdd(int kind, int value);
	
	/**
	 * 战士减伤(万分比)
	 */
	public int getZsRed();
	
	/**
	 * 设置战士减伤
	 * @param kind
	 * @param value
	 */
	public void setZsRed(int kind, int value);
	
	/**
	 * 法师增伤(万分比)
	 */
	public int getFsAdd();
	
	/**
	 * 设置法师增伤(万分比)
	 * @param kind
	 * @param value
	 * @return
	 */
	public void setFsAdd(int kind, int value);
	
	/**
	 * 法师减伤(万分比)
	 */
	public int getFsRed();
	
	/**
	 * 设置法师减伤(万分比)
	 * @param kind
	 * @param value
	 */
	public void setFsRed(int kind, int value);
	
	/**
	 * 牧师增伤(万分比)
	 */
	public int getMsAdd();
	
	/**
	 * 设置牧师增伤(万分比)
	 * @param kind
	 * @param value
	 */
	public void setMsAdd(int kind, int value);
	
	/**
	 * 牧师减伤(万分比)
	 */
	public int getMsRed();
	
	/**
	 * 设置牧师减伤(万分比)
	 * @param kind
	 * @param value
	 */
	public void setMsRed(int kind, int value);
	
	/**
	 * 玩家增伤(万分比)
	 */
	public int getPlayerAdd();
	
	/**
	 * 设置玩家增伤(万分比)
	 * @param kind
	 * @param value
	 */
	public void setPlayerAdd(int kind, int value);
	
	/**
	 * 玩家减伤(万分比)
	 */
	public int getPlayerRed();
	
	/**
	 * 设置玩家减伤(万分比)
	 * @param kind
	 * @param value
	 */
	public void setPlayerRed(int kind, int value);
	
	/**
	 * 神罚值上限
	 */	
	public int getGodPenaltyMax();
	
	/**
	 * 设置神罚值上限
	 * @param value
	 */
	public void setGodPenaltyMax(int value);
	
	/**
	 * 神罚值
	 */
	public int getGodPenalty();
	
	/**
	 * 设置神罚值
	 * @param value
	 */
	public void setGodPenalty(int value);
	
	/**
	 * 神罚回复
	 */	
	public int getGodPenaltyRec();
	
	/**
	 * 设置神罚回复
	 * @param kind
	 * @param value
	 */
	public void setGodPenaltyRec(int kind, int value);
	
	/**
	 * 获取武器部位属性百分比提升(万分比)
	 */
	public int getWeaponAdd();
	
	/**
	 * 设置武器部位属性百分比提升(万分比)
	 * @param kind
	 * @param value
	 */
	public void setWeaponAdd(int kind, int value);
	
	/**
	 * 获取衣服部位属性百分比提升(万分比)
	 */
	public int getClothesAdd();
	
	/**
	 * 设置衣服部位属性百分比提升(万分比)
	 * @param kind
	 * @param value
	 */
	public void setClothesAdd(int kind, int value);
	
	/**
	 * 获取护腕部位属性百分比提升(万分比)
	 * @return
	 */
	public int getBracersAdd();
	
	/**
	 * 设置护腕部位属性百分比提升(万分比)
	 * @param kind
	 * @param value
	 */
	public void setBracersAdd(int kind, int value);
	
	/**
	 * 获取戒指部位属性百分比提升(万分比)
	 * @return
	 */
	public int getRingAdd();
	
	/**
	 * 设置戒指部位属性百分比提升(万分比)
	 * @param kind
	 * @param value
	 */
	public void setRingAdd(int kind, int value);
	
	/**
	 * 获取头盔部位属性百分比提升(万分比)
	 * @return
	 */
	public int getHelmetAdd();
	
	/**
	 * 设置头盔部位属性百分比提升(万分比)
	 * @param kind
	 * @param value
	 */
	public void setHelmetAdd(int kind, int value);
	
	/**
	 * 获取项链部位属性百分比提升(万分比)
	 * @return
	 */
	public int getNecklaceAdd();
	
	/**
	 * 设置项链部位属性百分比提升(万分比)
	 * @param kind
	 * @param value
	 */
	public void setNecklaceAdd(int kind, int value);
	
	/**
	 * 获取腰带部位属性百分比提升(万分比)
	 * @return
	 */
	public int getBeltAdd();
	
	/**
	 * 设置腰带部位属性百分比提升(万分比)
	 * @param kind
	 * @param value
	 */
	public void setBeltAdd(int kind, int value);
	
	/**
	 * 获取鞋子部位属性百分比提升(万分比)
	 * @return
	 */
	public int getShoesAdd();
	
	/**
	 * 设置鞋子部位属性百分比提升(万分比)
	 * @param kind
	 * @param value
	 */
	public void setShoesAdd(int kind, int value);
	
	/**
	 * 获取关卡收益经验加成(万分比)
	 * @return
	 */
	public int getExpAdd();
	
	/**
	 * 设置关卡收益经验加成(万分比)
	 * @param kind
	 * @param value
	 */
	public void setExpAdd(int kind, int value);
	
	/**
	 * 获取关卡收益金币加成(万分比)
	 * @return
	 */
	public int getGoldAdd();
	
	/**
	 * 设置关卡收益金币加成(万分比)
	 * @param kind
	 * @param value
	 */
	public void setGoldAdd(int kind, int value);
	
	/**
	 * 获取固定增伤
	 * @return
	 */
	public int getFdamageAdd();
	
	/**
	 * 设置固定增伤
	 * @param kind
	 * @param value
	 */
	public void setFdamageAdd(int kind, int value);
	
	/**
	 * 获取固定减伤
	 * @return
	 */
	public int getFdamageReduce();
	
	/**
	 * 设置固定减伤
	 * @param kind
	 * @param value
	 */
	public void setFdamageReduce(int kind, int value);
	
	/**
	 * 获取固定暴伤
	 * @return
	 */
	public int getCritAdd();
	
	/**
	 * 设置固定暴伤
	 * @param kind
	 * @param value
	 */
	public void setCritAdd(int kind, int value);
	
	/**
	 * 获取固定抗暴
	 * @return
	 */
	public int getCritReduce();
	
	/**
	 * 设置固定抗暴
	 * @param kind
	 * @param value
	 */
	public void setCritReduce(int kind, int value);

	/**
	 * 获取虚拟战斗力
	 * @return
	 */
	public int getPow();
	
	/**
	 * 设置虚拟战斗力
	 * @param kind
	 * @param value
	 */
	public void setPow(int kind, int value);
	
	
	/**
	 * 获取血量万分比加成的分子部分
	 * @return
	 */
	public int getHpMaxPer();
	
	/**
	 * 设置血量万分比加成的分子部分
	 * @param kind
	 * @param value
	 */
	public void setHpMaxPer(int kind, int value);
	
	/**
	 * 获取攻击万分比加成的分子部分
	 * @return
	 */
	public int getAtkPer();
	
	/**
	 * 设置攻击万分比加成的分子部分
	 * @param kind
	 * @param value
	 */
	public void setAtkPer(int kind, int value);
	
	/**
	 * 获取物理防御万分比加成的分子部分
	 * @return
	 */
	public int getPdefPer();
	
	/**
	 * 设置物理防御万分比加成的分子部分
	 * @param kind
	 * @param value
	 */
	public void setPdefPer(int kind, int value);
	
	/**
	 * 获取魔法防御万分比加成的分子部分
	 * @return
	 */
	public int getMdefPer();
	
	/**
	 * 设置魔法防御万分比加成的分子部分
	 * @param kind
	 * @param value
	 */
	public void setMdefPer(int kind, int value);
	
	/**
	 * 获取怪物魔焰伤害
	 * @return
	 */
	public int getGodPenaltyAddBeast();
	
	/**
	 * 设置怪物魔焰伤害
	 * @param kind
	 * @param value
	 */
	public void setGodPenaltyAddBeast(int kind, int value);
	
	/**
	 * 获取对玩家魔焰伤害
	 * @return
	 */
	public int getGodPenaltyAddPlayer();
	
	/**
	 * 设置对玩家魔焰伤害
	 * @param kind
	 * @param value
	 */
	public void setGodPenaltyAddPlayer(int kind, int value);
	
	
	/**
	 * 获取护盾百分比
	 * @return
	 */
	public int getShieldPer();
	
	/**
	 * 设置护盾百分比
	 * @param kind
	 * @param value
	 */
	public void setShieldPer(int kind, int value);
	
	/**
	 * 获取暗影伤害
	 * @return
	 */
	public int getShadowDmg();
	
	/**
	 * 设置暗影伤害
	 * @param kind
	 * @param value
	 */
	public void setShadowDmg(int kind, int value);
	
	/**
	 * 获取神圣伤害
	 * @return
	 */
	public int getHolyDmg();
	
	/**
	 * 设置神圣伤害
	 * @param kind
	 * @param value
	 */
	public void setHolyDmg(int kind, int value);
	
}
