package com.dipsy.laa.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期格式化工具
 *
 * @auther cuiqiongyu
 * @create 2018/5/14 22:29
 */
public class DateFormatUtil {

    private final static Logger logger = LoggerFactory.getLogger(DateFormatUtil.class);

    private static String datePattern = "yyyy-MM-dd HH:mm:ss";
    private static String datePatternV1 = "yyyy-MM-ddHH:mm:ss";

    private static ThreadLocal<Map<String, SimpleDateFormat>> dateFormatPool = new ThreadLocal<Map<String, SimpleDateFormat>>() {
        public Map<String, SimpleDateFormat> initialValue() {
            return new HashMap<>();
        }
    };

    public static String format(Date date) {
        SimpleDateFormat dateFormat = getDateFormat(datePattern);
        return dateFormat.format(date);
    }

    public static Date parse(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = getDateFormat(datePattern);
        return dateFormat.parse(dateStr);
    }

    public static String formatV1(Date date) {
        SimpleDateFormat dateFormat = getDateFormat(datePatternV1);
        return dateFormat.format(date);
    }

    public static Date parseV1(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = getDateFormat(datePatternV1);
        return dateFormat.parse(dateStr);
    }

    private static SimpleDateFormat getDateFormat(String pattern) {
        Map<String, SimpleDateFormat> dateFormatMap = dateFormatPool.get();
        SimpleDateFormat dateFormat = dateFormatMap.get(pattern);
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(pattern);
        }
        return dateFormat;
    }

}
