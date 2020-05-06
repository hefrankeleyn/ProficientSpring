package com.hef.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Date 2020/4/21
 * @Author lifei
 */
public class MyInstanceUtil {

    private final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 转化成Instant
     * @param date
     * @return
     */
    public static Instant changeInstantToDate(Date date){
        if (date==null){
            return null;
        }else {
            return date.toInstant();
        }
    }

    /**
     * 转化成时间
     * @param instant
     * @return
     */
    public static String instantToDefaultStr(Instant instant){
        if (instant==null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_FORMAT).withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }
}
