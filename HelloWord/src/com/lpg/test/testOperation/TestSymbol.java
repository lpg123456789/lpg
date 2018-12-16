package com.lpg.test.testOperation;

/**
 * 处理计算机符号
 * @author lpg
 * 2018年12月7日
 */
public class TestSymbol {
	
	public static void main(String[] args) {
		//toDB();
		
		//judge();
		
		print();
		System.out.println();
		print2();
	}
	
	public static void test1() {
		//同1取1
		 System.out.println(2 & 2);
	}
	
	public static void test2() {
		//有1取1
		 System.out.println(128 | 129);
	}
	
	
	/**
	 * 判断
	 */
	public static void judge() {
		int record=1;
		int getVipReward=1;
		int flag=getRewardMark(getVipReward);
		boolean result=((record & flag) != 0);
		System.out.println(result);
	}
	
	/**
	 * 入库
	 */
	public static void toDB() {
		int record=0;
		int getVipReward=1;
		int flag=getRewardMark(getVipReward);
		int result=record|flag;
		System.out.println("0 级如果领了 vip"+getVipReward+" 得到的是标志是 "+flag+" 记录值是 "+result);
	}
	
	/**
	 * 获取奖励
	 * @param vip
	 * @return
	 */
	public static int getRewardMark(int vip) {
		return (int) Math.pow(2, vip - 1);
	}
	
	/**
	 * 按照顺序打印
	 */
	public static void print() {
		int record=0;
		int value=0;
		for (int i = 1; i <=12 ; i++) {
			int getVipReward=i;
			int flag=getRewardMark(getVipReward);
			value=record;
			record|=flag;
			System.out.println("如果领了 vip"+getVipReward+" 上一轮得到的值 "+value+" 得到的是标志是 "+flag+" 记录值是 "+record);	
			
		}
	}
	
	public static void print2() {
		int record=0;
		int value=0;
		for (int i = 12; i >0 ; i--) {
			int getVipReward=i;
			int flag=getRewardMark(getVipReward);
			value=record;
			record|=flag;
			System.out.println("如果领了 vip"+getVipReward+" 上一轮得到的值 "+value+" 得到的是标志是 "+flag+" 记录值是 "+record);	
			
		}
	}
	
	public static void print3() {
		int record=0;
		int value=0;
		for (int i = 12; i >0 ; i--) {
			int getVipReward=i;
			int flag=getRewardMark(getVipReward);
			value=record;
			record|=flag;
			System.out.println("如果领了 vip"+getVipReward+" 上一轮得到的值 "+value+" 得到的是标志是 "+flag+" 记录值是 "+record);	
		}
	}
}
