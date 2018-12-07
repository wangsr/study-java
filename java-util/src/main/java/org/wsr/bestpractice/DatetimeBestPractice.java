package org.wsr.bestpractice;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 操作datetime的最佳实践
 * <pre>
 *     参考：
 *      http://www.importnew.com/14140.html
 *      http://www.importnew.com/14857.html
 * </pre>
 * @author wangsr
 * @date 2018/8/3
 */
public class DatetimeBestPractice {
    //jdk8

    /**
     * create
     */
    public void create() {
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
    }

    /**
     * parse,format
     */
    public void parseFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse("", formatter);
        String str = dateTime.format(formatter);
    }

    /**
     * transfer:LocalDateTime<->LocalDate<->LocalTime
     */
    public static void transfer() {
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDate localDate = dateTime.toLocalDate();
        LocalTime localTime = dateTime.toLocalTime();
        dateTime = localTime.atDate(LocalDate.now());
        dateTime = localDate.atTime(LocalTime.MIDNIGHT);
        dateTime = LocalDateTime.of(localDate, localTime);
    }

    /**
     * date<-->LocalDateTime
     * date<-->LocalDate
     * date<-->LocalTime
     */
    public static void transferOld() {
        //old->new
        Date date = new Date();
        //1、
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        dateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        //2、
        LocalDate localdate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        //3、
        LocalTime localTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

        //new->old
        //1、
        Instant instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
        date = Date.from(instant);
        //2、
        instant = localdate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        date = Date.from(instant);
        //3、(时间需要添加一个日期才能转换为date)
        instant = localTime.atDate(localdate).atZone(ZoneId.systemDefault()).toInstant();
        date = Date.from(instant);
    }

    /**
     * 毫秒
     */
    public static void milliseconds() {
        //old->long
        Date date = new Date();
        long m = date.getTime();
        //new->long
        LocalDateTime localDateTime = LocalDateTime.now();
        m = localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static void main(String[] args) {
        transfer();
    }

}
