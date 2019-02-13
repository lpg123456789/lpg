package com.lpg.battle.battleOne;

public class BattleMain {

	public static void main(String[] args) {
			
		battle();
	}
	
	/**
	 * 单线程弄死彼此的，用线程池
	 */
	private static void battle() {
		
		MyThreadPool.execute(getBattle1());
		MyThreadPool.execute(getBattle2());
		
	}
	
	private static MyBattleTask getBattle1() {
		MyBattleRole attacker=new MyBattleRole("战士212321",10,1);
		MyBattleRole target=new MyBattleRole("怪物2",5,1);	
		MyBattleTask battleTask=new MyBattleTask(target, attacker);
		return battleTask;
	}
	
	private static MyBattleTask getBattle2() {
		MyBattleRole attacker=new MyBattleRole("战士1",10,1);
		MyBattleRole target=new MyBattleRole("怪物1",5,1);	
		MyBattleTask battleTask=new MyBattleTask(target, attacker);
		return battleTask;
	}
	
}
