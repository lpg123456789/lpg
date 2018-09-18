package com.lpg.utils;

/**
 * 数组工具类
 * 
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2017年12月27日 下午4:28:26
 */
public class ArrayUtils {
	/**
	 * 使用二分查询法查询对象索引
	 * 
	 * @param arr
	 * @param t
	 * @return
	 */
	public static <T> int indexOf(T[] arr, T t) {
		int len = arr.length;
		if(0 == len) {
			return -1;
		}
		
		int headIndex = 0;
		int tailIndex = len - 1;
		
		while(headIndex <= tailIndex) {
			if(arr[headIndex] == t) {
				return headIndex;
			}
			
			if(arr[tailIndex] == t) {
				return tailIndex;
			}
			
			headIndex++;
			tailIndex--;
		}
		return -1;
	}
	
	/**
	 * 使用二分查询法查询对象索引
	 * 
	 * @param arr
	 * @param t
	 * @return
	 */
	public static int indexOf(int[] arr, int t) {
		int len = arr.length;
		if(0 == len) {
			return -1;
		}
		
		int headIndex = 0;
		int tailIndex = len - 1;
		
		while(headIndex <= tailIndex) {
			if(arr[headIndex] == t) {
				return headIndex;
			}
			
			if(arr[tailIndex] == t) {
				return tailIndex;
			}
			
			headIndex++;
			tailIndex--;
		}
		return -1;
	}
	
	/**
	 * 使用二分查询法查询对象索引
	 * 
	 * @param arr
	 * @param t
	 * @return
	 */
	public static int indexOf(long[] arr, long t) {
		int len = arr.length;
		if(0 == len) {
			return -1;
		}
		
		int headIndex = 0;
		int tailIndex = len - 1;
		
		while(headIndex <= tailIndex) {
			if(arr[headIndex] == t) {
				return headIndex;
			}
			
			if(arr[tailIndex] == t) {
				return tailIndex;
			}
			
			headIndex++;
			tailIndex--;
		}
		return -1;
	}
	
	/**
	 * 将特定分隔符分隔的数字字符串转换为整形数组
	 * 
	 * @param str
	 * @param separator
	 */
	public static int[] strToInts(String str, String separator) {
		String[] sArr = str.split(separator);
		int len = sArr.length;
		int[] nArr = new int[len];
		for(int i = 0; i < len; ++i) {
			nArr[i] = Integer.parseInt(sArr[i]);
		}
		return nArr;
	}
	
	/**
	 * 将特定分隔符分隔的数字字符串转换为long数组
	 * 
	 * @param str
	 * @param separator
	 * @return
	 */
	public static long[] strToLongs(String str, String separator) {
		String[] sArr = str.split(separator);
		int len = sArr.length;
		long[] nArr = new long[len];
		for(int i = 0; i < len; ++i) {
			nArr[i] = Long.parseLong(sArr[i]);
		}
		return nArr;
	}
	
	/**
	 * 将特定分隔符分隔的数字字符串转换为float数组
	 * 
	 * @param str
	 * @param separator
	 * @return
	 */
	public static float[] strToFloats(String str, String separator) {
		String[] sArr = str.split(separator);
		int len = sArr.length;
		float[] nArr = new float[len];
		for(int i = 0; i < len; ++i) {
			nArr[i] = Float.parseFloat(sArr[i]);
		}
		return nArr;
	}
	
	/**
	 * 将特定分隔符分隔的数字字符串转换为double数组
	 * 
	 * @param str
	 * @param separator
	 * @return
	 */
	public static double[] strToDoubles(String str, String separator) {
		String[] sArr = str.split(separator);
		int len = sArr.length;
		double[] nArr = new double[len];
		for(int i = 0; i < len; ++i) {
			nArr[i] = Double.parseDouble(sArr[i]);
		}
		return nArr;
	}
}
