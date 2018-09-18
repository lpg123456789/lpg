package com.lpg.moudle.buff;

/**
 * 在技能表中配置的BUFF相关的参数信息
 * 
 */
public class BuffInfo {

	private int id;
	private int pro;
	private int target;
	private int time;
	
	public BuffInfo(int id, int pro, int target, int time) {
		this.id = id;
		this.pro = pro;
		this.target = target;
		this.time = time;
	}

	/**
	 * BUFF id
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * BUFF概率
	 * 
	 * @return
	 */
	public int getPro() {
		return pro;
	}

	/**
	 * BUFF目标。1：自己；2：攻击目标
	 * 
	 * @return
	 */
	public int getTarget() {
		return target;
	}

	/**
	 * BUFF持续时间
	 * 
	 * @return
	 */
	public int getTime() {
		return time;
	}

}
