package org.wsr.stu.observer.jdk_event_listener.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangshengren on 2017/8/18.
 */
public class EventDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("org.wsr.stu.observer.jdk_event_listener");
        //Spring提供了事件的注册和分发
        ctx.publishEvent(new ImgNewsEvent(ctx));//基于spring接口的事件
        ctx.publishEvent(new org.wsr.stu.observer.jdk_event_listener.spring.plain.ImgNewsEvent());//基于注解的事件
        //
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
