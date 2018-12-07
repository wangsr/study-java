package org.wsr.stu.observer.jdk_event_listener.spring.plain;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by wangshengren on 2017/8/18.
 */
@Component("ImgNewsAnnotationListener")
public class ImgNewsListener {
    @EventListener//根据event类型推测
    @Order(100)
    //@Async//支持异步
    public void onImgNewsEvent(ImgNewsEvent event) {
        System.out.println("基于Spring的注解");
        System.out.println(event.getMsg());
        System.out.println();
    }
}
