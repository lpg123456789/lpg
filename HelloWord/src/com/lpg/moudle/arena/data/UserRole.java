package com.lpg.moudle.arena.data;

/**
 * 数据库里面的数据
 * @author lpg
 * 2018年11月1日
 */
public class UserRole {

	private long userId;
	
	private String name;
	
	private int power;
	
	private int job;
	
	private int vip;
	
	private int sid;

	public String getName() {
		return name;
	}

	public int getPower() {
		return power;
	}

	public int getJob() {
		return job;
	}

	public int getVip() {
		return vip;
	}

	public int getSid() {
		return sid;
	}

	public long getUserId() {
		return userId;
	}
	
	
	
}
