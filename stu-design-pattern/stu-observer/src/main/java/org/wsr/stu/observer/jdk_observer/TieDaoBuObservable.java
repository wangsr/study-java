package org.wsr.stu.observer.jdk_observer;

import java.util.Date;
import java.util.Observable;

/**
 * Created by wangshengren on 2017/6/21.
 */
public class TieDaoBuObservable extends Observable {// 表示铁道部可以被观察
    private String message;// 官方消息

    public TieDaoBuObservable(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        // 每一次修改的时候都应该引起观察者的注意
        System.out.println("****************************************");
        System.out.println(new Date() + " 官方发布消息为：" + message);
        super.setChanged();    // 设置变化点
        super.notifyObservers(message);// 通知所有观察者
        this.message = message;
    }
}
