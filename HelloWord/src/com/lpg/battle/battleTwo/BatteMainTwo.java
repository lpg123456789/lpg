package com.lpg.battle.battleTwo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatteMainTwo {

	/**
	 * key：userId value：battleId
	 */
	public Map<Long,Long> userToBattle=new HashMap<Long, Long>();
	
	/**
	 * key:battleId value:users
	 */
	public Map<Long,List<Long>> battleMap=new HashMap();
	
	/**
	 * @param role1
	 * @param role2
	 */
	public void attack(MyBattleRoleTwo role1,MyBattleRoleTwo role2){
		
		//需不需要获取role1的伙伴
		
		//需不需要获取role2的伙伴
		
	}
	
}
