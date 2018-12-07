package org.wsr.stu.introspector.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * BeanInfo的查找规则
 * <pre>
 *     Thus for a class such as "sun.xyz.OurButton" we would first look for a BeanInfo class called "sun.xyz.OurButtonBeanInfo" and if that failed
 *     we'd look in each package in the BeanInfo search path for an OurButtonBeanInfo class. With the default search path, this would mean looking
 *     for "sun.beans.infos.OurButtonBeanInfo".
 * </pre>
 * <pre>
 *     例如,分析这个类:sun.xyz.OurButton
 *     1、查找:sun.xyz.OurButtonBeanInfo
 *     2、sun.beans.infos.OurButtonBeanInfo
 *     3、反射
 * </pre>
 */
public class BeanInfoMain {
    public static void main(String[] args) {

        try {
            // 获取bean以及父类的beaninfo
            BeanInfo info = Introspector.getBeanInfo(UserInfo.class);
            PropertyDescriptor[] arr = info.getPropertyDescriptors();
            for (PropertyDescriptor item : arr) {
                System.out.println(item);
            }
            System.out.println("=========");

            // 获取bean到Object的beaninfo(集成体系),不包括object
            info = Introspector.getBeanInfo(UserInfo.class, Object.class);
            arr = info.getPropertyDescriptors();
            for (PropertyDescriptor item : arr) {
                System.out.println(item);
            }
            System.out.println("=========");

            //USE_ALL_BEANINFO(显示的beaninfo优先,然后反射)
            //IGNORE_IMMEDIATE_BEANINFO(忽略当前bean的直接beaninfo信息)
            //IGNORE_ALL_BEANINFO(只通过反射获取,忽略所有显示的BeanInfo)
            info = Introspector.getBeanInfo(UserInfo.class, Introspector.IGNORE_ALL_BEANINFO);
            arr = info.getPropertyDescriptors();
            for (PropertyDescriptor item : arr) {
                System.out.println(item);
            }

        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }
}
