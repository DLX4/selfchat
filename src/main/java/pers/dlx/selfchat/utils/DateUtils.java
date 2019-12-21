package pers.dlx.selfchat.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author dinglingxiang
 */
public class DateUtils {
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final String DEFAULT_DB_DATE_TIME_FORMAT = "yyyyMMddHHmmss";
    public static final String DEFAULT_SYSTEM_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_SYSTEM_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DEFAULT_DB_DATE_FORMAT = "yyyyMMdd";
    public static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd";
    public static final String SEQUENCE_DATE_FORMAT = "yyMMdd";
    public static final String DEFAULT_SYSTEM_MONTH_FORMAT = "yyyyMM";
    public static final String MONTH_DAY_FORMAT = "M.d";
    public static final String YEAR_MONTH_DAY_FORMAT = "yy.M.d";

    public static String format(java.util.Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static java.util.Date parse(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatYearMonthDay(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return format(date, YEAR_MONTH_DAY_FORMAT);
    }

    public static String formatMonthDay(java.util.Date date) {
        if (date == null)
            return null;
        return format(date, MONTH_DAY_FORMAT);
    }

    public static String formatDateTime(java.util.Date date) {
        if (date == null)
            return null;
        return format(date, DEFAULT_DATE_TIME_FORMAT);
    }

    public static String formatTimestamp(java.util.Date date) {
        if (date == null)
            return null;
        return format(date, DEFAULT_SYSTEM_TIMESTAMP_FORMAT);
    }

    public static String formatSystemDateTime(java.util.Date date) {
        if (date == null)
            return null;
        return format(date, DEFAULT_SYSTEM_DATE_TIME_FORMAT);
    }

    public static java.util.Date parseDateTime(String date) {
        if (date == null)
            return null;
        return parse(date, DEFAULT_DATE_TIME_FORMAT);
    }

    public static String formatDbDateTime(java.util.Date date) {
        if (date == null)
            return null;
        return format(date, DEFAULT_DB_DATE_TIME_FORMAT);
    }

    public static String formatDbMonth(java.util.Date date) {
        if (date == null)
            return null;
        return format(date, DEFAULT_SYSTEM_MONTH_FORMAT);
    }

    public static java.util.Date parseSystemDateTime(String date) {
        if (date == null || "".equals(date))
            return null;
        return parse(date, DEFAULT_SYSTEM_DATE_TIME_FORMAT);
    }

    public static java.util.Date parseDbDateTime(String date) {
        if (date == null)
            return null;
        return parse(date, DEFAULT_DB_DATE_TIME_FORMAT);
    }

    public static String formatDate(java.util.Date date) {
        if (date == null)
            return null;
        return format(date, DEFAULT_DATE_FORMAT);
    }

    public static String formatDbDate(java.util.Date date) {
        if (date == null)
            return null;
        return format(date, DEFAULT_DB_DATE_FORMAT);
    }

    public static java.util.Date parseDate(String date) {
        return parse(date, DEFAULT_DATE_FORMAT);
    }

    public static java.util.Date getNow() {
        return new java.util.Date();
    }

    public static java.util.Date getNowWithoutTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static short getYear() {
        return (short) Calendar.getInstance().get(Calendar.YEAR);
    }

    public static short getYear(java.util.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return (short) c.get(Calendar.YEAR);
    }

    public static short getHour() {
        return (short) Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    public static short getHour(java.util.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return (short) c.get(Calendar.HOUR_OF_DAY);
    }

    public static short getMinute() {
        return (short) Calendar.getInstance().get(Calendar.MINUTE);
    }

    public static short getMinute(java.util.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return (short) c.get(Calendar.MINUTE);
    }

    public static short getWeek() {
        return (short) Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public static short getWeek(java.util.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return (short) c.get(Calendar.DAY_OF_WEEK);
    }

    public static short getMonth() {
        return (short) Calendar.getInstance().get(Calendar.MONTH);
    }

    public static short getMonth(java.util.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return (short) c.get(Calendar.MONTH);
    }

    public static java.util.Date getYesterday(java.util.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    public static java.util.Date getYesterday() {
        return getYesterday(getNow());
    }

    public static java.util.Date getDate(java.util.Date date, int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(field, amount);
        return c.getTime();
    }

    public static java.util.Date getDate(int field, int amount) {
        return getDate(getNow(), field, amount);
    }

    /**
     * 将指定日期的时间设为"23时59分59秒"
     *
     * @param date 日期
     * @return 最大时分秒日期
     */
    public static java.util.Date setMaxTimeToDate(java.util.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    /**
     * 将指定日期的时间设为"0时0分0秒"
     *
     * @param date 日期
     * @return 最小时分秒日期
     */
    public static java.util.Date setMinTimeToDate(java.util.Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    /**
     * 将日期格式字符串转为正确格式
     *
     * @param strDate 日期字符串
     * @return
     */
    public static String convertDateString(String strDate) {
        if (strDate.indexOf('/') != -1) {
            Date date = parseDateTime(strDate);
            return formatSystemDateTime(date);
        }
        return strDate;
    }

    /**
     * 计算两个日期相差的天数
     *
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_SYSTEM_DATE_TIME_FORMAT);
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;

        cal.setTime(sdf.parse(smdate));
        time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        time2 = cal.getTimeInMillis();

        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个日期相差的天数
     *
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;

        cal.setTime(smdate);
        time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        time2 = cal.getTimeInMillis();

        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个日期相差的秒数
     *
     * @param start
     * @param end
     * @return
     */
    public static int secondsBetween(Date start, Date end) {
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;

        cal.setTime(start);
        time1 = cal.getTimeInMillis();
        cal.setTime(end);
        time2 = cal.getTimeInMillis();

        long betweenSeconds = (time2 - time1) / (1000);
        return Integer.parseInt(String.valueOf(betweenSeconds));
    }

    /**
     * 获取当前时间 seconds秒之前的时间
     *
     * @param seconds
     * @return
     */
    public static Date getSecondsBefore(int seconds) {
        seconds *= -1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    /**
     * 获取Date时间 seconds秒之前的时间
     *
     * @param seconds
     * @return
     */
    public static Date getSecondsBefore(Date date, int seconds) {
        seconds *= -1;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, seconds);
        return calendar.getTime();
    }
}
