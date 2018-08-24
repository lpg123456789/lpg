package com.lpg.moudle.attributeMoule.attribute.role;

/**
 * 伙伴角色接口
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年3月23日 下午5:14:20
 */
public interface IMateRole extends IBattleRole {
	
	/**
	 * 获取角色的平台id
	 * 
	 * @return
	 */
	public int getPid();
	
	/**
	 * 获取角色的服务器id
	 * 
	 * @return
	 */
	public int getSid();
	
	/**
	 * 获取伙伴先前所在地图id。跟玩家共享
	 * 
	 * @return
	 */
	public int getPreMapId();
	
	/**
	 * 获取职业类型
	 * 
	 * @return
	 */
	public int getJob();
	
	/**
	 * 获取角色性别
	 * 
	 * @return
	 */
	public int getSex();
	
	/**
	 * 获取威望值
	 * 
	 * @return
	 */
	public int getPrestige();
	
	/**
	 * 获取关卡id
	 * 
	 * @return
	 */
	public int getGkId();
	
	/**
	 * 获取伙伴VIP等级。伙伴跟玩家主角共享VIP等级
	 * 
	 * @return
	 */
	public int getVip();
	
	/**
	 * VIP经验
	 * 
	 * @return
	 */
	public int getVipExp();
	
	/**
	 * VIP奖励领取记录
	 * 
	 * @return
	 */
	public int getVipRecords();
	
	/**
	 * 获取伙伴转生等级。伙伴跟玩家主角共享转生等级
	 * 
	 * @return
	 */
	public int getRein();
	
	/**
	 * 获取伙伴化神阶级。伙伴跟玩家主角共享化神阶级
	 * 
	 * @return
	 */
	public int getBecomeGodLevel();
	
	/**
	 * 获取伙伴化神等级。伙伴跟玩家主角共享化神等级
	 * 
	 * @return
	 */
	public int getBecomeGodStar();
	
	/**
	 * 获取伙伴经验。伙伴跟主角是共享经验的
	 * 
	 * @return
	 */
	public int getExp();

	/**
	 * 获取伙伴经验。伙伴跟主角是共享经验的
	 * 
	 * @return
	 */
	public int getExpMax();
	/**
	 * 伙伴创建时间（解锁时间）
	 * 
	 * @return
	 */
	public int getCreateTime();
	
	/**
	 * 获取伙伴战斗力
	 * 
	 * @return
	 */
	public int getPower();
	
	/**
	 * 指示玩家是否在线
	 * 
	 * @return
	 */
	public boolean isOnline();
}
