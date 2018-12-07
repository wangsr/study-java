package org.wsr.util;

import com.google.common.base.Preconditions;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author wangsr
 * @date 2018/12/7
 * @description jdk8 时间处理
 */
public class DatetimeTools {
    public static DateTimeFormatter YMDHMS = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static DateTimeFormatter YMDHM = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    public static DateTimeFormatter YMD = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static DateTimeFormatter Y_M_D_H_M_S = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter Y_M_D_H_M = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static DateTimeFormatter Y_M_D = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //====================date<-->LocalDateTime====================
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Preconditions.checkNotNull(date);
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDate dateToLocalDate(Date date) {
        Preconditions.checkNotNull(date);
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalTime dateToLocalTime(Date date) {
        Preconditions.checkNotNull(date);
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        Preconditions.checkNotNull(localDateTime);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date localDateToDate(LocalDate localDate) {
        Preconditions.checkNotNull(localDate);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    //====================date<-->LocalDateTime====================

    //====================millis<-->LocalDateTime====================
    public static long localDateTimeToMillis(LocalDateTime localDateTime) {
        Preconditions.checkNotNull(localDateTime);
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static long localDateToMillis(LocalDate localDate) {
        Preconditions.checkNotNull(localDate);
        return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static LocalDateTime millisToLocalDateTime(long millis) {
        return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDate millisToLocalDate(long millis) {
        return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalTime millisToLocalTime(long millis) {
        return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalTime();
    }
    //====================millis<-->LocalDateTime====================

    public static void main(String[] args) {
        long millis = localDateTimeToMillis(LocalDateTime.now());
        System.out.println(millis);
        System.out.println(millisToLocalDateTime(millis));
        System.out.println(millisToLocalDate(millis));
        System.out.println(millisToLocalTime(millis));
        System.out.println("=========================");
        millis = localDateToMillis(LocalDate.now());
        System.out.println(millis);
        System.out.println(millisToLocalDateTime(millis));
        System.out.println(millisToLocalDate(millis));
        System.out.println(millisToLocalTime(millis));
    }
}
