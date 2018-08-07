package com.lpg.moudle.luckMoney;

import java.util.ArrayList;
import java.util.List;

public class LuckMoney {

	private long ownerId;
	
	private int type=0;
	
	private List<Integer> userIdList=new ArrayList<>();
	
	/**
	 * 全服为0,不为0则为军团id
	 */
	private int groupId;
	
	private int num;
	
	private long luckUserId;
	
	private long endTime;
	
	public boolean canGetLuckMoney(long userId){
		if(userIdList.contains(userId)){
			return false;
		}
		if(userIdList.size()==num){
			return false;
		}
		if(isOutTime()){
			return false;
		}
		return true;
	}
	
	public boolean isFull(){
		return userIdList.size()==num;
	}
	
	public int getType() {
		return type;
	}

	public boolean isOutTime(){
		return true;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public List<Integer> getUserIdList() {
		return userIdList;
	}

	public int getGroupId() {
		return groupId;
	}

	public long getLuckUserId() {
		return luckUserId;
	}

	public long getEndTime() {
		return endTime;
	}
	
}
