package com.lpg.moudle.arena;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lpg.moudle.arena.model.ArenaRobot;
import com.lpg.moudle.arena.model.ArenaRoleInfo;


/**
 * @author lpg
 * 2018年10月19日
 */
public class ArenaManager {
	
	private static ArenaManager instance;
	public static ArenaManager getInstance() {
		if(instance == null) {
			instance = new ArenaManager();
		}
		return instance;
	}

	/**
	 * key:userId
	 * 正在挑战的玩家
	 */
	private Map<Long,Integer> challengeUser = new HashMap<Long,Integer>();
	
	/**
	 * key:rank
	 * 正在被挑战的级
	 */
	private Map<Integer,Integer> challengeRank = new HashMap<Integer,Integer>();
	
	/**
	 * key：玩家id  
	 */
	private Map<Long,ArenaRoleInfo> arenaRoleMap=new ConcurrentHashMap<>();
	
	/**
	 * key:排名  value 
	 */
	private Map<Integer,ArenaRoleInfo>  arenaRankingMap=new ConcurrentHashMap<>();
	
	/**
	 * 获取排名
	 * @return
	 */
	public Map<Integer,ArenaRoleInfo> getArenaRankingMap(){
		return arenaRankingMap;
	}
	
	public Map<Long,ArenaRoleInfo> getArenaRoleMap() {
		return arenaRoleMap;
	}
	
	/**
	 * 排行榜添加信息
	 * @param arenaRoleInfo
	 */
	public void addArenaRole(ArenaRoleInfo arenaRoleInfo) {
		arenaRoleMap.put(arenaRoleInfo.getUserId(), arenaRoleInfo);
		arenaRankingMap.put(arenaRoleInfo.getRankPos(), arenaRoleInfo);
	}
	
	/**
	 * 判断是否为机器人
	 * @param userId
	 * @return
	 */
	public boolean isRobot(long userId) {
		if(arenaRoleMap.get(userId)==null) {
			return false;
		}
		return arenaRoleMap.get(userId).isUser()==0;
	}
	
	/**
	 * 用怪物id创建机器人
	 * @param rankPos
	 * @return
	 */
	public ArenaRoleInfo initArenaRole(int rankPos){
		ArenaRoleInfo arenaRoleInfo=new ArenaRobot(rankPos,1);
		arenaRankingMap.put(rankPos, arenaRoleInfo);
		arenaRoleMap.put(arenaRoleInfo.getUserId(), arenaRoleInfo);
		return arenaRoleInfo;
	}
	
	/**
	 * 通过排名位置获取相关信息，直接返回
	 * @param rankPos
	 * @return
	 */
	public ArenaRoleInfo getArenaRole(int rankPos) {
		return arenaRankingMap.get(rankPos);
	}
	
	/**
	 * 通过userId来获取相关的对象
	 * @param userId
	 */
	public ArenaRoleInfo getArenaRoleByUserId(long userId) {
		return arenaRoleMap.get(userId);
	}
	
	/**
	 * 添加玩家挑战竞技场的人状态
	 *  机器人和镜像走不了同一个逻辑
	 * @param userId
	 */
	public void addChallengeStatus(long userId,int rank) {
		challengeUser.put(userId, rank);
		challengeRank.put(rank, 1);
	}
	
	/**
	 * 移除玩家挑战竞技场的人状态
	 * @param userId
	 */
	public void removeChallengeStatus(long userId) {
		Integer rank=challengeUser.remove(userId);
		challengeRank.remove(rank);
	}
	
	/**
	 * 获取挑战竞技场的人的状态
	 * @param userId
	 * @return
	 */
	public Integer getChallengeStatus(long userId) {
		return challengeUser.get(userId);
	}
	
	/**
	 * 是否在挑战竞技场的人
	 * @param userId
	 */
	public boolean isChallengeUser(long userId) {
		if(challengeUser.containsKey(userId)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 是否正在被挑战
	 * @param rank
	 * @return
	 */
	public boolean isChallengeRank(int rank) {
		if(challengeRank.containsKey(rank)) {
			return true;
		}
		return false;
	}
		
	/**
	 * 互换位置
	 * @param myRankPos
	 * @param challengRank
	 * @param map
	 */
	public void arenaSort(long userId) {
		int challengPos = challengeUser.get(userId);
		int myRankPos = arenaRoleMap.get(userId).getRankPos();
		ArenaRoleInfo myArenaRoleInfo = arenaRankingMap.get(myRankPos);
		ArenaRoleInfo enemyArenaRoleInfo = arenaRankingMap.get(challengPos);
		//我的设成其它人的
		myArenaRoleInfo.updatePos(challengPos);
		myArenaRoleInfo.update();
		arenaRankingMap.put(challengPos, myArenaRoleInfo);
		//其它人的设成我的
		enemyArenaRoleInfo.updatePos(myRankPos);
		enemyArenaRoleInfo.update();
		arenaRankingMap.put(myRankPos, enemyArenaRoleInfo);
	}
	
}
