package com.lpg.moudle.arena.data;

/**
 * 数据库里面的数据
 * @author lpg
 * 2018年11月1日
 */
public class ArenaRankDS {
	/**
	 * 自增长id
	 */
	public int id;
	
	/**
	 * 角色id
	 */
	public long userId;
	
	/**
	 * 已经挑战的次数
	 */
	public int arenaNum;
	
	/**
	 * 排名位置
	 */
	public int rankPos;
	
	/**
	 * 当前已购买的次数
	 */
	public int costNum;
	
	/**
	 * 历史最高排名
	 */
	public int topRankPos;
	
	/**
	 * 竞技积分
	 */
	public int arenaScore;
	
	/**
	 * winNum
	 */
	public int winNum;
	/**
	 * 当日0点时间
	 */
	public int zeroTime;
	
	/**
	 * 插入数据库
	 */
	public void insert() {
		
	}
	
}
