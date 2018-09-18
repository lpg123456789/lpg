package com.lpg.utils;

/**
 * 逻辑工具类
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年3月15日 下午5:17:58
 */
public class LogicUtils {
	
	/**
	 * 检测指定的数字中是否有非零数
	 * 
	 * @param nums
	 */
	public static boolean hasNonzero(int...nums) {
		for(int n : nums) {
			if(n != 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 检测指定的数字中是否有非零数
	 * 
	 * @param nums
	 * @return
	 */
	public static boolean hasNonzero(long...nums) {
		for(long n : nums) {
			if(n != 0) {
				return true;
			}
		}
		return false;
	}
}
