package org.wsr.stu.clazz;

import java.util.Arrays;

/**
 * Created by wangshengren on 2017/4/21.
 */
public class StuEnum {
    public static void main(String[] args) {
        System.out.println(MyEnum.A.getDeclaringClass());
        System.out.println(MyEnum.A.getClass());
        System.out.println(MyEnum.A.getDeclaringClass() == MyEnum.B.getClass());
        System.out.println(Arrays.toString(MyEnum.A.getDeclaringClass().getEnumConstants()));
        System.out.println(Arrays.toString(MyEnum.values()));
        System.out.println(Enum.valueOf(MyEnum.class, "A"));
        System.out.println(Enum.valueOf(MyEnum.class, "D"));//IllegalArgumentException
    }

    //Enum是java中，所有自定义枚举的基类
    //java compiler编译后，将MyEnum生成为继承了Enum的final类，添加了两个静态方法
    public enum MyEnum{
        A,B
    }
//======MyEnum编译后生成的class文件如下
//    Compiled from "StuEnum.java"
//    public final class org.wsr.stu.clazz.StuEnum$MyEnum extends java.lang.Enum<org.wsr.stu.clazz.StuEnum$MyEnum> {
//        public static final org.wsr.stu.clazz.StuEnum$MyEnum A;
//        public static final org.wsr.stu.clazz.StuEnum$MyEnum B;
//        public static org.wsr.stu.clazz.StuEnum$MyEnum[] values();
//        public static org.wsr.stu.clazz.StuEnum$MyEnum valueOf(java.lang.String);
//        static {};
//    }

}
