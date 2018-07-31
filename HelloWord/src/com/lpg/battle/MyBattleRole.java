package com.lpg.battle;


public class MyBattleRole {

	private String name;
	
	private int life=0;
	
	private int attack=0;
	
	private boolean isDead=false;

	public MyBattleRole(String name,int life,int attack) {
		this.name=name;
		this.life=life;
		this.attack=attack;
	}
	
	public void dealLife(int lifeInfo){
		life-=lifeInfo;
		System.out.println(name+" 受到攻击 "+",损失血量"+lifeInfo+" 剩余血量"+life);
		if(life<=0) {
			isDead=true;
			System.out.println(name+" 角色死亡,战斗结束");
		}
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
}
