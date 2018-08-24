package com.lpg.moudle.attributeMoule.attribute.role;

import java.util.ArrayList;
import java.util.Collection;

import com.lpg.moudle.attributeMoule.IBattleAttributes;

import io.netty.util.internal.ConcurrentSet;

/**
 * 玩家角色
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年1月4日 下午4:55:21
 */
public interface IBattleRole{
	
	/**
	 * 实时统计战斗力
	 * 
	 * @return
	 */
	public int statPower();
	
	/**
	 * 获取战斗角色的血量
	 * @return
	 */
	public long getHp();
	
	/**
	 * 设置战斗角色的血量
	 * @param hp
	 */
	public void setHp(long hp);
	
	/**
	 * 增加血量。在现有血量上增加参数指定的血量。传入负数可以减少血量
	 * 
	 * @param addNum
	 */
	public void addHp(long addNum);
	
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
	 * 
	 * @param shield
	 */
	public void setSd(int sd);
	
	/**
	 * 重置护盾值
	 */
	public void resetSd();
	
	/**
	 * 获取神罚值
	 * 
	 * @return
	 */
	public int getGodPenalty();
	
	/**
	 * 设置神罚值
	 * 
	 * @return
	 */
	public void setGodPenalty(int value);
	
	/**
	 * 重置神罚值
	 */
	public void resetGodPenalty();
	
	/**
	 * 获取战斗角色的血量上限
	 * @return
	 */
	public long getHpMax();
	
	/**
	 * 获取最大护盾值
	 * @return
	 */
	public int getSdMax(); 
	
	/**
	 * 获取神罚值上限
	 * 
	 * @return
	 */
	public int getGodPenaltyMax();
	
	/**
	 * 按生命恢复速度回复生命
	 */
	public void recoverHp();
	
	/**
	 * 按护盾恢复速度回复护盾
	 */
	public void recoverSd();
	
	/**
	 * 按神罚恢复速度恢复神罚值
	 */
	public void recoverGodPenalty();
	
	/**
	 * 角色所属玩家的userId或者镜像的伙伴所属主角镜像的id
	 * 
	 * @return
	 */
	public long getOwnerId();
	
	/**
	 * 设置角色所属玩家的userId
	 * 
	 * @param ownerId
	 */
	public void setOwnerId(long ownerId);
	
	/**
	 * 获取队伍id
	 * 
	 * @return
	 */
	public int getTeamId();
	
	/**
	 * 设置队伍id
	 * 
	 * @param teamId
	 */
	public void setTeamId(int teamId);
	
	/**
	 * 获取军团名称
	 * 
	 * @return
	 */
	public String getCorpsName();
	
	/**
	 * 设置军团名称
	 * 
	 * @param teamId
	 */
	public void setCorpsName(String corpsName);
	
	/**
	 * 获取军团id
	 * 
	 * @return
	 */
	public long getCorpsId();
	
	/**
	 * 设置军团id
	 * 
	 * @param teamId
	 */
	public void setCorpsId(long corpsId);
	
	/**
	 * 获取阵营id
	 * 
	 * @return
	 */
	public int getCamp();
	
	/**
	 * 设置阵营id
	 * 
	 * @return
	 */
	public void setCamp(int camp);
	
	/**
	 * 获取角色等级
	 * 
	 * @return
	 */
	public int getLevel();
	
	/**
	 * 获取攻击CD
	 * 
	 * @return
	 */
	public int getAtkCd();
	
	/**
	 * 获取最后一次攻击的时间
	 * 
	 * @return
	 */
	public long getLastAtkTime();
	
	/**
	 * 结束战斗，清除战斗相关标记
	 */
	public void finishBattle();
	
	/**
	 * 指示角色是否在攻击CD中。在CD中返回true，不在则返回false。攻击CD由技能的公共CD决定
	 * 
	 * @return
	 */
	public boolean inAtkCd();
	
	/**
	 * 获取仇恨目标。如果仇恨目标为null说明不在战斗中
	 * 
	 * @return
	 */
	public IBattleRole getHatredTarget();
	
	/**
	 * 设置仇恨目标
	 * 
	 * @param role
	 */
	public void setHatredTarget(IBattleRole role);
	
	/**
	 * 移除所有战斗观察者
	 */
	public void removeAllObserver();

	
	/**
	 * 角色战斗属性
	 * 
	 * @return
	 */
	public IBattleAttributes getAttributes();
	
	/**
	 * 指示角色是否满血
	 * 
	 * @return
	 */
	public boolean isFullBlood();
	
	/**
	 * 指示角色是否满护盾
	 * 
	 * @return
	 */
	public boolean isFullShield();
	
	/**
	 * 指示角色是否死亡
	 * 
	 * @return
	 */
	public boolean isDead();
	
	/**
	 * 检测指定的角色是否是当前角色的友军
	 * 
	 * @return
	 */
	public boolean isFriend(IBattleRole battleRole);
	
	
	/**
	 * 战斗时的移动步数。为了避免战斗打怪时双方无限环绕，如果怪物移动100步还找不到目标则不再移动
	 * @return
	 */
	public int getMoveCount();
	
	/**
	 * 增加战斗时的移动步数。为了避免战斗打怪时双方无限环绕，如果怪物移动100步还找不到目标则不再移动
	 * @return
	 */
	public int increaseMoveCount();
	
	/**
	 * 清除移动步数
	 * @return
	 */
	public int clearMoveCount();
	
	/**
	 * 指示角色是否自动反击
	 * 
	 * @return
	 */
	public boolean getAutoBeatBack();
	
	/**
	 * 设置角色是否自动反击
	 * 
	 * @param value
	 */
	public void setAutoBeatBack(boolean value);
	
	/**
	 * 获取最后杀死本角色的对象的id
	 * 
	 * @return
	 */
	public long getLastKiller();
	
	/**
	 * 设置最后杀死本角色的对象的id
	 * 
	 * @param lastKiller
	 */
	public void setLastKiller(long lastKiller);
}
