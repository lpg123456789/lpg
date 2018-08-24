package com.lpg.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 字符串处理工具类
 * 
 * @author Brant
 * @mail brtcoder@163.com
 * @date 2017年11月16日 下午9:51:02
 */
/**
 * @author Brant
 * @mail   brtcoder@163.com
 * @date   2018年1月6日 下午2:22:35
 */
public class StringUtils {

	/**
	 * 将用固定分隔符分隔的数字字符串解析为整数数组。
	 * 
	 * @param str 带固定分隔符的整数字符串
	 * @param regex 分隔符正则表达式
	 * @return 一个正数数组
	 */
	public static int[] toIntArray(String str, String regex) {
		String[] strArr = str.split(regex);
		int[] intArr = new int[strArr.length];
		for (int i = 0; i < strArr.length; ++i) {
			intArr[i] = Integer.parseInt(strArr[i]);
		}
		return intArr;
	}

	/**
	 * 检测给定字符串是否是非空。null和空字符串都返回true，其它情况返回false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return null == str || str.isEmpty();
	}

	/**
	 * 删除文本两端的空白字符
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		return leftTrim(rightTrim(str));
	}

	/**
	 * 裁切掉指定字符串左侧的空白字符
	 * 
	 * @param str
	 * @return
	 */
	public static String leftTrim(String str) {
		if (isEmpty(str)) {
			return "";
		}

		int index = 0;
		int len = str.length();
		while (index < len) {
			if (' ' != str.charAt(index)) {
				break;
			}
			++index;
		}
		return str.substring(index);
	}

	/**
	 * 裁除字符串右侧空白
	 * 
	 * @param str
	 * @return
	 */
	public static String rightTrim(String str) {
		if (isEmpty(str)) {
			return "";
		}

		int index = str.length() - 1;
		while (index != -1) {
			if (' ' != str.charAt(index)) {
				break;
			}
			--index;
		}
		return str.substring(0, index + 1);
	}

	/**
	 * 连接单词为驼峰命名规则的词组。驼峰命名规则：除第一个字符之外的其它每个单词的首字母大写
	 * 
	 * @param words
	 * @return
	 */
	public static String linkHumpWord(String... words) {
		int len = words.length;
		if (0 == len) {
			return "";
		}

		String hump = trim(words[0]);
		for (int i = 1; i < len; ++i) {
			String word = trim(words[i]);
			hump += word.substring(0, 1).toUpperCase() + word.substring(1);
		}
		return hump;
	}
	
	/**
	 * 将getter或者setter函数的名字去掉get或set，并将剩余的部分首字母小写，得到属性字段名
	 * 
	 * @param getterOrSetterName
	 * @return
	 */
	public static String parseFieldName(String getterOrSetterName) {
		return getterOrSetterName.substring(3, 4).toLowerCase() + getterOrSetterName.substring(4);
	}
	
	/**
	 * 使用二分查询法查询指定的字符串中是否包含特定字符
	 * 
	 * @param str
	 * @param c
	 * @return
	 */
	public static int indexOf(String str, int c) {
		if(isEmpty(str)) {
			return -1;
		}
		
		int headIndex = 0;
		int tailIndex = str.length() - 1;
		while(headIndex <= tailIndex) {
			if(str.charAt(headIndex) == c) {
				return headIndex;
			}
			
			if(str.charAt(tailIndex) == c) {
				return tailIndex;
			}
			++headIndex;
			--tailIndex;
		}
		return -1;
	}

	/**
	 * 删除空白字符
	 * 
	 * @param str
	 * @return
	 */
	public static String omitWhitespace(String str) {
		return str.replaceAll("\\s+", "");
	}
	
	/**
	 * 在字符串左侧填充指定数量的字符，使之
	 * 
	 * @param str
	 * @param fillChar
	 * @param len
	 * @return
	 */
	public static String toFixedString(long n, int radix, int len) {
		String str = Long.toString(n, radix);
		int fillNum = len - str.length();
		if(fillNum > 0) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < fillNum; ++i) {
				sb.append('0');
			}
			sb.append(str);
			str = sb.toString();
		}
		return str;
	}
	
	/**
	 * 将 abc{0}def{1}ddc{2}……{N}xxx 格式的字符串中的{N}，依次用params替换
	 * 
	 * @param str
	 * @param params
	 * @return
	 */
	public static String format(String str, Object...params) {
		int len = params.length;
		if(len == 0) {
			return str;
		}
		
		for(int i = 0; i < len; ++i) {
			Object obj = params[i];
			String regex = "\\{\\s?"+ i + "\\s?\\}";
			str = str.replaceFirst(regex, obj.toString());
		}
		return str;
	}
	
	/**
	 * 将异常对象转换为字符串 
	 * 
	 * @param ex
	 * @return
	 */
	public static String exceptionToString(Throwable ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(sw, true);
			ex.printStackTrace(pw);
		}
		finally {
			try {
				sw.close();
			}
			catch (Throwable ex2) {
			}
			
			if(pw != null) {
				pw.close();
			}
		}
		return sw.toString();
	} 
	
	/**
	 * 为字符串增加双引号
	 * 
	 * @param str
	 * @return
	 */
	public static String quote(String str) {
		if(null == str) {
			return null;
		}
		return "\"" + str + "\"";
	}
	
	/**
	 * 转义反斜线
	 * 
	 * @param str
	 * @return
	 */
	public static String escapeBackslash(String str) {
		if(null == str) {
			return str;
		}
		return str.replaceAll("\\\\", "\\\\\\\\");
	}
}
