package com.lpg.moudle.arena.model;

/**
 * @author lpg
 * 2018年10月19日
 * 竞技场角色对象信息，包括真实玩家和竞技场
 * 数据库对象封装类
 * 有机器人有玩家的排名
 */
public abstract class ArenaRoleInfo {
	
	
	public abstract long getUserId();
	/**
	 * 排位顺序
	 * @return
	 */
	public abstract int getRankPos();

	/**
	 * 是否是玩家
	 * @return
	 */
	public abstract int isUser();
	
	/**
	 * 获取名字
	 * @return
	 */
	public abstract String getName();
	
	/**
	 * 获取战斗力
	 * @return
	 */
	public abstract int getPower();
	
	/**
	 * 已经挑战的次数
	 * @return
	 */
	public int getArenaNum(){
		return 0;
	}
	
	/**
	 * 当前已购买的次数
	 * @return
	 */
	public int getCostNum() {
		return 0;
	}
	
	/**
	 * 衣服id
	 * @return
	 */
	public abstract int getClothId(int index);
	
	/**
	 * 武器id
	 * @return
	 */
	public abstract int getWeaponId(int index);
	
	/**
	 * 职业id
	 * @return
	 */
	public abstract int getJob();
	
	/**
	 * 修改位置
	 * @return
	 */
	public abstract int updatePos(int rankPos);
	
	/**
	 * 更新
	 */
	public void update() {
		
	}
	
	/**
	 * 0点更新
	 */
	public void zeroUpdate() {
		
	}
	
	/**
	 * @return
	 */
	public abstract int getVip();
	
	/**
	 * @return
	 */
	public abstract int getServerId();
	
}
