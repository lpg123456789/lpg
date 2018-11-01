package com.lpg.moudle.arena.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存中的数据，不入数据库
 * @author lpg
 * 2018年11月1日
 */
public class ArenaRobot extends  ArenaRoleInfo{
	
	private long userId; 

	private String name;
	
	private int id;
	
	private String resourceId;
	
	private String skillId;
	
	private int power; 
	
	private int rankPos;
	
	/**
	 * 根据怪物模版id初始化相关数据
	 */
	public ArenaRobot(int rankPos,long userId) {
		this.userId=userId;
		this.rankPos=rankPos;
		this.initArenaRobot();
	}
	
	/**
	 * 根据配置初始相关数据
	 */
	private void initArenaRobot() {
		
	}
	
	/**
	 * 根据下标获取模版id
	 * @param index
	 */
	public int getTemplateId(int index) {
		String[] str=resourceId.split("\\|")[index].split(",");
		return Integer.parseInt(str[1]);
	}
	
	/**
	 * 根据下标获取模版技能列表
	 * @return
	 */
	public List<Integer> getSkillList(int index) {
		String str=skillId.split("\\|")[index];
		String[] strArr=str.split(",");
		List<Integer> resultList = new ArrayList<>(strArr.length);
		for (String s : strArr) {
		    resultList.add(Integer.parseInt(s));
		}
		return resultList;
	}

	@Override
	public int getRankPos() {
		return rankPos;
	}

	@Override
	public int isUser() {
		return 0;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getPower() {
		return power;
	}

	@Override
	public int getClothId(int index) {
		return Integer.parseInt(resourceId.split("\\|")[index].split(",")[1]);
	}

	@Override
	public int getWeaponId(int index) {
		return Integer.parseInt(resourceId.split("\\|")[index].split(",")[0]);
	}

	@Override
	public int getJob() {
		return 0;
	}
	
	@Override
	public int updatePos(int rankPos) {
		return this.rankPos=rankPos;
	}
	
	public int getSize() {
		return resourceId.split("\\|").length;
	}

	@Override
	public int getVip() {
		return 0;
	}

	@Override
	public int getServerId() {
		return 1;
	}

	@Override
	public long getUserId() {
		return userId;
	}
	
}
