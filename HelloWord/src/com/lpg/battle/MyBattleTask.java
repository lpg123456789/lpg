package com.lpg.battle;

/**
 * @author Administrator
 *
 */
public class MyBattleTask implements Runnable {

	/**
	 * 目标
	 */
	MyBattleRole target;

	/**
	 * 攻击者
	 */
	MyBattleRole attacker;

	public MyBattleTask(MyBattleRole target, MyBattleRole attacker) {
		this.target = target;
		this.attacker = attacker;
	}

	public void battle() {
		boolean flag = true;
		while (flag) {
			attackerToTarget();
			TargetToattacker();
			if (isStopBattle()) {
				flag = false;
				System.out.println("有一方生命值为0，战斗结束");
				return;
			}
		}
	}

	public boolean isStopBattle() {
		return target.isDead() || attacker.isDead();
	}

	private void attackerToTarget() {
		if (isStopBattle()) {
			return;
		}
		int life = 2;
		target.dealLife(life);
	}

	private void TargetToattacker() {
		if (isStopBattle()) {
			return;
		}
		int life = 1;
		attacker.dealLife(life);
	}

	@Override
	public void run() {
		battle();
	}

}
