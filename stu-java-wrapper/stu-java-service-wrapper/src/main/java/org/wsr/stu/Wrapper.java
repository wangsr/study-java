package org.wsr.stu;

import org.joda.time.DateTime;

import java.util.Arrays;

/**
 * Created by wangshengren on 16/9/11.
 */
public class Wrapper {
    public static void main(String[] args) {
        System.out.println("********************");
        System.out.println(DateTime.now().toString("yyyy-MM-dd"));
        System.out.println(Arrays.toString(args));
        System.out.println("********************");
    }
}
