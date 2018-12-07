package org.wsr.stu.observer.jdk_event_listener.spring.plain;

/**
 * 不需要Spring的任何接口
 * Created by wangshengren on 2017/8/18.
 */
public class ImgNewsEvent {
    private String msg = "我是图片新闻";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
