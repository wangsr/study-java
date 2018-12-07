package org.wsr.stu.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by wangshengren on 2017/9/6.
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class Starter {
    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(Starter.class, args);
        app.getBean(AService.class).aFun();
    }
}
