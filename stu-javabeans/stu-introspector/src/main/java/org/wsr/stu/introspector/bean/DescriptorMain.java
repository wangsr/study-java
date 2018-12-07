package org.wsr.stu.introspector.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * descriptor展示
 * Created by wangshengren on 16/10/12.
 */
public class DescriptorMain {
    public static void main(String[] args) {
        try {
            BeanInfo info = Introspector.getBeanInfo(UserInfo.class);

            info.getBeanDescriptor();
            info.getAdditionalBeanInfo();

            info.getPropertyDescriptors();
            info.getDefaultPropertyIndex();
            info.getEventSetDescriptors();
            info.getDefaultEventIndex();
            info.getMethodDescriptors();

        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }
}
