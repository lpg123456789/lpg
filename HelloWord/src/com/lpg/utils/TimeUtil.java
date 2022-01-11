package com.lpg.utils;

import org.quartz.CronExpression;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * 时间处理工具类
 * 
 * @author zhangzhen
 * @date 2017年8月15日
 * @version 1.0
 */
public class TimeUtil {
    /** 1秒的毫秒数 */
    public static final long MILLIS = 1000L;
    /** 5秒的毫秒数 */
    public static final long FIVE_MILLIS = 5 * 1000L;
    /** 10秒的毫秒数 */
    public static final long TEN_MILLIS = 10 * 1000L;
    /** 三十秒的毫秒数 */
    public static final long THIRTY_MILLIS = 30 * 1000L;
    /** 一分钟的毫秒数 */
    public static final long MIN_MILLIS = 60 * 1000L;
    /** 两分钟的毫秒数 */
    public static final long TWO_MIN_MILLIS = 2 * 60 * 1000L;
    /** 五分钟的毫秒数 */
    public static final long FIVE_MIN_MILLIS = 5 * 60 * 1000L;
    /** 十分钟的毫秒数 */
    public static final long TEN_MIN_MILLIS = 10 * 60 * 1000L;
    /** 30分钟的毫秒数 */
    public static final long THIRTY_MIN_MILLIS = 30 * 60 * 1000L;
    /** 一小时的毫秒数 */
    public static final long HOUR_MILLIS = 60 * 60 * 1000L;
    /** 一天的毫秒数 */
    public static final long DAY_MILLIS = 60 * 60 * 24 * 1000L;
    /** 三天的毫秒数 */
    public static final long THREE_DAY_MILLIS = 3 * 60 * 60 * 24 * 1000L;
    /** 七天的毫秒数 */
    public static final long SEVEN_DAY_MILLIS = 7 * 60 * 60 * 24 * 1000L;
    /** 一周的毫秒数 */
    public static final long WEEK_MILLIS = 3600L * 24L * 7L * 1000L;
    /** 两周的毫秒数 */
    public static final long DOUBLE_WEEK_MILLIS = 3600L * 24L * 14L * 1000L;
    /** 十五天的毫秒数 */
    public static final long FIFTEEN_DAY_MILLIS = 15 * 60 * 60 * 24 * 1000L;
    /** 1个月的毫秒数 */
    public static final long MONTH_MILLIS = 3600L * 24L * 30L * 1000L;
    /** 1年的毫秒数 */
    public static final long YEAR_MILLIS = 3600L * 24L * 365L * 1000L;

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYYMMDD = "yyyyMMdd";

    public static final String YYYY_MM_DD__HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy,MM,dd,HH,mm,ss";

    public static final ZoneId ZONE_ID = ZoneId.systemDefault();

    /**
     * 获取当前服务器时间
     * 
     * @return
     */
    public static long getServerTime() {
        return System.currentTimeMillis();
    }

    public static int getServerSecond() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 格式化时间
     * 
     * @param time
     * @param pattern
     * @return
     */
    public static String formateDate(long time, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date(time);
        return simpleDateFormat.format(date);
    }

    /**
     * 格式化时间
     * 
     * @param timeStr
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String timeStr, String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(timeStr);
        return date;
    }

    /**
     * 获取该时间的 在那一天
     * 
     * @param currentTime
     * @return
     */
    public static int getDay(long currentTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取分钟
     * 
     * @param currentTime
     * @return
     */
    public static int getMinute(long currentTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取该时间在那一月
     * 
     * @param currentTime
     * @return
     */
    public static int getMonth(long currentTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前星期几 用常量判断
     * 
     * @param currentTime
     * @return
     */
    public static int getWeek(long currentTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取当前星期几
     * 
     * @param currentTime
     * @return
     */
    public static int getDayOfWeek(long currentTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        day--;
        if (day == 0) {
            day = 7;
        }
        return day;
    }

    /**
     * 获取本周星期几零点时间
     * 
     * @param week
     * @return
     */
    public static long getTimeOfWeek(int week) {
        long todayZeroTime = getZero(getServerTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTimeInMillis(todayZeroTime);
        int nowWeek = getWeek(calendar.getTimeInMillis()) - 1;
        if (nowWeek == 0) {
            nowWeek = 7;
        }
        int day = week - nowWeek;

        return todayZeroTime + (day * DAY_MILLIS);
    }

    /**
     * 获取当前的小时
     * 
     * @param currentTime
     * @return
     */
    public static int getHours(long currentTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取这个时间是一年中的第几天
     * 
     * @param currentTime
     * @return
     */
    public static int getDayNum(long currentTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int sum = 0;
        for (int i = 1; i < month; i++) {
            switch (i) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                sum += 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                sum += 30;
                break;
            case 2:
                if (((year % 4 == 0) && (year != 0)) || (year % 400 == 0))
                    sum += 29;
                else
                    sum += 28;
            }
        }
        return sum = sum + day;
    }

    /**
     * 根据毫秒获得小时分钟秒
     * 
     * @param timeMillis
     * @return
     */
    public static String getTimeByTimeMillis(long timeMillis) {
        return formateDate(timeMillis - TimeZone.getDefault().getRawOffset(), "HH:mm:ss");
    }

    /** 判断两个时间是否是同一天 */
    public static boolean isSameDay(long time1, long time2) {
        if (time1 - time2 > DAY_MILLIS || time2 - time1 > DAY_MILLIS) {
            return false;
        } else {
            Calendar now = Calendar.getInstance();
            now.setTimeInMillis(time1);
            int day1 = now.get(Calendar.DAY_OF_YEAR);
            now.setTimeInMillis(time2);
            int day2 = now.get(Calendar.DAY_OF_YEAR);
            if (day1 != day2) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断两个时间是否是同一周
     */
    public static boolean isSameWeek(long time1, long time2) {
        return isSameWeek(time1, time2, DayOfWeek.MONDAY);
    }

    /**
     * 判断两个时间是否是同一周
     */
    public static boolean isSameWeek(long time1, long time2, DayOfWeek startDayOfWeek) {
        if (time1 - time2 > WEEK_MILLIS || time2 - time1 > WEEK_MILLIS) {
            return false;
        }
        LocalDate localDate = timestamp2LocalDate(time1);
        long start = localDate.with(startDayOfWeek).atStartOfDay(ZONE_ID).toInstant().toEpochMilli();
        long end = start + WEEK_MILLIS;
        return time2 >= start && time2 < end;
    }

    public static LocalDate timestamp2LocalDate(long time) {
        return Instant.ofEpochMilli(time).atZone(ZONE_ID).toLocalDate();
    }

    /**
     * 判断日期是否是周一
     */
    public static boolean isMonday(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day == Calendar.MONDAY;
    }

    /**
     * java1.8获取周几 周1是1，周日7
     *
     * @return
     */
    public static int getWeekDay() {
        LocalDate now = LocalDate.now();
        return now.getDayOfWeek().getValue();
    }

    /**
     * 判断日期是否是周日
     */
    public static boolean isSunday(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day == Calendar.SUNDAY;
    }

    /**
     * 计算两个时间间隔几周
     */
    public static int getDifferWeeks(long time1, long time2) throws ParseException {
        int weeks = 0;
        /*
         * Calendar can1 = Calendar.getInstance();
         * can1.setFirstDayOfWeek(Calendar.MONDAY); can1.setTimeInMillis(time1);
         * Calendar can2 = Calendar.getInstance();
         * can2.setFirstDayOfWeek(Calendar.MONDAY); can2.setTimeInMillis(time2); //
         * 拿出两个年份 int year1 = can1.get(Calendar.YEAR); int year2 =
         * can2.get(Calendar.YEAR); Calendar can = null; if (can1.before(can2)) { weeks
         * -= can1.get(Calendar.WEEK_OF_YEAR); weeks += can2.get(Calendar.WEEK_OF_YEAR);
         * can = can1; } else { weeks -= can2.get(Calendar.WEEK_OF_YEAR); weeks +=
         * can1.get(Calendar.WEEK_OF_YEAR); can = can2; }
         * can.setFirstDayOfWeek(Calendar.MONDAY); for (int i = 0; i < Math.abs(year2 -
         * year1); i++) { // 获取小的时间当前年的总天数 weeks +=
         * can.getActualMaximum(Calendar.WEEK_OF_YEAR); // 再计算下一年。
         * can.add(Calendar.YEAR, 1); } return weeks;
         */
        CronExpression cronExpression = new CronExpression("0 0 0 ? * MON");
        long t1 = cronExpression.getNextValidTimeAfter(new Date(time1)).getTime();
        long t2 = cronExpression.getNextValidTimeAfter(new Date(time2)).getTime();
        weeks = (int) (Math.abs(t2 - t1) / WEEK_MILLIS);
        return weeks;
    }

    /**
     * 返回2个时间相差的天数 过凌晨算第二天
     * 
     * @param time1
     * @param time2
     * @return
     */
    public static int getDifferDays(long time1, long time2) {
        int days = 0;
        try {
            // 将转换的两个时间对象转换成Calendard对象
            Calendar can1 = Calendar.getInstance();
            can1.setTimeInMillis(time1);
            Calendar can2 = Calendar.getInstance();
            can2.setTimeInMillis(time2);
            // 拿出两个年份
            int year1 = can1.get(Calendar.YEAR);
            int year2 = can2.get(Calendar.YEAR);
            // 天数
            Calendar can = null;
            // 如果can1 < can2
            // 减去小的时间在这一年已经过了的天数
            // 加上大的时间已过的天数
            if (can1.before(can2)) {
                days -= can1.get(Calendar.DAY_OF_YEAR);
                days += can2.get(Calendar.DAY_OF_YEAR);
                can = can1;
            } else {
                days -= can2.get(Calendar.DAY_OF_YEAR);
                days += can1.get(Calendar.DAY_OF_YEAR);
                can = can2;
            }
            for (int i = 0; i < Math.abs(year2 - year1); i++) {
                // 获取小的时间当前年的总天数
                days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
                // 再计算下一年。
                can.add(Calendar.YEAR, 1);
            }
            return days;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 获得指定天数指定小时的毫秒数
     * 
     * @param offsetTime
     * @return
     */
    public static long getTimeInMillisByHour(long offsetTime, int hour) {
        Calendar today = Calendar.getInstance();
        today.setTimeInMillis(offsetTime);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        return today.getTimeInMillis() + (hour * TimeUtil.HOUR_MILLIS);
    }

    /**
     * 获得指定天数指定小时和分钟的毫秒数
     * 
     * @param offsetTime
     * @return
     */
    public static long getTimeInMillisByHourAndMin(long offsetTime, int hour, int min) {
        Calendar today = Calendar.getInstance();
        today.setTimeInMillis(offsetTime);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        return today.getTimeInMillis() + (hour * TimeUtil.HOUR_MILLIS) + (min * TimeUtil.MIN_MILLIS);
    }

    /**
     * 获得指定 年月日时分 的毫秒数
     * 
     * @param times
     * @return
     */
    public static long getTimeInMillisByTimes(String[] times) {
        Calendar time = Calendar.getInstance();
        int year = Integer.parseInt(times[0]);
        int month = Integer.parseInt(times[1]) - 1;
        int day = Integer.parseInt(times[2]);
        int hours = Integer.parseInt(times[3]);
        int minute = Integer.parseInt(times[4]);
        time.set(year, month, day, hours, minute, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
        return time.getTimeInMillis();
    }

    /**
     * 指定时间还未到
     * 
     * @param hour
     * @param min
     * @return
     */
    public static boolean isTodayTimeBeforeByTime(int hour, int min) {
        try {
            long currentTime = System.currentTimeMillis();
            long time = getTimeInMillisByHourAndMin(currentTime, hour, min);
            if (currentTime < time) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 副本是否在指定时间
     * 
     * @return
     */
	/*  public static boolean isBossInTime() {
	    try {
	        long currentTime = System.currentTimeMillis();
	        B_data_Bean data_bean = ManagerPool.getInstance().dataManager.c_data_Contailner.getMap().get(DataType.ALLIANCE_RANK.getValue());
	        String[] start = data_bean.getData4().split(",");
	        String[] end = data_bean.getData5().split(",");
	        long startTime = getTimeInMillisByHourAndMin(currentTime, Integer.valueOf(start[0]), Integer.valueOf(start[1]));
	        long endTime = getTimeInMillisByHourAndMin(currentTime, Integer.valueOf(end[0]), Integer.valueOf(end[1]));
	        if (currentTime > startTime && currentTime < endTime) {
	            return true;
	        }
	        return false;
	    } catch (Exception e) {
	        return false;
	    }
	}*/

    /**
     * 解析时分秒时间为秒数
     * 
     * @param formatTime
     * @return
     */
    public static int parseFormatTime(String formatTime) {
        if (formatTime == null || "".equals(formatTime))
            return 0;
        int seconds = 0;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        int offsetTime = TimeZone.getDefault().getRawOffset();
        try {
            seconds = (int) ((format.parse(formatTime).getTime() + offsetTime) / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seconds;
    }

    /**
     * 获取指定时间与当天凌晨秒数只差
     * 
     * @return
     */
    public static long getSecondOfDay(long nowTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long tempTime = calendar.getTimeInMillis();
        return nowTime - tempTime;
    }

    /**
     * 获取传入时间当天零点
     *
     * @return
     */
    public static long getZero(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取今天的零点
     *
     * @return
     */
    public static long getTodayZero() {
        return getZero(System.currentTimeMillis());
    }

    /**
     * 获取今天的12点
     *
     * @return
     */
    public static long getTodayNoon() {
        return getNoon(System.currentTimeMillis());
    }

    /**
     * 获取传入时间当天12点
     *
     * @return
     */
    public static long getNoon(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * 通根据当前时间和间隔时间转成出新时间
     *
     * @param time
     * @return
     */
    public static long getNewTime(long time, long intervalTime) {
        return time + intervalTime;
    }

    /**
     * 根据2个时间获取分钟之差
     * 
     * @param time1
     * @param time2
     * @return
     */
    public static int getMinuteByTwoTime(long time1, long time2) {
        long time = Math.abs(time1 - time2);
        return (int) (time / TimeUtil.MIN_MILLIS);
    }

    /**
     * 根据时间配置数组，获取时间（用于活动时间，道具合成时间等等配置）
     * 
     * @param timeArray
     * @return 毫秒
     */
    public static long getActivityTimeByArray(String timeArray) {
        try {
            // 毫秒
            long time = 0L;
            int[] timeParams = StringUtil.str2IntArray(timeArray, ",");
            if (timeParams[0] == 1) {
                int day = timeParams[1];
                int hours = timeParams[2];
                int minute = timeParams[3];
                Date date=null;
                //Date date = GameServer.getInstance().getServerConfig().getOpen();
                time += date.getTime();
                time += day * TimeUtil.DAY_MILLIS;
                time += hours * TimeUtil.HOUR_MILLIS;
                time += minute * TimeUtil.MIN_MILLIS;
            } else {
                Calendar calendar = Calendar.getInstance();
                int year = timeParams[0];
                int month = timeParams[1] - 1;
                int day = timeParams[2];
                int hours = timeParams[3];
                int minute = timeParams[4];

                // 秒数强制为0,不然活动的开启时间,结束时间,结束领奖时间是会变化的,秒数是当前秒数,不是固定一个时间点
                calendar.set(year, month, day, hours, minute, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                time = calendar.getTimeInMillis();
            }
            return time;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 根据时间配置数组，获取时间（用于活动时间，道具合成时间等等配置）
     *
     * @param timeArray
     * @return
     */
    public static long getWeekTimeByArray(String timeArray) {
        try {
            long time = 0L;
            String[] times = timeArray.split(",");
            time = getTimeOfWeek(Integer.valueOf(times[0]));

            time += Integer.valueOf(times[1]) * HOUR_MILLIS;
            time += Integer.valueOf(times[2]) * MIN_MILLIS;
            time += Integer.valueOf(times[3]) * MILLIS;
            return time;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 根据传进来的秒数来获取天数
     * 
     * @param time
     * @return
     */
    public static double getDayNum(double time) {
        return time / (60 * 60 * 24);
    }

    /**
     * 获取时间 毫秒
     * 
     * @param time =年,月,日,时,分
     * @return
     */
    public static long getTimeByArray(String time) {
        try {
            // 秒
            long timerMinutes = 0L;
            String[] times = time.split(",");
            Calendar a = Calendar.getInstance();
            int year = Integer.parseInt(times[0]);
            int month = Integer.parseInt(times[1]) - 1;
            int day = Integer.parseInt(times[2]);
            int hours = Integer.parseInt(times[3]);
            int minute = Integer.parseInt(times[4]);
            a.set(year, month, day, hours, minute);
            a.set(Calendar.SECOND, 0);
            a.set(Calendar.MILLISECOND, 0);
            timerMinutes = a.getTimeInMillis();
            return timerMinutes;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 获取开服天数<br>
     * 开服当天为第一天<br>
     * 开服之前为负数
     * 
     * @return
     */
    public static int getOpenServerDay() {
        Date date = null;
        Instant openServerInstant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime openServerDateTime = ZonedDateTime.ofInstant(openServerInstant, zoneId);
        LocalDate openServerDate = openServerDateTime.toLocalDate();
        LocalDate todayDate = LocalDate.now(zoneId);
        int days = (int) openServerDate.until(todayDate, ChronoUnit.DAYS);
        return days + 1;
    }

    /**
     * 判断当前日期是否是该月的第一天
     */
    public static boolean isFirstDayOfMonth() {
        LocalDate today = LocalDate.now();
        return today.with(TemporalAdjusters.firstDayOfMonth()).isEqual(today);
    }

    /**
     * 获取当前月份
     */
    public static int obtainMonth() {
        LocalDate today = LocalDate.now();
        return today.getMonthValue();
    }

    /**
     * 判断当前日期是否是该月的最后一天
     * 
     * @return false-不是最后一天
     */
    public static boolean isLastOfMonth() {
        LocalDate today = LocalDate.now();
        return today.with(TemporalAdjusters.lastDayOfMonth()).isEqual(today);
    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取时区
     */
    public static int obtainTimeZone() {
        Calendar cal = Calendar.getInstance();
        int offset = cal.get(Calendar.ZONE_OFFSET);
        cal.add(Calendar.MILLISECOND, -offset);
        long time = cal.getTimeInMillis();
        long timeStamp = getServerTime();
        long timeZone = (timeStamp - time) / (1000 * 3600);
        return (int) timeZone;
    }

    /**
     * 获取根据年月获取0点时间戳
     *
     * @return
     */
    public static long getZeroTimeOfMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.set(Calendar.HOUR_OF_DAY, 0);
        a.set(Calendar.MINUTE, 0);
        a.set(Calendar.SECOND, 0);
        a.set(Calendar.MILLISECOND, 0);
        return a.getTimeInMillis();
    }

    /**
     * 获取完整的时间参数, 年月日时分秒 通常用于参数的格式化处理
     *
     * @param time
     * @return
     */
    public static String[] getFullTimeArgs(long time) {
        String formatTime = formateDate(time, YYYY_MM_DD_HH_MM_SS);
        return formatTime.split(",");
    }

    /**
     * 获取完整的时间参数, 年月日时分秒 通常用于参数的格式化处理
     *
     * @param time
     * @param pattern
     * @return
     */
    public static String[] getFullTimeArgs(long time, String pattern) {
        String formatTime = formateDate(time, pattern);
        return formatTime.split(",");
    }

    /**
     * 获取本月的0点时间戳
     *
     * @param year
     * @param month
     * @return
     */
    public static long getZeroTimeOfMonth(int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.set(Calendar.HOUR_OF_DAY, 0);
        a.set(Calendar.MINUTE, 0);
        a.set(Calendar.SECOND, 0);
        a.set(Calendar.MILLISECOND, 0);
        return a.getTimeInMillis();
    }

    /**
     * 判断传入时间是否越过当前时间\ 是否是上架时间
     */
    public static boolean isOnTime(int time) {
        long now = getServerTime();
        return isOnTime(time, now);
    }

    /**
     * 判断传入时间是否越过当前时间\ 是否是上架时间
     */
    public static boolean isOnTime(int time, long now) {
        return now >= TimeUnit.SECONDS.toMillis(time);
    }

    /**
     * 毫秒转为秒
     * 
     * @param milliseconds
     * @return
     */
    public static int ms2s(long milliseconds) {
        long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);
        return (int) seconds;
    }



    /**
     * 秒转毫秒
     *
     * @param second
     * @return long
     */
    public static long s2ms(int second) {
        return TimeUnit.SECONDS.toMillis(second);
    }


}
