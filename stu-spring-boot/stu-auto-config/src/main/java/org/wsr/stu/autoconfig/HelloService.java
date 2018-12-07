package org.wsr.stu.autoconfig;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by wangshengren on 2017/6/21.
 */
@Service
public class HelloService {
    private String msg;

    public String sayHello() {
        return "hello " + msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
