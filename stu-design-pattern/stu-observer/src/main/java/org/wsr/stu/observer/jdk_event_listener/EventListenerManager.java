package org.wsr.stu.observer.jdk_event_listener;

/**
 * Created by wangshengren on 2017/6/21.
 */
public class EventListenerManager {
    public static void main(String[] args) {
        //使用JDK的EventObject，需要自己负责事件的注册和分发，
        //而使用Observer，则Observable提供了事件的注册和分发，
        //Spring的事件是基于JDK的EventObject、EventListener实现的，Spring提供了事件注册和分发的能力
    }
}
