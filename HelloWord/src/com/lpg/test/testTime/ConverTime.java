package com.lpg.test.testTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 根据档位换算时间
 * 
 * @author lpg 2018年12月26日
 */
public class ConverTime {

	/**
	 * 4档是上档
	 */
	public static int maxTaskNum=4;
	
	// 需求 一档的时间到了切换二档，二档的时间到了切换三档，以此类推
	// 不能做成服务器的在线更新，因为一占用资源，玩家可能不玩了，或者很久不登陆，所以做成转换实现
	// 调用地方 1 打开界面调用 2 登陆的时候调用
	public static void main(String[] args) {
		//converTime();
		converTime2();
	}

	/**
	 * 4档结束后，数据库记录会上升到第5档
	 * 换算档位
	 */
	private static void converTime() {
		// 设置时间，各档需要时间
		Map<Integer, Integer> timeMap = new HashMap<>();
		timeMap.put(1, 1000);
		timeMap.put(2, 1400);
		timeMap.put(3, 2500);
		timeMap.put(4, 4000);

		int initTime = 23478;// 初始时间
		int initNum = 1;// 初始档位
		int passTime = 1000000;// 已过时间
		
		int newNum = initNum;// 新档位
		int newTime = initTime;// 新的初始化时间
		
		if(initNum>=4) {
			System.out.println("该档位是最高档，不需要转换");
		}else {
			for (Integer key : timeMap.keySet()) {
				if (key < initNum) {// 如果配置的档位很低，则不需要转换
					continue;
				}
				Integer timeValue = timeMap.get(key);
				if (passTime >= timeValue) {// 说明经过了该档
					passTime -= timeValue;// 减去该档的时间
					newNum += 1;// 档数+1
					newTime += timeValue;
					System.out.println(timeValue);
				}
			}
			System.out.println("新的档位 " + newNum + " 新的时间" + newTime);
		}
	}
	
	/**
	 * 登陆的换算
	 * 换算档位的同时结算奖励，奖励结算规则如下：
	 * 每个档位8个任务全部完成的，可以结算8个档位奖励以及大奖；如果没有全部完成，只需要结算各个档位小奖
	 * 真实只有4档，过了第四档后数据库会转换为第5档，第5档则不需要计算
	 * 大奖的isGet字段如果有档数变化是需要重置的
	 */
	private static void converTime2() {
		// 设置时间，各档需要时间
		Map<Integer, Integer> timeMap = new HashMap<>();
		timeMap.put(1, 1000);
		timeMap.put(2, 1400);
		timeMap.put(3, 2500);
		timeMap.put(4, 4000);

		int initTime = 23478;// 初始时间
		int initNum = 2;// 初始档位
		int passTime = 46100;// 已过时间

		int newNum = initNum;// 新档位
		int newTime = initTime;// 新的初始化时间
		//获得需要
		Set<Integer> result=new HashSet<>();
		if(initNum>maxTaskNum) {
			return;
		}else{
			for (Integer key : timeMap.keySet()) {
				if (key < initNum) {// 如果配置的档位很低，则不需要转换
					continue;
				}
				Integer timeValue = timeMap.get(key);
				if (passTime >= timeValue) {// 说明经过了该档
					passTime -= timeValue;// 减去该档的时间
					newNum += 1;// 档数+1
					newTime += timeValue;
					result.add(key);
				}
			}
		}
		//需要转换的档数有，计算小奖奖励，计算大奖奖励
		for (Integer key : result) {
			System.out.print("需要换算的档位有 "+key+"     ");
		}
		System.out.println();
		System.out.println("新的档位 " + newNum + " 新的时间" + newTime);
		
	}

}
