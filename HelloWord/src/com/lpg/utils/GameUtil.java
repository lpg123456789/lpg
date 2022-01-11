package com.lpg.utils;
/**
 * 游戏常用工具(待补充)
 * 
 * @author zhangzhen
 * 
 */
public class GameUtil {
	public static int[] get2ByteInShort(int value) {
		short l = (short) value;
		short r = (short) value;
		l >>= 8;
		r = (short) (r << 8);
		r >>= 8;
		return new int[] { l, r };
	}

	public static int put2ByteInShort(int left, int right) {
		int v = left;
		v <<= 8;
		right = right & 0xff;
		return (int) (v | right);
	}

	public static int[] get2ShortInInt(int value) {
		int l = value;
		int r = value;
		l >>= 16;
		r = r << 16;
		r >>= 16;
		return new int[] { l, r };
	}

	public static int put2ShortInInt(int left, int right) {
		int v = left;
		v <<= 16;
		right = right & 0xffff;
		return v | right;
	}

	public static long[] get2IntInLong(long value) {
		long l = value;
		long r = value;
		l >>= 32;
		r <<= 32;
		r >>= 32;
		return new long[] { l, r };
	}

	public static long put2IntInLong(long left, long right) {
		long v = left;
		v <<= 32;
		right = right & 0xffffffff;
		return v | right;
	}

	/**
	 * 将emoji表情替换成空（替换emoji表情）
	 * 
	 * @param source
	 * @return 过滤后的字符串
	 */
	public static String emoji(String source) {
		return source.replaceAll("[^\\u0000-\\uFFFF]", "");
	}

	/**
	 * 验证名字替换名字中的部分符号
	 * 
	 * @param name
	 * @return
	 */
	public static String checkName(String name) {
	    name = name.trim();
		return name.replaceAll("[\\[\\]{}<>]", "");
	}
	
}
