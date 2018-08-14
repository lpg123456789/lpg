package com.lpg.moudle.redPocket;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.lpg.utils.DateUtils;

public class RedPocket {

	private int id;

	private long sendId;

	private Long corpsId = 0L;

	/**
	 *key：用户id  value：钻石 
	 */
	private Map<Long,Integer> userIdMap = new HashMap<Long,Integer>();

	private int num;

	private long luckUserId;

	private int endTime;
	
	private int totalDiamond;
	
	private int templateId;
	
	/**
	 * 内存测试
	 * @param num
	 * @param totalDiamond
	 */
	public RedPocket(int num,int totalDiamond) {
		this.num=num;
		this.totalDiamond=totalDiamond;
	}

	public RedPocket(int id, long sendId, Long corpsId,int endTime,String[] params,int templateId) {
		this.id = id;
		this.sendId = sendId;
		this.corpsId = corpsId;
		this.endTime = endTime;
		this.num = Integer.parseInt(params[1]);
		this.totalDiamond=Integer.parseInt(params[0]);
		this.templateId=templateId;
	}

	public RedPocket(int id, long sendId, Long corpsId, long luckUserId, int endTime,String[] params,int templateId) {
		this(id, sendId, corpsId, endTime,params,templateId);
		this.luckUserId = luckUserId;
	}

	public void addUser(Long userId,int diamond) {
		userIdMap.put(userId, diamond);
	}
	
	public void calc() {
		long index=0;
		int value=0;
		for(Long key:userIdMap.keySet()) {
			int diamond=userIdMap.get(key);
			if(diamond>value){
				index=key;
				value=diamond;
			}
		}
		if(index!=0){
			this.luckUserId = index;
		}
	}
	
	public int getMyRedPocket(long userId){
		Integer diamond=userIdMap.get(userId);
		return diamond==null?0:diamond;
	}
	
	public int getMapNum(){
		return userIdMap.size();
	}
	
	public boolean canGetLuckMoney(long userId) {
		if (userIdMap.containsKey(userId)) {
			return false;
		}
		if (userIdMap.size() >= num) {
			return false;
		}
		if (isOutTime()) {
			return false;
		}
		return true;
	}
	
	public boolean isContainsUserId(long userId) {
		if (userIdMap.containsKey(userId)) {
			return true;
		}
		return false;
	}
	
	public int isContainsUser(long userId) {
		if (userIdMap.containsKey(userId)) {
			return 1;
		}
		if (userIdMap.size() >= num) {
			return 1;
		}
		if (isOutTime()) {
			return 1;
		}
		return -1;
	}

	public boolean isFull() {
		return userIdMap.size() >= num;
	}

	public Long getCorpsId() {
		return corpsId;
	}

	public boolean isOutTime() {
		return DateUtils.getCurrentSecond()-endTime>0;
	}

	public long getLuckUserId() {
		return luckUserId;
	}

	public long getEndTime() {
		return endTime;
	}

	public int getId() {
		return id;
	}

	public long getSendId() {
		return sendId;
	}

	public int getNum() {
		return num;
	}
	
	public int getType() {
		return corpsId==0?0:1;
	}

	public Map<Long, Integer> getUserIdMap() {
		return userIdMap;
	}
	
	public int getRemainSize() {
		return num-userIdMap.size();
	} 
	
	public int getRecivedMoney() {
		int money=0;
		for(Long key:userIdMap.keySet()) {
			int diamond=userIdMap.get(key);
			money+=diamond;
		}
		return money;
	}
	
	public int getRemainMoney() {
		int money=0;
		for(Long key:userIdMap.keySet()) {
			int diamond=userIdMap.get(key);
			money+=diamond;
		}
		return totalDiamond-money;
	}
	
	public Set<Long> getUserKey(){
		return userIdMap.keySet();
	}
	
	public int getTemplateId() {
		return templateId;
	}

	public int getTotalDiamond() {
		return totalDiamond;
	}
	
	public boolean isShow() {
		return endTime + RedPocketMoudle.SevenDay > DateUtils.getCurrentSecond();
	}

	public void printInfo() {
		System.out.println("红包数量为 "+num+" 金额为 "+totalDiamond);
		for (Long key : userIdMap.keySet()) {
			int value=userIdMap.get(key);
			System.out.print("用户"+key+"领取了红包"+value+"  ");
		}
		System.out.println();
		System.out.println("获得的总值 "+getRecivedMoney());
	}
	
	public void printInfo2() {
		System.out.println("id "+id+" 过期时间 "+endTime+" 是否满了"+isFull());
	}
	
}
