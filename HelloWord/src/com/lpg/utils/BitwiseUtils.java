package com.lpg.utils;

/**
 * 位操作工具类
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年1月6日 下午4:24:39
 */
public class BitwiseUtils {
	
	/**
	 * 指示一个二进制位串标记中是否包含指定的标记
	 * 
	 * @param flags
	 * @param flag
	 * @return
	 */
	public static boolean hasFlag(int flags, int flag) {
		return (flags & flag) != 0;
	}
}
