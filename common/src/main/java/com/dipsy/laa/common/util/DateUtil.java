package com.dipsy.laa.common.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 *
 * @auther cuiqiongyu
 * @create 2018/5/14 22:07
 */
public class DateUtil {

    // 缺省的日期显示格式： yyyy-MM-dd
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    // 缺省的日期显示格式：yyyy-MM-dd HH:mm:ss
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    // 缺省的日期显示格式：yyyy/MM/dd HH:mm:ss
    public static final String DATETIME_FORMA = "yyyy/MM/dd HH:mm:ss";

    // 缺省的日期时间显示格式：yyyy-MM-dd HH:mm
    public static final String DEFAULT_DATETIME_FORMAT_HM = "yyyy-MM-dd HH:mm";

    // 缺省的日期时间显示格式：yyyy-MM-dd HH
    public static final String DEFAULT_DATETIME_FORMAT_HH = "yyyy-MM-dd HH";

    // 日期入库格式：yyyyMMdd
    public static final String DEFAULT_DATE_FORMAT_SHT = "yyyyMMdd";

    // 日期入库格式：yyyyMMddHH
    public static final String DEFAULT_DATE_FORMAT_SHT_HH = "yyyyMMddHH";

    // yyyyMMddHHmmss
    public static final String DEFAULT_DATETIME_FORMAT_SHT = "yyyyMMddHHmmss";

    // yyyyMMddHHmm
    public static final String DEFAULT_DATETIME_MINTIME_SHT = "yyyyMMddHHmm";

    // yyyy年MM月dd日
    public static final String DEFAULT_DATE_FORMAT_CHINESE = "yyyy年MM月dd日";

    // yyyy年MM月
    public static final String DEFAULT_DATE_FORMAT_MONTH = "yyyyMM";

    // 一周开始的时间
    private static int FIRST_DATE_OF_WEEK = Calendar.SUNDAY;

    /**
     * 私有构造方法，禁止对该类进行实例化
     */
    private DateUtil() {

    }

    /**
     * @param date 时间
     * @param pattern 日期展示格式
     * @return 转换后的字符串格式时间
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * @param date 日期字符串
     * @param pattern 日期展示格式
     * @return 时间
     */
    public static Date parse(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date d = sdf.parse(date);
            return d;
        } catch (ParseException e) {
            throw new RuntimeException("日期转换错误", e);
        }
    }

    /**
     * @param date 计算前的日期
     * @param calendorField 添加字段的类型：天，周，月……
     * @param amount 添加的数量
     * @return 计算后的日期
     */
    public static Date add(Date date, int calendorField, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(calendorField, amount);
        return cal.getTime();
    }

    /**
     * 获取日期是周几
     * @param date 日期
     * @return 周几
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        switch (cal.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return 7;
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 4;
            case 6:
                return 5;
            default:
                return 6;
        }
    }

    /**
     * 获取传入时间的月份
     * @param date 时间
     * @return 月份
     */
    public static int getDayOfMouth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 计算两个日期间相差的天数
     * @param date 基础日期
     * @param compareDate 带比较的日期
     * @return 相隔的天数
     */
    public static long compareTo(Date date, Date compareDate) {
        // 去掉时分秒
        date = parse(format(date, "yyMMdd"), "yyMMdd");
        compareDate = parse(format(compareDate, "yyMMdd"), "yyMMdd");
        long a = (date.getTime() - compareDate.getTime()) / (1000 * 60 * 60 * 24);
        return a;
    }

    /**
     * 判断是否为一周的最后一天(目前配置的是周日为一周的第一天)
     * @param date 日期
     * @return 是否是一周的最后一天
     */
    public static boolean isEndOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        int weekDay = cal.get(Calendar.DAY_OF_WEEK);
        if (weekDay == FIRST_DATE_OF_WEEK) {
            return true;
        }
        return false;
    }

    /**
     * 判断时间是否为月末
     * @param nowDate 日期（需要验证的日期）
     * @return boolean true 表示是月末 false 表示不为月末
     *
     */
    public static boolean isMonthEnd(Date nowDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day == 1) {
            return true;
        }
        return false;
    }

    /**
     * 判断时间是否为季末
     * @param nowDate 日期（需要验证的日期）
     * @return boolean true 表示是季末 false 表示不是季末
     */
    public static boolean isQuarterEnd(Date nowDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        int month = cal.get(Calendar.MONTH);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day == 1 && (month == Calendar.MARCH || month == Calendar.JUNE
                || month == Calendar.SEPTEMBER || month == Calendar.DECEMBER)) {
            return true;
        }
        return false;
    }

    /**
     * 判断时间是否为季出
     * @param nowDate 日期（需要验证的日期）
     * @return boolean true 表示是季初 false 表示不是季初
     */
    public static boolean isQuarterBegin(Date nowDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day == 1 && (month == Calendar.JANUARY || month == Calendar.APRIL
                || month == Calendar.JULY || month == Calendar.OCTOBER)) {
            return true;
        }
        return false;
    }


    /**
     * 判断时间是否为半年末
     * @param nowDate 日期（需要验证的日期）
     * @return boolean true 表示是半年末 false 表示不是半年末
     */
    public static boolean isHalfYearEnd(Date nowDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        int month = cal.get(Calendar.MONTH);
        cal.add(Calendar.DAY_OF_MONTH, 1);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day == 1 && (month == Calendar.JUNE || month == Calendar.DECEMBER)) {
            return true;
        }
        return false;
    }

    /**
     * 判断时间是否为半年出
     * @param nowDate 日期（需要验证的日期）
     * @return boolean true 表示是半年初 false 表示不是半年初
     */
    public static boolean isHalfYearBegin(Date nowDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day == 1 && (month == Calendar.JANUARY || month == Calendar.JULY)) {
            return true;
        }
        return false;
    }

    /**
     * 判断时间是否为年末
     * @param nowDate 日期（需要验证的日期）
     * @return boolean true 表示是年末 false 表示不为年末
     */
    public static boolean isYearEnd(Date nowDate) {
        if ("1231".equals(format(nowDate, "MMdd"))) {
            return true;
        }
        return false;
    }

    /**
     * 判断时间是否为年初
     * @param nowDate 日期（需要验证的日期）
     * @return boolean true 表示是年初 false 表示不为年初
     */
    public static boolean isYearBegin(Date nowDate) {
        if ("0101".equals(format(nowDate, "MMdd"))) {
            return true;
        }
        return false;
    }

    /**
     * 获取日期的年月日
     * @param date
     * @return
     */
    public static Calendar getYMD(Date date) {
        String dateStr = format(date, "yyyyMMdd");
        date = parse(dateStr, "yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 判断是否为结算日期
     * @param stlCycle
     * @param stlCycleDay
     * @param tranDate
     * @return
     */
    public static boolean chkStlTime(String stlCycle, String stlCycleDay, Date tranDate) {
        boolean b = false;
        switch (stlCycle.toCharArray()[0]) {
            case '1':
                // 日结
                b = true;
                break;
            case '2':
                // 周结
                String nowDate = String.valueOf(DateUtil.getDayOfWeek(tranDate));
                if (nowDate.equals(stlCycleDay)) {
                    b = true;
                }
                break;
            case '3':
                // 月结
                if (stlCycleDay.equals("0")) {
                    // 月末结
                    boolean result = DateUtil.isMonthEnd(tranDate);
                    if (result) {
                        b = true;
                    }
                } else {
                    // 非月末结
                    String nowDate1 = String.valueOf(DateUtil.getDayOfMouth(tranDate));
                    if (nowDate1.equals(stlCycleDay)) {
                        b = true;
                    }
                }
                break;
            case '4':
                // 季结
                if ("1".equals(stlCycleDay)) {
                    // 季初
                    if (DateUtil.isQuarterBegin(tranDate)) {
                        b = true;
                    }
                } else if ("0".equals(stlCycleDay)) {
                    // 季末
                    if (DateUtil.isQuarterEnd(tranDate)) {
                        b = true;
                    }
                }
                break;
            case '5':
                // 半年结
                if ("1".equals(stlCycleDay)) {
                    // 半年初
                    if (DateUtil.isHalfYearBegin(tranDate)) {
                        b = true;
                    }
                } else if ("0".equals(stlCycleDay)) {
                    // 半年末
                    if (DateUtil.isHalfYearEnd(tranDate)) {
                        b = true;
                    }
                }
                break;
            case '6':
                // 年结
                if ("1".equals(stlCycleDay)) {
                    // 年初
                    if (DateUtil.isYearBegin(tranDate)) {
                        b = true;
                    }
                } else if ("0".equals(stlCycleDay)) {
                    // 年末
                    if (DateUtil.isYearEnd(tranDate)) {
                        b = true;
                    }
                }
                break;
            default:
                break;
        }
        return b;
    }

    /**
     * 比较频繁交易前后两笔的时间间隔与指定的某个时间对比，在这个时间段内，是频繁交易
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param interTime 指定的频繁次数
     * @return
     */
    public static boolean monFreCompare(Date startTime, Date endTime, int interTime) {
        boolean flag = false;
        long a = (endTime.getTime() - startTime.getTime());
        // 两笔交易的时间间隔<=interTime,是频繁交易
        long interval = a / 1000;
        if(interval<=interTime && interval > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * cst格式的时间字符串解析成日期
     * @param cstStr cst格式的时间字符串
     * @return
     */
    public static Date parseCSTStrToDate(String cstStr){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date date = null;
        try {
            if(StringUtils.isNotBlank(cstStr)){
                date = simpleDateFormat.parse( cstStr );
            }
        } catch (ParseException e) {

        }
        return date;
    }

    /**
     * 获得指定日期的前一天或后一天
     * @param nDay nDay>0 表示后N天；nDay<0,表示前N天
     * @param date
     * @param pattern
     * @return
     */
    public static Date getSpecifiedDayAfter(int nDay,Date date,String pattern){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day+nDay);
        String dayAfter=new SimpleDateFormat(pattern).format(c.getTime());
        return DateUtil.parse( dayAfter , pattern);
    }

    /**
     * 得到系统当前日期时间
     * @return 当前日期时间
     */
    public static Date getNow() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 得到用缺省方式格式化的当前日期
     * @return 当前日期
     */
    public static String getDate() {
        return getDateTime(DEFAULT_DATE_FORMAT);
    }

    /**
     * 得到用缺省方式格式化的当前日期
     * @return 当前日期
     */
    public static String getDateSHT() {
        return getDateTime(DEFAULT_DATE_FORMAT_SHT);
    }

    /**
     * 得到用缺省方式格式化的当前日期及时间
     * @return 当前日期及时间
     */
    public static String getDateTime() {
        return getDateTime(DEFAULT_DATETIME_FORMAT_SHT);
    }

    /**
     * 得到系统当前日期及时间，并用指定的方式格式化
     * @param pattern 显示格式
     * @return 当前日期及时间
     */
    public static String getDateTime(String pattern) {
        Date datetime = Calendar.getInstance().getTime();
        return getDateTime(datetime, pattern);
    }

    /**
     * 得到用指定方式格式化的日期
     * @param date 需要进行格式化的日期
     * @param pattern 显示格式
     * @return 日期时间字符串
     */
    public static String getDateTime(Date date, String pattern) {
        if (null == pattern || "".equals(pattern)) {
            pattern = DEFAULT_DATETIME_FORMAT;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 得到当前年份
     * @return 当前年份
     */
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 得到当前月份
     * @return 当前月份
     */
    public static int getCurrentMonth() {
        // 用get得到的月份数比实际的小1，需要加上
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * 得到当前日
     * @return 当前日
     */
    public static int getCurrentDay() {
        return Calendar.getInstance().get(Calendar.DATE);
    }

    /**
     * 得到当前小时
     * @return
     */
    public static int getCurrentHours() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 得到当前的分钟
     * @return
     */
    public static int getCurrentMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    /**
     * 得到当前的秒
     * @return
     */
    public static int getCurrentSecond() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    /**
     * 得到当前的时分秒
     * @return
     */
    public static String getCurrentHms() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
    }

    /**
     * 取得当前日期以后若干天的日期。如果要得到以前的日期，参数用负数。 例如要得到上星期同一天的日期，参数则为-7
     * @param days 增加的日期数
     * @return 增加以后的日期
     */
    public static Date addDays(int days) {
        return add(getNow(), Calendar.DATE, days);
    }

    /**
     * 根据时间转换为指定格式时间
     * @param date
     * @param pattern
     * @return
     */
    public static Date getDateReDate(Date date, String pattern) throws Exception {
        Date oneDate = DateUtil.parse(DateUtil.getDateTime(date, pattern), pattern);
        return oneDate;
    }

    /**
     * 取得指定日期以后若干天的日期。如果要得到以前的日期，参数用负数。
     * @param date 基准日期
     * @param days 增加的日期数
     * @return 增加以后的日期
     */
    public static Date addDays(Date date, int days) {
        return add(date, Calendar.DATE, days);
    }

    /**
     * 取得当前日期以后某月的日期。如果要得到以前月份的日期，参数用负数。
     * @param months 增加的月份数
     * @return 增加以后的日期
     */
    public static Date addMonths(int months) {
        return add(getNow(), Calendar.MONTH, months);
    }

    /**
     * 取得指定日期以后某月的日期。如果要得到以前月份的日期，参数用负数。 注意，可能不是同一日子，例如2003-1-31加上一个月是2003-2-28
     * @param date 基准日期
     * @param months 增加的月份数
     * @return 增加以后的日期
     */
    public static Date addMonths(Date date, int months) {
        return add(date, Calendar.MONTH, months);
    }

    /**
     * 计算两个日期相差毫秒数。 用第一个日期减去第二个。如果前一个日期小于后一个日期，则返回负数
     * @param one 第一个日期数，作为基准
     * @param two 第二个日期数，作为比较
     * @return 两个日期相差毫秒数
     */
    public static long diffMilliseconds(Date one, Date two) {
        return one.getTime() - two.getTime();
    }

    /**
     * 计算两个日期相差秒数。 用第一个日期减去第二个。如果前一个日期小于后一个日期，则返回负数
     * @param one 第一个日期数，作为基准
     * @param two 第二个日期数，作为比较
     * @return 两个日期相差秒数
     */
    public static long diffSecond(Date one, Date two) {
        return diffMilliseconds(one, two) / 1000;
    }

    /**
     * 计算两个日期相差小时数。 用第一个日期减去第二个。如果前一个日期小于后一个日期，则返回负数
     * @param one 第一个日期数，作为基准
     * @param two 第二个日期数，作为比较
     * @return 两个日期相差秒数
     */
    public static long diffHour(Date one, Date two) {
        return diffSecond(one, two) / 3600;
    }

    /**
     * 计算两个日期相差天数。 用第一个日期减去第二个。如果前一个日期小于后一个日期，则返回负数
     * @param one 第一个日期数，作为基准
     * @param two 第二个日期数，作为比较
     * @return 两个日期相差天数
     */
    public static long diffDays(Date one, Date two) {
        return diffHour(one, two) / 24;
    }

    /**
     * 计算两个日期相差月份数 如果前一个日期小于后一个日期，则返回负数
     * @param one 第一个日期数，作为基准
     * @param two 第二个日期数，作为比较
     * @return 两个日期相差月份数
     */
    public static int diffMonths(Date one, Date two) {
        Calendar calendar = Calendar.getInstance();

        // 得到第一个日期的年分和月份数
        calendar.setTime(one);
        int yearOne = calendar.get(Calendar.YEAR);
        int monthOne = calendar.get(Calendar.MONDAY);

        // 得到第二个日期的年份和月份
        calendar.setTime(two);
        int yearTwo = calendar.get(Calendar.YEAR);
        int monthTwo = calendar.get(Calendar.MONDAY);

        return (yearOne - yearTwo) * 12 + (monthOne - monthTwo);
    }

    /**
     * 返回本月的最后一天
     * @return 本月最后一天的日期
     */
    public static Date getMonthLastDay() {
        return getMonthLastDay(getNow());
    }

    /**
     * 返回本月的第一天
     * @return
     */
    public static Date getMonthFirstDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        return c.getTime();
    }

    /**
     * 返回给定日期中的月份中的最后一天
     * @param date 基准日期
     * @return 该月最后一天的日期
     */
    public static Date getMonthLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 将日期设置为下一月第一天
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1);
        // 减去1天，得到的即本月的最后一天
        calendar.add(Calendar.DATE, -1);

        return calendar.getTime();
    }

    /**
     * 处理字符串格式的时间
     * @param timeStr
     * @return
     */
    public static String trimAfterPointer(String timeStr) {
        String result = "";
        if (!StringUtils.isBlank(timeStr)) {
            result = timeStr.contains(".") ? timeStr.substring(0, timeStr.indexOf(".")) : timeStr;
        }
        return result;
    }

    /**
     * 将Timestamp格式的时间转为String
     * @param time
     * @return
     */
    public static String formatTimestamp(Timestamp time) {
        String result = "";

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time.getTime());
        Date current = cal.getTime();

        SimpleDateFormat format = new SimpleDateFormat(DateUtil.DEFAULT_DATETIME_FORMAT);
        result = format.format(current);

        return result;
    }

    /**
     * 将毫秒数转换成日期
     * @param millisecondStr 毫秒数
     * @param pattern 日期格式
     * @return
     */
    public static String formatMillisecond(String millisecondStr, String pattern) {
        if (null == pattern || "".equals(pattern)) {
            pattern = DEFAULT_DATE_FORMAT;
        }
        if (null == millisecondStr || "".equals(millisecondStr)) {
            return "";
        }

        String dateStr = "";
        try {
            Date date = new Date(Long.parseLong(millisecondStr));
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            dateStr = dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;

    }

    /**
     * 返回SimpleDateFormat对象
     * @param pattern 格式
     * @return SimpleDateFormat对象
     */
    public static SimpleDateFormat getSDFByPattern(String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat;

    }

    /**
     * 将格式为时间戳的日期转化为日期
     * @param dateVals
     * @return
     */
    public static String generateDate(String dateVals) {
        SimpleDateFormat format;
        SimpleDateFormat daters;
        String result = "";
        format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date dates;
        try {
            dates = format.parse(dateVals);
            daters = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
            result = daters.format(dates);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 得到3天之前的日期
     */
    public static String getBefore3Days() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - 2);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }
    /**
     * 得到7天之前的日期
     */
    public static String getBefore7Days() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - 6);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    /**
     * 得到某天之前的日期
     */
    public static String getBeforeDays(int days) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - (days-1));
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }
    /**
     * 得到昨天的日期
     */
    public static String getYesterdayDate() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    /**
     * 得到昨天时间
     */
    public static String getBefore24Hours() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.HOUR, date.get(Calendar.HOUR) - 24);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    /**
     * 获取24小时前的时间
     * @return
     */
    public static String getBefore24HoursTime() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMddHHmmss");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.HOUR, date.get(Calendar.HOUR) - 24);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    /**
     * 时间差
     * @param smallTime
     * @param greatTime
     * @return
     */
    public static int comparateTime(String smallTime, String greatTime) {
        SimpleDateFormat dft = new SimpleDateFormat(DateUtil.DEFAULT_DATETIME_FORMAT);
        long subtime = 0l;
        try {
            subtime = dft.parse(greatTime).getTime() - dft.parse(smallTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) (subtime / 1000);
    }

    /**
     * 获取当前月日期列表
     * @return
     */
    public static List<String> getCurrentDateList() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        List<String> dataList = new ArrayList<String>();
        for (int i = 1; i <= day; i++) {
            dataList.add(year + "-" + "0" + month + "-" + (i < 10 ? "0" + i : i));
        }
        return dataList;
    }

    /**
     * 获取某月的所有日期
     * @param year
     * @param month
     * @return
     */
    public static Date[] getDatesOfMonth(String year, String month) {
        int maxDate = 0;
        Date first = null;
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT_MONTH);
            first = sdf.parse(year + month);
            cal.setTime(first);
            maxDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date[] dates = new Date[maxDate];
        for (int i = 1; i <= maxDate; i++) {
            dates[i - 1] = new Date(first.getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(first);
            dates[i - 1] = calendar.getTime();
            calendar.add(Calendar.DATE, 1);
            first = calendar.getTime();
        }
        return dates;
    }

    /**
     * 获取给定时间的 往后某天日期
     * @param date
     * @param days
     * @return
     */
    public static Date getAfterDate(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, days);
        Date d = c.getTime();
        return d;
    }

    /**
     * 获取给定时间的往后给定秒的时间
     * @param date
     * @param time
     * @return
     */
    public static Date getAfterTime(Date date, int time) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, time);
        Date d = c.getTime();
        return d;
    }
    /**
     * 获取给定时间的完后给定小时的时间
     * @param date
     * @param hour
     * @return
     */
    public static Date getAfterHour(Date date, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, hour);
        Date d = c.getTime();
        return d;
    }
    /**
     * 获取给定时间 延时 小时数 的小时
     * @param date
     * @param hour
     * @return
     */
    public static int getHourByAfter(Date date, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, hour);
        return c.get(Calendar.HOUR_OF_DAY);
    }
    /**
     * 获取某天凌晨时间
     * @param date
     * @return
     */
    public static String getBeforeDawn(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.MILLISECOND, 0);
        return DateUtil.getSDFByPattern(DateUtil.DEFAULT_DATETIME_FORMAT).format(c.getTime());
    }


    /**
     * 获取整点时间
     * @param date
     * @param hour
     * @param smm
     * @return
     */
    public static Date getHourTime(Date date, int hour, int smm) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.SECOND, smm);
        c.set(Calendar.MINUTE, smm);
        c.set(Calendar.MILLISECOND, smm);
        return c.getTime();
    }
    /**
     * 获取某天24点时间
     * @param date
     * @return
     */
    public static String getTimesnight(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 24);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.MILLISECOND, 0);
        return DateUtil.getSDFByPattern(DateUtil.DEFAULT_DATETIME_FORMAT).format(c.getTime());
    }

    /**
     * 获取某天凌晨时间
     * @param date
     * @return
     */
    public static Date get0HourDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取某天24点时间
     * @param date
     * @return
     */
    public static Date get24HourDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 24);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 时间处理，用于页面显示
     * @param date
     * @return
     * @throws ParseException
     */
    public static String ConvertDateFormat(String date) throws ParseException {
        SimpleDateFormat dateDF = new SimpleDateFormat(DateUtil.DEFAULT_DATETIME_FORMAT);
        SimpleDateFormat defaultDF = new SimpleDateFormat(DateUtil.DEFAULT_DATETIME_FORMAT);
        return defaultDF.format(dateDF.parse(date));
    }

    /**
     * 获取当前月的第一天包含 00:00:00
     * @return
     */
    public static Date getMonthStartDay(){
        SimpleDateFormat dateDF = new SimpleDateFormat(DateUtil.DEFAULT_DATETIME_FORMAT);
        //获取当前时间
        Calendar cal = Calendar.getInstance();
        int MinDay=cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //按你的要求设置时间
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),MinDay,0,0,0);

        try {
            return dateDF.parse(dateDF.format(cal.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据时间获取该时间所在月份的第一天
     * @param date
     * @return
     */
    public static Date getMonthStartDay(Date date){
        SimpleDateFormat dateDF = new SimpleDateFormat(DateUtil.DEFAULT_DATETIME_FORMAT);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int MinDay=cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),MinDay,0,0,0);

        try {
            return dateDF.parse(dateDF.format(cal.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据时间获取月的最后一天包含 23:59:59
     * @return
     */
    public static Date getMonthEndDay(Date date){
        SimpleDateFormat dateDF = new SimpleDateFormat(DateUtil.DEFAULT_DATETIME_FORMAT);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int MaxDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),MaxDay,23,59,59);

        try {
            return dateDF.parse(dateDF.format(cal.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取当前月的最后一天包含 23:59:59
     * @return
     */
    public static Date getMonthEndDay(){
        SimpleDateFormat dateDF = new SimpleDateFormat(DateUtil.DEFAULT_DATETIME_FORMAT);
        //获取当前时间
        Calendar cal = Calendar.getInstance();
        int MaxDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //按你的要求设置时间
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),MaxDay,23,59,59);

        try {
            return dateDF.parse(dateDF.format(cal.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *
     * Description: 获取日期中的小时（24小时制）
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取一个日期中的月份中的第几天
     * @param date
     * @return int
     */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Description: 获取一个日期中的月份
     * @param date 传入的日期
     * @return
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return (cal.get(Calendar.MONTH) + 1);
    }

    /**
     * Description: 获取一个日期中的年
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取今天的最早时间
     * @return Date
     */
    public static Date getNowDateStart() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取今天的最晚时间
     * @return Date
     */
    public static Date getNowDateEnd() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

}
