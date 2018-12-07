package org.wsr.stu.observer.jdk_event_listener.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by wangshengren on 2017/8/18.
 */
@Component("ImgNewsInterfaceListener")
public class ImgNewsListener implements ApplicationListener<ImgNewsEvent> {
    @Override
    public void onApplicationEvent(ImgNewsEvent event) {
        System.out.println("基于Spring的接口");
        System.out.println(event.getMsg());
        System.out.println();
    }
}
