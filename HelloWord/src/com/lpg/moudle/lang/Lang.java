package com.lpg.moudle.lang;
import java.util.HashMap;

public class Lang {

	private static Lang instance;
	public static Lang getInstance() {
		if (instance == null) {
			instance = new Lang();
		}
		return instance;
	}
	
	/**
	 * key:语言包key
	 * value:语言包中的字符串
	 */
	private HashMap<Long, String> idToLang = new HashMap<Long, String>();
	
	/**
	 * 获取语言包
	 * @param key
	 * @return
	 */
	public String get(long key) {
		String str = this.idToLang.get(key);
		if (str == null) {
			throw new RuntimeException("不存在的语言包key:"+key);
		}
		return str;
	}
	
	/**
	 * 获取语言包
	 * @param key
	 * @return
	 */
	public String get(long key, Object... args) {
		String msg = this.get(key);
		return format(msg, args);
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
	
}
