package org.wsr.stu.singleton;

/**
 * Created by wangshengren on 2017/7/14.
 */
public class SingletonMain {

    private SingletonMain() {
    }

    //1
    private static SingletonMain instance = new SingletonMain();
    public static SingletonMain getInstance() {
        return instance;
    }

}
