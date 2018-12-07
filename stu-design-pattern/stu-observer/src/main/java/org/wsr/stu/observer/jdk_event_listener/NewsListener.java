package org.wsr.stu.observer.jdk_event_listener;

import java.util.EventListener;

/**
 * Created by wangshengren on 2017/6/21.
 */
public class NewsListener implements EventListener {
    public void onNewsPublish(ImgNewsEvent imgNewsEvent){
        System.out.println(imgNewsEvent.getMsg());
    }
}
