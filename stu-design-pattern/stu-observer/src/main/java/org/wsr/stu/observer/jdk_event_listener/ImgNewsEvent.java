package org.wsr.stu.observer.jdk_event_listener;

import java.util.EventObject;

/**
 * Created by wangshengren on 2017/6/21.
 */
public class ImgNewsEvent extends EventObject {

    private String msg = "我是图片新闻";

    public ImgNewsEvent(Object source) {
        super(source);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
