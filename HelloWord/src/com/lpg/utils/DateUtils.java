package com.lpg.utils;

import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期格式工具类
 * 
 * @author Brant
 * @mail brtcoder@163.com
 * @date 2017年11月4日 下午2:49:06
 */
public class DateUtils {

	/*--------------------------------网络时间服务器地址------------------------------------*/
	/**
	 * 国家授时中心
	 */
	public static final String TIME_HOST_NTSC = "http://www.ntsc.ac.cn";

	/**
	 * 百度
	 */
	public static final String TIME_HOST_BAIDU = "http://www.baidu.com";

	/**
	 * 淘宝
	 */
	public static final String TIME_HOST_TAOBAO = "http://www.taobao.com";
	
	
	/*--------------------------------日期格式化模板------------------------------------*/
	/**
	 * 精确到秒的24小时日期格式化模版
	 */
	public static final String FORMAT_PATTERN_SE = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 精确到毫秒的24小时日期格式化模版
	 */
	public static final String FORMAT_PATTERN_MS = "yyyy-MM-dd HH:mm:ss.S";
	
	/*--------------------------------时间单位------------------------------------*/
	/**
	 * 每天秒数
	 */
	public static final int PER_DAY_SECOND = 86400;
	
	/**
	 * 每小时秒数
	 */
	public static final int PER_HOUR_SECOND = 3600;
	
	/**
	 * 每分钟秒数
	 */
	public static final int PER_MINUTE_SECOND = 60;
	
	/**
	 * 每周毫秒数
	 */
	public static final int PER_WEEK_MILLIS = 604800000;
	
	/**
	 * 每天毫秒数
	 */
	public static final int PER_DAY_MILLIS = 86400000;
	
	/**
	 * 每小时毫秒数
	 */
	public static final int PER_HOUR_MILLIS = 3600000;
	
	/**
	 * 每分钟毫秒数 
	 */
	public static final int PER_MINUTE_MILLIS = 60000;
	
	
	/**
	 * 网络时间服务器主机地址
	 */
	public static String networkTimeHost = TIME_HOST_NTSC;
	
	/**
	 * 指示在获取当前系统时间时是否使用网络时间。默认值为false
	 */
	public static boolean useNextworkTime = false;
	
	public static void main(String[] args) {
		
		System.out.println(getCurrentMillis());
		
	}
	
	/**
	 * 获取当前毫秒数
	 * 
	 * @return
	 */
	public static long getCurrentMillis() {
		if(!useNextworkTime) {
			return System.currentTimeMillis();
		}
		
		try {
			URLConnection con = new URL(networkTimeHost).openConnection();
			con.connect();

			return con.getDate();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return System.currentTimeMillis();
	}
	
	/**
	 * 获取当前的秒单位时间戳
	 * 
	 * @return
	 */
	public static int getCurrentSecond() {
		return millisToSecond(getCurrentMillis());
	}
	
	

	/**
	 * 毫秒转换为秒
	 * 
	 * @param millisecond
	 * @return
	 */
	public static int millisToSecond(long millis) {
		return (int) (millis / 1000);
	}
	
	/**
	 * 秒转换为毫秒
	 * 
	 * @param second
	 * @return
	 */
	public static long secondToMillis(int second) {
		return second * 1000L;
	}
	
	/**
	 * 根据指定的格式模版格式化当前日期
	 * 
	 * @param pattern
	 * @return
	 */
	public static String formatCurrentTime(String pattern) {
		return formatDate(new Date(getCurrentMillis()), pattern);
	}

	/**
	 * 使用格式化模版FORMAT_PATTERN_SE格式化当前时间
	 * 
	 * @return
	 */
	public static String formatCurrentTime() {
		return formatDate(new Date(getCurrentMillis()), FORMAT_PATTERN_SE);
	}

	/**
	 * 根据指定的格式模版格式化传入的日期对象
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	/**
	 * 根据指定的格式模版格式化秒为单位的日期时间戳
	 * 
	 * @param second
	 * @param pattern
	 * @return
	 */
	public static String formatDate(int second, String pattern) {
		return formatDate(secondToMillis(second), pattern);
	}
	
	/**
	 * 根据指定的格式模版格式化毫秒为单位的日期时间戳
	 * 
	 * @param second
	 * @param pattern
	 * @return
	 */
	public static String formatDate(long millis, String pattern) {
		Date date = new Date(millis);
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}
	
	/**
	 * 使用格式化模版FORMAT_PATTERN_SE格式化指定的毫秒时间
	 * 
	 * @param millis 毫秒单位时间戳
	 * @return
	 */
	public static String formatDate(long millis) {
		Date date = new Date(millis);
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_PATTERN_SE);
		return dateFormat.format(date);
	}

	/**
	 * 使用格式化模版 FORMAT_PATTERN_SE 格式化日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date) {
		return formatDate(date, FORMAT_PATTERN_SE);
	}

	/**
	 * 获取24小时制今日特定小时、分、秒、毫秒的日期
	 * 
	 * @param hour
	 * @param minute
	 * @param second
	 * @param millisecond
	 * @return 一个Date对象
	 */
	public static Date getTodayTime(int hour, int minute, int second, int millisecond) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getCurrentMillis());
		c.set(Calendar.AM_PM, Calendar.AM);
		c.set(Calendar.HOUR, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		c.set(Calendar.MILLISECOND, millisecond);
		return c.getTime();
	}
	
	/**
	 * 获取昨天00:00:00.00的时间戳。（单位：秒）
	 * 
	 * @return
	 */
	public static int getYestodayZeroTime() {
		// 获得昨天零点的时间
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getCurrentMillis());
		c.set(Calendar.AM_PM, Calendar.AM);
		c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) - 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return millisToSecond(c.getTimeInMillis());
	}
	
	/**
	 * 获得当天00:00:00.00的时间戳。（单位：秒）
	 * 
	 * @return
	 */
	public static int getTodayZeroTime() {
		// 获得当天零点的时间
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getCurrentMillis());
		c.set(Calendar.AM_PM, Calendar.AM);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return millisToSecond(c.getTimeInMillis());
	}

	/**
	 * 获取指定时间戳当天 00:00:00.00的时间戳。（单位：秒）
	 * 
	 * @param theDayTime
	 * @return
	 */
	public static int getTheDayZeroTime(long theDayTime) {
		// 获得当天零点的时间
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(theDayTime);
		c.set(Calendar.AM_PM, Calendar.AM);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return millisToSecond(c.getTimeInMillis());
	}

	/**
	 * 获取本周起始时间。即周一的00:00:00
	 * 
	 * @return
	 */
	public static int getWeekBeginTime() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.AM_PM, Calendar.AM);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return millisToSecond(c.getTimeInMillis());
	}

	/**
	 * 用常用FORMAT_PATTERN_SE模版解析字符串形式的配置日期
	 * 
	 * @param strTime
	 * @return 解析出来的日期时间戳。单位：秒
	 * @throws ParseException
	 */
	public static int parseTime(String strTime) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(FORMAT_PATTERN_SE);
		Date date = format.parse(strTime);
		return millisToSecond(date.getTime());
	}
	
	/**
	 * 用常用FORMAT_PATTERN_SE模版解析字符串形式的配置日期
	 * 
	 * @param strTime
	 * @return 解析出来的日期时间戳。单位：秒
	 * @throws ParseException
	 */
	public static int parseTime(String strTime, String pattern) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = format.parse(strTime);
		return millisToSecond(date.getTime());
	}

	/**
	 * 计算两个时间戳相差的天数。
	 * 
	 * @param newTime
	 * @param oldTime
	 * @return
	 */
	public static int getDayDiff(long newMillisTime, long oldMillisTime) {
		long millis = Math.abs(newMillisTime - oldMillisTime);
		return (int)(millis / PER_DAY_MILLIS);
	}

}
