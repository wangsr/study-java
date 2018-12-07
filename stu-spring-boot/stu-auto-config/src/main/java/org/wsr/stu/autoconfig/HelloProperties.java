package org.wsr.stu.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by wangshengren on 2017/6/21.
 */
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {
    private static final String MSG = "world";
    private String msg = MSG;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
