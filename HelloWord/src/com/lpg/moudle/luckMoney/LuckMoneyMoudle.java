package com.lpg.moudle.luckMoney;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LuckMoneyMoudle {

	/**
	 * 时间换空间
	 * key:id
	 */
	private Map<Integer,LuckMoney> luckMoneyMap=new HashMap<>();
	
	/**
	 * 时间换空间
	 * key:组id
	 * 0为世界服  不为0则为组
	 */
	private Map<Integer,List<LuckMoney>> gropLuckMoneyMap=new HashMap<>();
	
	/**
	 * 用户的map
	 */
	private Map<Integer,UserLuckMoney> userLuckMap=new HashMap<>();
	
	/**
	 * 0点清除过期信息
	 */
	public void zeroTime(){
		Set<Integer> userIdSet=new HashSet<>();
		for (Integer key : luckMoneyMap.keySet()) {
			LuckMoney luckMoney=luckMoneyMap.get(key);
			if(!luckMoney.isOutTime()){
				continue;
			}
			userIdSet.addAll(luckMoney.getUserIdList());
			for (Integer userId : luckMoney.getUserIdList()) {
				userLuckMap.get(userId).map.remove(key);
			}
			luckMoneyMap.remove(luckMoney);
			gropLuckMoneyMap.get(luckMoney.getGroupId()).remove(luckMoney);
		}
		for (Integer integer : userIdSet) {
			userLuckMap.get(integer).calDiamond();
		}
	}
	
	public void sendLuckMoney(Integer userId){
		
	}
	
	public void getLuckMoney(Integer userId,int id){
		LuckMoney luckMoney=luckMoneyMap.get(id);
		synchronized (luckMoney) {
			boolean flag=luckMoney.canGetLuckMoney(userId);
			if(!flag){
				return;
			}		
		}
	}
	
	public static void main(String[] args) {
		
	}
	
}
