package org.wsr.stu.joda;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by wangshengren on 16/8/22.
 */
public class BestPracticeJodaMain {
    public static void main(String[] args) {
        //1、创建now
        DateTime now = DateTime.now();
        //2、构造函数(ReadableInstant, String, Calendar and Date,field)
        DateTime datetime = new DateTime();
        datetime = new DateTime(now);
        datetime = new DateTime("2010-01-01");
        //date'T'time(@see ISODateTimeFormat.dateTimeParser())
        datetime = new DateTime("2010-01-01T01:01:01");
        datetime = new DateTime(Calendar.getInstance());
        datetime = new DateTime(new Date());
        datetime = new DateTime(2010, 1, 1, 1, 1, 1);
        System.out.println(datetime);

        //2、localdate、localtime、localdatetime(没有时区的概念)
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        //3、parse,format
        //date'T'time
        DateTime.parse("2010-01-01T01:01:01");
        DateTime.parse("2010-01-01 01:01:01", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        //Output the date time in ISO8601 format (yyyy-MM-ddTHH:mm:ss.SSSZZ)
        now.toString();
        now.toString("yyyy-MM-dd HH:mm:ss");

        //4、计算
        now.isEqualNow();
        now.isBeforeNow();
        now.isAfterNow();
        now.plus(0);

        //5、property操作(修改dateTime中的各个字段)
        //        Each individual field can be queried in two ways:
        //              getHourOfDay()
        //              hourOfDay().get()
        //        The second technique also provides access to other useful methods on the field:
        //              numeric value
        //              text value
        //              short text value
        //              maximum/minimum values
        //              add/subtract
        //              set
        //              rounding
        now.year().setCopy("2010");//包now的年的字段设置为2010

        // 6、时间跨度
        //相隔1min
        Duration duration = new Duration(1000*60);
        System.out.println(duration);
        //相隔2mon2day
        Period period = Period.months(2).withDays(2);
        System.out.println(period);
        //[from, to)
        DateTime from = DateTime.now();
        DateTime to = from.plusMinutes(1);
        Interval interval = new Interval(from,to);
        System.out.println(interval);

    }
}

