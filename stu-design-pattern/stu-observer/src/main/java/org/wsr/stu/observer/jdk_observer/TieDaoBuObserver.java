package org.wsr.stu.observer.jdk_observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by wangshengren on 2017/6/21.
 */
public class TieDaoBuObserver implements Observer {
    private String name;

    public TieDaoBuObserver(String name) {   // 设置每一个观察者的名字
        this.name = name;
    }

    public void update(Observable o, Object arg) {
        System.out.print(this.name + " 官方消息更改为：");
        System.out.println(arg.toString());
    }
}
