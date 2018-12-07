package org.wsr.stu.clazz;

/**
 * Created by wangshengren on 2017/4/24.
 */
public class StuThread {
    public static void main(String[] args) {
        //1、线程的两种方式
        Thread t = new Thread(()->{
            System.out.println("实现runnable接口方式");
        });
        t.start();
        Thread t1 = new MyThread();

        t1.start();

        //Daemon示例
    }

    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("继承Thread类方式");
        }
    }
}
