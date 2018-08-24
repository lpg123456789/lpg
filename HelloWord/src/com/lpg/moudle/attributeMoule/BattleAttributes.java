package com.lpg.moudle.attributeMoule;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.lpg.moudle.attributeMoule.attribute.consts.AttributeName;
import com.lpg.utils.StringUtils;

/**
 * 角色战斗属性
 * 
 * @author 李俊豪、Brant
 * @mail   brtcoder@163.com
 * @date   2018年1月17日 上午11:59:03
 */
public class BattleAttributes implements IBattleAttributes {
	
	/**
	 * 属性名集合
	 */
	private static HashSet<String> attributeNames = new HashSet<>();
	
	/**
	 * 属性名到其getter的映射表
	 */
	private static HashMap<String, Method> nameToGetter = new HashMap<>();
	
	/**
	 * 属性名到其setter的映射表
	 */
	private static HashMap<String, Method> nameToSetter = new HashMap<>();

	// 初始化辅助属性
	static {
		Class<?> cls = IBattleAttributes.class;
		Method[] declaredMethods = cls.getDeclaredMethods();
		for(Method m : declaredMethods) {
			String methodName = m.getName();
			String attributeName = StringUtils.parseFieldName(m.getName());
			if(AttributeName.isAttibuteName(attributeName)) {
				if(methodName.startsWith("get")) {
					attributeNames.add(attributeName);
					nameToGetter.put(attributeName, m);
				}
				else if(methodName.startsWith("set")) {
					nameToSetter.put(attributeName, m);
				}
			}
		}
	}
	
	/**当前血量*/
	private AtomicLong hp = new AtomicLong(0);
	/**当前护盾*/
	private AtomicInteger sd = new AtomicInteger(0);
	/**神罚值*/
	private AtomicInteger godPenalty = new AtomicInteger(0);
	/**神罚值上限。固定600*/	
	private int godPenaltyMax = 600;
	/**神罚回复*/	
	private IntegerAttribute godPenaltyRec = new IntegerAttribute();
	/**血量上限**/
	private LongAttribute hpMax = new LongAttribute();
	/**攻击**/
	private IntegerAttribute atk = new IntegerAttribute();
	/**物理防御**/
	private IntegerAttribute pdef = new IntegerAttribute();
	/**魔法防御**/
	private IntegerAttribute mdef = new IntegerAttribute();
	/**物理穿透**/
	private IntegerAttribute ppen = new IntegerAttribute();
	/**魔法穿透**/
	private IntegerAttribute mpen = new IntegerAttribute();
	/**生命回复**/
	private IntegerAttribute hpRec = new IntegerAttribute();
	/**移动速度**/
	private IntegerAttribute speed = new IntegerAttribute();
	/**护盾上限**/
	private IntegerAttribute shield = new IntegerAttribute();
	/**护盾回复**/
	private IntegerAttribute shieldRec = new IntegerAttribute();
	/**护盾减伤(万分比)**/
	private IntegerAttribute shieldRed = new IntegerAttribute();
	/**暴击几率(万分比)**/
	private IntegerAttribute crit = new IntegerAttribute();
	/**暴击伤害(万分比)**/
	private IntegerAttribute critDmg = new IntegerAttribute();
	/**抗暴几率(万分比)**/
	private IntegerAttribute critRes = new IntegerAttribute();
	/**抗暴伤害(万分比)**/
	private IntegerAttribute critRed = new IntegerAttribute();
	/**命中率(万分比)**/
	private IntegerAttribute hit = new IntegerAttribute();
	/**闪避率(万分比)**/
	private IntegerAttribute dodge = new IntegerAttribute();
	/**伤害加成(万分比)**/
	private IntegerAttribute dmgAdd = new IntegerAttribute();
	/**伤害减免(万分比)**/
	private IntegerAttribute dmgRed = new IntegerAttribute();
	/**眩晕几率(万分比)**/
	private IntegerAttribute dizzy = new IntegerAttribute();
	/**眩晕抵抗(万分比)**/
	private IntegerAttribute dizzyRes = new IntegerAttribute();
	/**战士增伤(万分比)*/
	private IntegerAttribute zsAdd = new IntegerAttribute();
	/**战士减伤(万分比)*/
	private IntegerAttribute zsRed = new IntegerAttribute();
	/**法师增伤(万分比)*/
	private IntegerAttribute fsAdd = new IntegerAttribute();
	/**法师减伤(万分比)*/
	private IntegerAttribute fsRed = new IntegerAttribute();
	/**牧师增伤(万分比)*/
	private IntegerAttribute msAdd = new IntegerAttribute();
	/**牧师减伤(万分比)*/
	private IntegerAttribute msRed = new IntegerAttribute();
	/**玩家增伤(万分比)*/
	private IntegerAttribute playerAdd = new IntegerAttribute();
	/**玩家减伤(万分比)*/
	private IntegerAttribute playerRed = new IntegerAttribute();
	/**武器部位属性百分比提升(万分比)*/
	private IntegerAttribute weaponAdd = new IntegerAttribute();
	/**衣服部位属性百分比提升(万分比)*/
	private IntegerAttribute clothesAdd = new IntegerAttribute();
	/**护腕部位属性百分比提升(万分比)*/
	private IntegerAttribute bracersAdd = new IntegerAttribute();
	/**戒指部位属性百分比提升(万分比)*/
	private IntegerAttribute ringAdd = new IntegerAttribute();
	/**头盔部位属性百分比提升(万分比)*/
	private IntegerAttribute helmetAdd = new IntegerAttribute();
	/**项链部位属性百分比提升(万分比)*/
	private IntegerAttribute necklaceAdd = new IntegerAttribute();
	/**腰带部位属性百分比提升(万分比)*/
	private IntegerAttribute beltAdd = new IntegerAttribute();
	/**鞋子部位属性百分比提升(万分比)*/
	private IntegerAttribute shoesAdd = new IntegerAttribute();
	/**关卡收益经验加成(万分比)*/
	private IntegerAttribute expAdd = new IntegerAttribute();
	/**关卡收益金币加成(万分比)*/
	private IntegerAttribute goldAdd = new IntegerAttribute();
	/**固定增伤*/
	private IntegerAttribute fdamageAdd = new IntegerAttribute();
	/**固定减伤*/
	private IntegerAttribute fdamageReduce = new IntegerAttribute();
	/**固定暴伤*/
	private IntegerAttribute critAdd = new IntegerAttribute();
	/**固定抗暴*/
	private IntegerAttribute critReduce = new IntegerAttribute();
	/**虚拟战斗力*/
	private IntegerAttribute pow = new IntegerAttribute();
	/**血量万分比加成的分子部分*/
	private IntegerAttribute hpMaxPer = new IntegerAttribute();
	/**攻击万分比加成的分子部分*/
	private IntegerAttribute atkPer = new IntegerAttribute();
	/**物理防御万分比加成的分子部分*/
	private IntegerAttribute pdefPer = new IntegerAttribute();
	/**魔法防御万分比加成的分子部分*/
	private IntegerAttribute mdefPer = new IntegerAttribute();
	
	/**对怪物的魔焰伤害*/
	private IntegerAttribute godPenaltyAddBeast = new IntegerAttribute();
	
	/**对玩家的魔焰伤害*/
	private IntegerAttribute godPenaltyAddPlayer = new IntegerAttribute();
	
	/**护盾百分比*/	
	private IntegerAttribute shieldPer = new IntegerAttribute();
	/**暗影伤害*/	
	private IntegerAttribute shadowDmg = new IntegerAttribute();
	/**神圣伤害*/	
	private IntegerAttribute holyDmg = new IntegerAttribute();
	
	@Override
	public HashSet<String> getAttributeNames() {
		return BattleAttributes.attributeNames;
	}
	
	@Override
	public Method getGetter(String attributeName) {
		return BattleAttributes.nameToGetter.get(attributeName);
	}
	
	@Override
	public Method getSetter(String attributeName) {
		return BattleAttributes.nameToSetter.get(attributeName);
	}
	
	@Override
	public Number getAttribute(String attributeName) {
		Method getter = getGetter(attributeName);
		Number value = 0;
		try {
			value = (Number) getter.invoke(this);
		}
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	@Override
	public Number getAttribute(int attributeType) {
		return getAttribute(AttributeName.getAttributeName(attributeType));
	}
	
	/**
	 * 根据属性名获取属性值---不包含buff属性---用于计算战斗力
	 * 
	 * @param attributeName
	 * @return
	 */
	public Number getNoBuffAttribute(String attributeName) {
		try {
 			Object obj = this.getClass().getDeclaredField(attributeName).get(this);
			if (obj instanceof AbstractAttribute) {
				@SuppressWarnings("unchecked")
				AbstractAttribute<? extends Number> obj1 = (AbstractAttribute<? extends Number>)obj;
				return obj1.getNotBuffValue();
			} else if (obj instanceof AtomicInteger) {
				return ((AtomicInteger)obj).get();
			} else if (obj instanceof AtomicLong) {
					return ((AtomicLong)obj).get();
			} else if (obj instanceof Integer) {
				return Integer.parseInt(obj.toString());
			} else if (obj instanceof Long) {
				return Long.parseLong(obj.toString());
			}
		}  catch (Exception e1) {
			System.out.println("获取战斗力属性时错误："+attributeName);
			e1.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 根据属性名获取kind类型的属性值
	 * 
	 * @param attributeName
	 * @param kind
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Number getAttribute(String attributeName,int kind) {
		AbstractAttribute<? extends Number> v;
		try {
			v = (AbstractAttribute<? extends Number>) this.getClass().getDeclaredField(attributeName).get(this);
			return v.getValue(kind);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean hasAttribute(String attributeName) {
		return attributeNames.contains(attributeName);
	}
	
	@Override
	public boolean hasAttribute(int attributeType) {
		return attributeNames.contains(AttributeName.getAttributeName(attributeType));
	}
	
	@Override
	public long getHp() {
		if(hp.get() < getHpMax()) {
			return hp.get();
		}
		return getHpMax();
	}
	
	@Override
	public void setHp(long value) {
		this.hp.set(value);
	}
	
	@Override
	public void resetHp() {
		setHp(getHpMax());
	}
	
	@Override
	public int getSd() {
		if(sd.get() < getShield()) {
			return sd.get();
		}
		return getShield();
	}
	
	@Override
	public void setSd(int value) {
		this.sd.set(value);
	}
	
	@Override
	public void resetSd() {
		setSd(getShield());
	}

	@Override
	public long getHpMax() {
//		return this.hpMax.getValue();
		long rawValue = this.hpMax.getValue();
		int perValue = this.getHpMaxPer();
		if (perValue == 0) {
			return rawValue;
		}
		return (long) (rawValue*(1+perValue/10000f));
	}
	
	@Override
	public void setHpMax(int kind, long value) {
		this.hpMax.setValue(kind, value);
	}

	@Override
	public int getAtk() {
		int rawValue = this.atk.getValue();
		int perValue = this.getAtkPer();
		if (perValue == 0) {
			return rawValue;
		}
		return (int) (rawValue*(1+perValue/10000f));
	}
	
	@Override
	public void setAtk(int kind, int value) {
		this.atk.setValue(kind, value);
	}

	@Override
	public int getPdef() {
//		return this.pdef.getValue();
		int rawValue = this.pdef.getValue();
		int perValue = this.getPdefPer();
		if (perValue == 0) {
			return rawValue;
		}
		return (int) (rawValue*(1+perValue/10000f));
	}
	
	@Override
	public void setPdef(int kind, int value) {
		this.pdef.setValue(kind, value);
	}

	@Override
	public int getMdef() {
//		return this.mdef.getValue();
		int rawValue = this.mdef.getValue();
		int perValue = this.getMdefPer();
		if (perValue == 0) {
			return rawValue;
		}
		return (int) (rawValue*(1+perValue/10000f));
	}
	
	@Override
	public void setMdef(int kind, int value) {
		this.mdef.setValue(kind, value);
	}

	@Override
	public int getPpen() {
		return this.ppen.getValue();
	}
	
	@Override
	public void setPpen(int kind, int value) {
		this.ppen.setValue(kind, value);
	}

	@Override
	public int getMpen() {
		return this.mpen.getValue();
	}
	
	@Override
	public void setMpen(int kind, int value) {
		this.mpen.setValue(kind, value);
	}

	@Override
	public int getHpRec() {
		return this.hpRec.getValue();
	}
	
	@Override
	public void setHpRec(int kind, int value) {
		this.hpRec.setValue(kind, value);
	}

	@Override
	public int getSpeed() {
		return this.speed.getValue();
	}
	
	@Override
	public void setSpeed(int kind, int value) {
		this.speed.setValue(kind, value);
	}

	@Override
	public int getShield() {
		int rawValue = this.shield.getValue();
		int perValue = this.getShieldPer();
		if (perValue == 0) {
			return rawValue;
		}
		return (int) (rawValue*(1+perValue/10000f));
	}
	
	@Override
	public void setShield(int kind, int value) {
		this.shield.setValue(kind, value);
	}

	@Override
	public int getShieldRec() {
		return this.shieldRec.getValue();
	}
	
	@Override
	public void setShieldRec(int kind, int value) {
		this.shieldRec.setValue(kind, value);
	}

	@Override
	public int getShieldRed() {
		return this.shieldRed.getValue();
	}
	
	@Override
	public void setShieldRed(int kind, int value) {
		this.shieldRed.setValue(kind, value);
	}

	@Override
	public int getCrit() {
		return this.crit.getValue();
	}
	
	@Override
	public void setCrit(int kind, int value) {
		this.crit.setValue(kind, value);
	}

	@Override
	public int getCritDmg() {
		return this.critDmg.getValue();
	}
	
	@Override
	public void setCritDmg(int kind, int value) {
		this.critDmg.setValue(kind, value);
	}

	@Override
	public int getCritRes() {
		return this.critRes.getValue();
	}
	
	@Override
	public void setCritRes(int kind, int value) {
		this.critRes.setValue(kind, value);
	}

	@Override
	public int getCritRed() {
		return this.critRed.getValue();
	}
	
	@Override
	public void setCritRed(int kind, int value) {
		this.critRed.setValue(kind, value);
	}

	@Override
	public int getHit() {
		return this.hit.getValue();
	}
	
	@Override
	public void setHit(int kind, int value) {
		this.hit.setValue(kind, value);
	}

	@Override
	public int getDodge() {
		return this.dodge.getValue();
	}
	
	@Override
	public void setDodge(int kind, int value) {
		this.dodge.setValue(kind, value);
	}

	@Override
	public int getDmgAdd() {
		return this.dmgAdd.getValue();
	}
	
	@Override
	public void setDmgAdd(int kind, int value) {
		this.dmgAdd.setValue(kind, value);
	}

	@Override
	public int getDmgRed() {
		return this.dmgRed.getValue();
	}
	
	@Override
	public void setDmgRed(int kind, int value) {
		this.dmgRed.setValue(kind, value);
	}

	@Override
	public int getDizzy() {
		return this.dizzy.getValue();
	}
	
	@Override
	public void setDizzy(int kind, int value) {
		this.dizzy.setValue(kind, value);
	}

	@Override
	public int getDizzyRes() {
		return this.dizzyRes.getValue();
	}
	
	@Override
	public void setDizzyRes(int kind, int value) {
		this.dizzyRes.setValue(kind, value);
	}
	
	@Override
	public int getZsAdd() {
		return this.zsAdd.getValue();
	}
	
	@Override
	public void setZsAdd(int kind, int value) {
		this.zsAdd.setValue(kind, value);
	}
	
	@Override
	public int getZsRed() {
		return this.zsRed.getValue();
	}
	
	@Override
	public void setZsRed(int kind, int value) {
		this.zsRed.setValue(kind, value);
	}
	
	@Override
	public int getFsAdd() {
		return this.fsAdd.getValue();
	}
	
	@Override
	public void setFsAdd(int kind, int value) {
		this.fsAdd.setValue(kind, value);
	}
	
	@Override
	public int getFsRed() {
		return this.fsRed.getValue();
	}
	
	@Override
	public void setFsRed(int kind, int value) {
		this.fsRed.setValue(kind, value);
	}
	
	@Override
	public int getMsAdd() {
		return this.msAdd.getValue();
	}
	
	@Override
	public void setMsAdd(int kind, int value) {
		this.msAdd.setValue(kind, value);
	}
	
	@Override
	public int getMsRed() {
		return this.msRed.getValue();
	}
	
	@Override
	public void setMsRed(int kind, int value) {
		this.msRed.setValue(kind, value);
	}
	
	@Override
	public int getPlayerAdd() {
		return this.playerAdd.getValue();
	}
	
	@Override
	public void setPlayerAdd(int kind, int value) {
		this.playerAdd.setValue(kind, value);
	}
	
	@Override
	public int getPlayerRed() {
		return this.playerRed.getValue();
	}
	
	@Override
	public void setPlayerRed(int kind, int value) {
		this.playerRed.setValue(kind, value);
	}
	
	@Override
	public int getGodPenaltyMax() {
		return this.godPenaltyMax;
	}
	
	@Override
	public void setGodPenaltyMax(int value) {
		this.godPenaltyMax = value;
	}
	
	@Override
	public int getGodPenalty() {
		return this.godPenalty.get();
	}
	
	@Override
	public void setGodPenalty(int value) {
		this.godPenalty.set(value);
	}
	
	@Override
	public int getGodPenaltyRec() {
		return this.godPenaltyRec.getValue();
	}
	
	@Override
	public void setGodPenaltyRec(int kind, int value) {
		this.godPenaltyRec.setValue(kind, value);
	}
	
	@Override
	public int getWeaponAdd() {
		return weaponAdd.getValue();
	}
	
	@Override
	public void setWeaponAdd(int kind, int value) {
		this.weaponAdd.setValue(kind, value);
	}
	
	@Override
	public int getClothesAdd() {
		return clothesAdd.getValue();
	}
	
	@Override
	public void setClothesAdd(int kind, int value) {
		this.clothesAdd.setValue(kind, value);
	}
	
	@Override
	public int getBracersAdd() {
		return bracersAdd.getValue();
	}
	
	@Override
	public void setBracersAdd(int kind, int value) {
		this.bracersAdd.setValue(kind, value);
	}
	
	@Override
	public int getRingAdd() {
		return ringAdd.getValue();
	}
	
	@Override
	public void setRingAdd(int kind, int value) {
		this.ringAdd.setValue(kind, value);
	}
	
	@Override
	public int getHelmetAdd() {
		return helmetAdd.getValue();
	}
	
	@Override
	public void setHelmetAdd(int kind, int value) {
		this.helmetAdd.setValue(kind, value);
	}
	
	@Override
	public int getNecklaceAdd() {
		return necklaceAdd.getValue();
	}
	
	@Override
	public void setNecklaceAdd(int kind, int value) {
		this.necklaceAdd.setValue(kind, value);
	}
	
	@Override
	public int getBeltAdd() {
		return beltAdd.getValue();
	}
	
	@Override
	public void setBeltAdd(int kind, int value) {
		this.beltAdd.setValue(kind, value);
	}
	
	@Override
	public int getShoesAdd() {
		return shoesAdd.getValue();
	}
	
	@Override
	public void setShoesAdd(int kind, int value) {
		this.shoesAdd.setValue(kind, value);
	}
	
	@Override
	public int getExpAdd() {
		return expAdd.getValue();
	}
	
	@Override
	public void setExpAdd(int kind, int value) {
		this.expAdd.setValue(kind, value);
	}
	
	@Override
	public int getGoldAdd() {
		return goldAdd.getValue();
	}
	
	@Override
	public void setGoldAdd(int kind, int value) {
		this.goldAdd.setValue(kind, value);
	}
	
	@Override
	public int getFdamageAdd() {
		return fdamageAdd.getValue();
	}
	
	@Override
	public void setFdamageAdd(int kind, int value) {
		this.fdamageAdd.setValue(kind, value);
	}
	
	@Override
	public int getFdamageReduce() {
		return fdamageReduce.getValue();
	}
	
	@Override
	public void setFdamageReduce(int kind, int value) {
		this.fdamageReduce.setValue(kind, value);
	}
	
	@Override
	public int getCritAdd() {
		return this.critAdd.getValue();
	}
	
	@Override
	public void setCritAdd(int kind, int value) {
		this.critAdd.setValue(kind, value);
	}
	
	@Override
	public int getCritReduce() {
		return this.critReduce.getValue();
	}
	
	@Override
	public void setCritReduce(int kind, int value) {
		this.critReduce.setValue(kind, value);
	}
	
	@Override
	public int getPow() {
		return this.pow.getValue();
	}
	
	@Override
	public void setPow(int kind, int value) {
		this.pow.setValue(kind, value);
	}
	
	/**
	 * 获取血量万分比加成的分子部分
	 * @return
	 */
	public int getHpMaxPer() {
		return this.hpMaxPer.getValue();
	}
	
	/**
	 * 设置血量万分比加成的分子部分
	 * @param kind
	 * @param value
	 */
	public void setHpMaxPer(int kind, int value) {
		this.hpMaxPer.setValue(kind, value);
	}
	
	/**
	 * 获取攻击万分比加成的分子部分
	 * @return
	 */
	public int getAtkPer() {
		return this.atkPer.getValue();
	}
	
	/**
	 * 设置攻击万分比加成的分子部分
	 * @param kind
	 * @param value
	 */
	public void setAtkPer(int kind, int value) {
		this.atkPer.setValue(kind, value);
	}
	
	/**
	 * 获取物理防御万分比加成的分子部分
	 * @return
	 */
	public int getPdefPer() {
		return this.pdefPer.getValue();
	}
	
	/**
	 * 设置物理防御万分比加成的分子部分
	 * @param kind
	 * @param value
	 */
	public void setPdefPer(int kind, int value) {
		this.pdefPer.setValue(kind, value);
	}
	
	/**
	 * 获取魔法防御万分比加成的分子部分
	 * @return
	 */
	public int getMdefPer() {
		return this.mdefPer.getValue();
	}
	
	/**
	 * 设置魔法防御万分比加成的分子部分
	 * @param kind
	 * @param value
	 */
	public void setMdefPer(int kind, int value) {
		this.mdefPer.setValue(kind, value);
	}
	
	/**
	 * 获取对怪物魔焰伤害
	 * @return
	 */
	public int getGodPenaltyAddBeast() {
		return this.godPenaltyAddBeast.getValue();
	}
	
	/**
	 * 设置对怪物魔焰伤害
	 * @param kind
	 * @param value
	 */
	public void setGodPenaltyAddBeast(int kind, int value) {
		this.godPenaltyAddBeast.setValue(kind, value);
	}

	/**
	 * 获取对玩家魔焰伤害
	 * @return
	 */
	public int getGodPenaltyAddPlayer() {
		return this.godPenaltyAddPlayer.getValue();
	}
	
	/**
	 * 设置对玩家魔焰伤害
	 * @param kind
	 * @param value
	 */
	public void setGodPenaltyAddPlayer(int kind, int value) {
		this.godPenaltyAddPlayer.setValue(kind, value);
	}
	
	/**
	 * 获取护盾百分比
	 * @return
	 */
	public int getShieldPer() {
		return this.shieldPer.getValue();
	}
	
	/**
	 * 设置护盾百分比
	 * @param kind
	 * @param value
	 */
	public void setShieldPer(int kind, int value) {
		this.shieldPer.setValue(kind, value);
	}
	
	/**
	 * 获取暗影伤害
	 * @return
	 */
	public int getShadowDmg() {
		return this.shadowDmg.getValue();
	}
	
	/**
	 * 设置暗影伤害
	 * @param kind
	 * @param value
	 */
	public void setShadowDmg(int kind, int value) {
		this.shadowDmg.setValue(kind, value);
	}
	
	/**
	 * 获取神圣伤害
	 * @return
	 */
	public int getHolyDmg() {
		return this.holyDmg.getValue();
	}
	
	/**
	 * 设置神圣伤害
	 * @param kind
	 * @param value
	 */
	public void setHolyDmg(int kind, int value) {
		this.holyDmg.setValue(kind, value);
	}
}
