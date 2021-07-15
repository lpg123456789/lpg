package com.lpg.testone;

import java.util.ArrayList;
import java.util.List;

public class Player {

	public String name="1";
	
	public int age=1;
	
	public Player(String name,int age) {
		this.name=name;
		this.age=age;
	}
	
	
	public static void main(String[] args) {
		
		List<Player> playList=new ArrayList<Player>();
		long being=System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			Player p=new Player("p"+i, i);
			playList.add(p);
		}
		long end=System.currentTimeMillis();
		System.out.println("时间差"+(end-being));
		
		long being1=System.currentTimeMillis();
		int age=0;
		List<Player> tempList=new ArrayList<Player>();
		tempList.addAll(playList);
		for (Player player : playList) {
			age+=player.age;
		}
		long end1=System.currentTimeMillis();
		System.out.println(end1-being+" age "+age);
		
	}
	
}
