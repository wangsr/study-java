package org.wsr.stu.clazz;

/**
 * Created by wangshengren on 2017/4/27.
 */
public class SutThreadLocal {
    private final static ThreadLocal<Integer> AGE = new ThreadLocal<>();
    private final static ThreadLocal<String> CNT = new ThreadLocal<>();

    public static void main(String[] args) {
        CNT.set("main");
        Thread t0 = new Thread(() -> {
            //CNT.set("t0");
            System.out.println(CNT.get());
            System.out.println(AGE.get());
        });
        Thread t1 = new Thread(() -> {
            CNT.set("t1");
            AGE.set(1);
            System.out.println(CNT.get());
            System.out.println(AGE.get());
        });
        Thread t2 = new Thread(() -> {
            CNT.set("t2");
            AGE.set(2);
            System.out.println(CNT.get());
            System.out.println(AGE.get());
        });
        Thread t3 = new Thread(() -> {
            CNT.set("t3");
            AGE.set(3);
            System.out.println(CNT.get());
            System.out.println(AGE.get());
        });
        t0.start();
        t1.start();
        t2.start();
        t3.start();


    }
}
