package com.lpg.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

//优先级1：本槽位上阵武将 > 未上阵武将 > 其他槽位上阵武将 > 超过上阵数量限制的类型武将（辅助，或者远程）

//1  2  3  4

//优先级2：武将自身战力高 > 武将自身战力低

public class HeroSort {

	private int id;
	private int status;
	private int power;
	
	public HeroSort(int id,int status,int power) {
		this.id=id;
		this.status=status;
		this.power=power;
	}

	public int getId() {
		return id;
	}

	public int getStatus() {
		return status;
	}

	public int getPower() {
		return power;
	}

	@Override
	public String toString() {
		return "HeroSort [id=" + id + ", status=" + status + ", power=" + power + "]";
	}
	
	public static void main(String[] args) {
		
		List<HeroSort> list=new ArrayList<>();
		Random random = new Random();
		
//		for (int i = 0; i < 300; i++) {
//			int status=random.nextInt(4)+1;
//			int power=random.nextInt(300);
//			HeroSort sort=new HeroSort(i, status, power);
//			list.add(sort);
//		}
	
		HeroSort sort1=new HeroSort(1, 1, 0);
		HeroSort sort2=new HeroSort(2, 2, 0);
		HeroSort sort3=new HeroSort(3, 1, 0);
		HeroSort sort4=new HeroSort(4, 1, 0);
		HeroSort sort5=new HeroSort(5, 1, 0);
		list.add(sort1);
		list.add(sort2);
		list.add(sort3);
		list.add(sort4);
		list.add(sort5);
		
		
		Collections.sort(list, new Comparator<HeroSort>() {
			@Override
			public int compare(HeroSort o1, HeroSort o2) {
				// 如果number不等按number排序（增序
				if(o1.getStatus()> o2.getStatus()) {
					return 1;
				}else if(o1.getStatus() == o2.getStatus()) {
					if(o1.getPower() > o2.getPower()) {
						return 1;
					}else {
						return -1;
					}
				}else {
					return -1;
				}
			}
		});
		

		for (HeroSort task : list) {
			System.out.println(task.toString());
		}
		
		
	}
	
}
