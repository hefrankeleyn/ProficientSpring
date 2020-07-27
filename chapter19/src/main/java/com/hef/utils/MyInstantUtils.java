package com.hef.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author lifei
 * @since 2020/7/27
 */
public class MyInstantUtils {

    public static final String FORMAT01 = "yyyy-MM-dd HH:mm:ss";

    /** 将Instant转成 字符串 */
    public static String instantToFormatStr(String timeFormat, Instant instant){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT01)
                .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }

    /** 将字符串转成 Instant */
    public static Instant strToInstant(String timeFormat, String instantStr){
        return LocalDateTime.parse(instantStr, DateTimeFormatter.ofPattern(timeFormat))
                .atZone(ZoneId.systemDefault()).toInstant();
    }
}
