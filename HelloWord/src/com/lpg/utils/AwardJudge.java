package com.lpg.utils;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 奖励判决者（抽奖工具类）
 * 
 * @author Brant
 * @mail brtcoder@163.com
 * @date 2017年11月9日 上午10:10:12
 */
public class AwardJudge {

	/**
	 * 常用必出概率值 10000
	 */
	public static final int USUAL_CERTAINLY = 10000;

	/**
	 * 概率奖品抽奖方法的简化版本。必出概率值默认为10000
	 * @param list 参与抽奖的奖品列表
	 * @param probFieldName 奖品中的概率字段的名字
	 * @param repeatNum 重复抽取次数
	 * @return
	 */
	public static <T extends AbstractList<E>, E> ArrayList<E> lottery(T list, String probFieldName, int repeatNum) {
		return lottery(list, probFieldName, repeatNum, AwardJudge.USUAL_CERTAINLY);
	}

	/**
	 * 概率奖品抽奖方法
	 * 
	 * @param list 奖品列表
	 * @param probFieldName 奖品对象中用于标识概率的字段名。可以是无参数的方法。例如：getProbability()。 如果是方法，要写上括号
	 * @param repeatNum
	 *            重复次数。此参数主要是方便多次抽奖。比如N连抽
	 * @param certainly
	 *            必出概率。如果奖品列表中某物品的概率为必出概率，则100%产出
	 * @return
	 */
	public static <T extends AbstractList<E>, E> ArrayList<E> lottery(T list, String probFieldName, int repeatNum, int certainly) {
		// 区间集合
		HashSet<ProbabilitySection<E>> sections = new HashSet<>();
		// 必出列表。这里保存它主要是为了方便多次抽奖时进行复制
		ArrayList<E> certainlyList = new ArrayList<>();

		// 创建区间时的末尾位置
		int probAmount = 0;
		int probability = 0;

		for (E e : list) {
			probability = (int) ReflectUtils.getProperty(e, probFieldName);
			// 产出概率为0表示不产出，跳过
			if (probability == 0) {
				continue;
			}

			// 处理必出奖品
			if (probability == certainly) {
				certainlyList.add(e);
				continue;
			}

			// 创建区间
			sections.add(new ProbabilitySection<E>(e, probability, probAmount, probAmount += probability));
			// 概率总和加1，避免区间端点重叠
			++probAmount;
		}
		return lottery(sections, certainlyList, probAmount, repeatNum);
	}
	
	
	/**
	 * 概率奖品抽奖方法
	 * 
	 * @param rateMap 奖品到概率的映射表
	 * @param repeatNum 重复次数。此参数主要是方便多次抽奖。比如N连抽
	 * @param certainly 必出概率。如果奖品列表中某物品的概率为必出概率，则100%产出
	 * @return
	 */
	public static <T> ArrayList<T> lottery(Map<T, Integer> rateMap, int repeatNum, int certainly) {
		// 区间集合
		HashSet<ProbabilitySection<T>> sections = new HashSet<>();

		// 必出列表。这里保存它主要是为了方便多次抽奖时进行复制
		ArrayList<T> certainlyList = new ArrayList<>();

		// 创建区间时的末尾位置
		int probAmount = 0;
		int probability = 0;
		
		Iterator<Entry<T, Integer>> iterator = rateMap.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<T, Integer> next = iterator.next();
			T t = next.getKey();
			probability = next.getValue();
			// 产出概率为0表示不产出，跳过
			if (probability == 0) {
				continue;
			}

			// 处理必出奖品
			if (probability == certainly) {
				certainlyList.add(t);
				continue;
			}
			
			// 创建区间
			sections.add(new ProbabilitySection<T>(t, probability, probAmount, probAmount += probability));
			// 概率总和加1，避免区间端点重叠
			++probAmount;
		}
		return lottery(sections, certainlyList, probAmount, repeatNum);
	}
	
	
	/**
	 * 概率奖品抽奖方法
	 * 
	 * @param rateMap 奖品到概率的映射表
	 * @param repeatNum 重复次数。此参数主要是方便多次抽奖。比如N连抽
	 * @param certainly 必出概率。如果奖品列表中某物品的概率为必出概率，则100%产出
	 * @return
	 */
	public static <T> ArrayList<T> lotteryForDrop(Map<T, Integer> rateMap,int certainly) {
		//随机值
		int rv = RateUtils.rangeRandom(1, certainly);
		ArrayList<T> resultList = new ArrayList<>();
		Iterator<Entry<T, Integer>> iterator = rateMap.entrySet().iterator();
		int tv = 0;
		while(iterator.hasNext()) {
			Entry<T, Integer> next = iterator.next();
			T t = next.getKey();
			//总概率
			tv += next.getValue();
			// 产出概率为0表示不产出，跳过
			if (rv <= tv) {
				resultList.add(t);
				break;
			}
		}
		return resultList;
	}

	/**
	 * 随机物品
	 * 
	 * @param probToAwards 概率到奖励列表的映射表
	 * @param probToSection 概率到区间的映射表
	 * @param certainlyList 必出物品列表
	 * @param probAmount 概率总和
	 * @param repeatNum 随机次数
	 * @return
	 */
	public static <T> ArrayList<T> lottery(HashSet<ProbabilitySection<T>> sections, ArrayList<T> certainlyList, int probAmount, int repeatNum) {
		ArrayList<T> resultList = new ArrayList<>();
		for (int i = 0; i < repeatNum; ++i) {
			// 复制必出奖品到结果集中
			resultList.addAll(certainlyList);

			// 产生随机数
			int r = (int) Math.floor(Math.random() * probAmount);

			for(ProbabilitySection<T> s : sections) {
				if (s.contains(r)) {
					resultList.add(s.t);
					break;
				}
			}
		}
		return resultList;
	}
	
	/**
	 * 概率区间
	 * 
	 * @author Brant
	 * @mail   brtcoder@163.com
	 * @date   2018年5月5日 下午4:46:09
	 */
	public static class ProbabilitySection<T> {
		/**
		 * 道具
		 */
		public T t;
		
		/**
		 * 概率
		 */
		public int probability;
		
		/**
		 * 区间左端点
		 */
		public int left;
		
		/**
		 * 区间右端点
		 */
		public int right;
		
		public ProbabilitySection(T t, int probability, int left, int right) {
			this.t = t;
			this.left = left;
			this.right = right;
		}
		
		/**
		 * 检测指定值是否落在区间中
		 * 
		 * @param value
		 * @return
		 */
		public boolean contains(int value) {
			return value >= left && value <= right;
		}
	}
}