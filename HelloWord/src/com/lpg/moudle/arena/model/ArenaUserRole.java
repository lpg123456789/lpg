package com.lpg.moudle.arena.model;

import com.lpg.moudle.arena.data.ArenaRankDS;
import com.lpg.moudle.arena.data.UserRole;

/**
 * 玩家数据的封装
 * @author lpg
 * 2018年11月1日
 */
public class ArenaUserRole extends ArenaRoleInfo {
	
	/**
	 * 玩家数据库对象
	 */
	public ArenaRankDS arenaRankingDS;
	
	/**
	 * 玩家信息
	 */
	UserRole userRole;
	
	public ArenaUserRole(ArenaRankDS arenaRankingDS) {
		this.arenaRankingDS=arenaRankingDS;
		//userRole = UserManager.getInstance().getUserRole(arenaRankingDS.userId);
	}

	@Override
	public int getRankPos() {
		return arenaRankingDS.rankPos;
	}

	@Override
	public int isUser() {
		return 1;
	}

	@Override
	public long getUserId() {
		return arenaRankingDS.userId;
	}

	@Override
	public int getArenaNum() {
		return arenaRankingDS.arenaNum;
	}

	@Override
	public int getCostNum() {
		return arenaRankingDS.costNum;
	}

	@Override
	public String getName() {
		return userRole.getName();
	}

	@Override
	public int getPower() {
		return userRole.getPower();
	}

	@Override
	public int getClothId(int index) {
		return 1;
	}

	@Override
	public int getWeaponId(int index) {
		return 1;
	}

	@Override
	public int getJob() {
		return userRole.getJob();
	}

	@Override
	public int updatePos(int rankPos) {
		arenaRankingDS.rankPos=rankPos;
		if(rankPos<arenaRankingDS.topRankPos) {
			arenaRankingDS.topRankPos=rankPos;
		}
		return arenaRankingDS.rankPos;
	}

	@Override
	public void update() {
		//arenaRankingDS.update();
	}

	@Override
	public void zeroUpdate() {
		arenaRankingDS.arenaNum=0;
		arenaRankingDS.costNum=0;
		arenaRankingDS.winNum=0;
		//arenaRankingDS.update();
	}

	@Override
	public int getVip() {
		return userRole.getVip();
	}

	@Override
	public int getServerId() {
		return userRole.getSid();
	}

	
	
	
	
	
	
}
