package org.wsr.stu.joda;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;
import java.util.Locale;

/**
 * 日期时间的需求
 * 1、创建日期时间
 * 2、日期date、时间time、日期时间datetime
 * 3、日期计算比较
 * 4、日期解析、格式化
 * 5、日期计算
 * Created by wangshengren on 16/8/22.
 */
public class JodaMain {
    public static void main(String[] args) {
        //1、joda常见日期时间的方法(系统时间、多字段、毫秒数、java.util.date或datetime)
        DateTime d1 = DateTime.now();
        DateTime d2 = new DateTime(2010, 1, 1, 0, 0, 0);
        DateTime d21 = new DateTime(2010, 1, 1, 1, 1, 1, 1);
        DateTime d3 = new DateTime(new Date().getTime());
        DateTime d4 = new DateTime(new Date());
        DateTime d41 = new DateTime(d4);
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d21);
        System.out.println(d3);
        System.out.println(d4);
        System.out.println(d41);
        //2、date,time
        LocalDate d5 = LocalDate.now();
        LocalDate d51 = d3.toLocalDate();
        LocalTime d6 = LocalTime.now();
        LocalTime d61 = d3.toLocalTime();
        LocalDateTime d7 = LocalDateTime.now();
        LocalDateTime d71 = d3.toLocalDateTime();
        // 抹除毫秒,得到:时分秒
        LocalDateTime d72 = d3.toLocalDateTime().millisOfSecond().setCopy(0);
        LocalDateTime d73 = d3.toLocalDateTime().millisOfSecond().withMinimumValue();
        LocalDateTime d74 = d3.toLocalDateTime().millisOfSecond().withMaximumValue();
        System.out.println(d5);
        System.out.println(d51);
        System.out.println(d6);
        System.out.println(d61);
        System.out.println(d7);
        System.out.println(d71);
        System.out.println(d72);
        System.out.println(d73);
        System.out.println(d74);
        System.out.println(d74.toDate());
        //3、比较
        d1.isBefore(d2);
        d1.isBeforeNow();
        d1.isAfter(d2);
        d1.isAfterNow();
        d1.isEqual(d2);
        d1.isEqualNow();
        //4、解析、格式化
        DateTime d8 = DateTime.parse("2010-01-01T12:12:12");
        DateTime d81 = new DateTime("2010-01-01T12:12:12");
        DateTime d82 = DateTime.parse("12-31-2010", DateTimeFormat.forPattern("MM-dd-yyyy"));
        System.out.println(d8);
        System.out.println(d81);
        System.out.println(d82);
        System.out.println(d82.dayOfWeek().getAsText(Locale.CHINA));
        System.out.println(d82.toDate().toLocaleString());
        System.out.println(d8.toString("yyyy-MM-dd hh:mm:ss"));
        System.out.println(d8.toString("yyyy-MM-dd"));
        //5、计算
        d1.plus(1000);

        // 时间跨度
        Duration duration = new Duration(10000);
        System.out.println(duration);
        Period period = new Period(1000);
        System.out.println(period);
        Interval interval = new Interval(1000,3000);
        System.out.println(interval);

        System.out.println(DateTime.now().year().get());
        System.out.println(DateTime.now().yearOfCentury().get());
        System.out.println(DateTime.now().yearOfEra().get());

    }
}
