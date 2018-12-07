package org.wsr.stu.observer.jdk_event_listener.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * 基于Spring的ApplicationEvent
 * Created by wangshengren on 2017/8/18.
 */
public class ImgNewsEvent extends ApplicationEvent {

    private String msg = "我是图片新闻";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ImgNewsEvent(Object source) {
        super(source);
    }
}
