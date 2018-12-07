package org.wsr.stu.clazz;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Arrays;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by wangshengren on 2017/4/27.
 */
public class StuClass {
    public static void main(String[] args) {
        printStars("父子class的转换");
        //父子class的转换
        System.out.println(Child.class.asSubclass(Parent.class));

        printStars("父子object的转换");
        //父子object的转换
        //不能将一个确定的父类转换为子类，否则报ClassCastException
        //Parent p = new Parent();
        //Child c = Child.class.cast(p);//报ClassCastException
        Parent p = new Child();
        Child c = Child.class.cast(p);
        //等价下面的操作下面
        //Child c = (Child) p;
        System.out.println(c);

        printStars("getName");
        //getName
        Class<Integer> tmp = Integer.TYPE;
        Class<Integer> t = int.class;
        System.out.println(tmp);
        System.out.println(t);
        System.out.println(t == tmp);
        System.out.println(int.class.getCanonicalName());
        System.out.println(int.class.getName());
        System.out.println(int.class.getTypeName());
        System.out.println(int.class.getSimpleName());
        int[][][][][][][] arr = new int[3][4][5][6][7][8][9];
        System.out.println(arr.getClass().getCanonicalName());
        System.out.println(arr.getClass().getName());
        System.out.println(arr.getClass().getTypeName());
        System.out.println(arr.getClass().getSimpleName());

        printStars("annotation");
        //annotation
        System.out.println(Arrays.toString(Child.class.getAnnotations()));
        System.out.println(Arrays.toString(Child.class.getDeclaredAnnotations()));
        System.out.println(Child.class.getAnnotation(MyAnno02.class));

        printStars("getClasses");
        //获取class的组成信息
        //这几个方法相似:getAnnotations,getClasses,getConstructors,getMethods,getFields
        //获取class的public的成员，包括继承的public的成员
        System.out.println(Arrays.toString(StuClass.class.getClasses()));
        //获取class声明的任意权限的成员（public、protected、packaged、private），但是不包括继承的成员
        System.out.println(Arrays.toString(StuClass.class.getDeclaredClasses()));

        printStars("classLoader");
        //generic
        System.out.println(Child.class.getClassLoader());
        //有些实现可能使用 null 来表示引导类加载器
        System.out.println(String.class.getClassLoader());
        //如果此对象表示一个基本类型或 void，则返回 null。
        System.out.println(Void.class.getClassLoader());
        System.out.println(void.class.getClassLoader());
        System.out.println(Integer.class.getClassLoader());
        System.out.println(int.class.getClassLoader());

        printStars("getComponentType");
        //getComponentType
        //返回表示数组组件类型的 Class。如果此类不表示数组类，则此方法返回 null
        System.out.println(new boolean[1][1].getClass().getComponentType());
        System.out.println(Child.class.getComponentType());

        //getEnumConstants()
        //如果此 Class 对象不表示枚举类型，则返回枚举类的元素或 null
        printStars("getEnumConstants");
        System.out.println(Arrays.asList(MyEnum.class.getEnumConstants()));
        System.out.println(Child.class.getEnumConstants());

        //getDeclaringClass()
        //如果此 Class 对象所表示的类或接口是另一个类的成员，则返回的 Class 对象表示该对象的声明类。
        printStars("getDeclaringClass");
        System.out.println(Child.class.getDeclaringClass());//StuClass
        System.out.println(Object.class.getDeclaringClass());//null

        //enclosing(闭包)
        //getEnclosingClass() ,getEnclosingConstructor() ,getEnclosingMethod()

        //getGenericInterfaces() ,getGenericSuperclass() ,getInterfaces()

    }

    public enum MyEnum {
        A,
        B,
        C
    }

    static void printClassName(Object obj) {
        System.out.println("The class of " + obj + " is " + obj.getClass().getName());
    }

    static void printStars(String param) {
        System.out.println("*****************【" + param + "】**********************");
    }

    @MyAnno01
    public static class Parent {
    }


    @MyAnno02
    @MyAnno03
    private static class Child extends Parent {
    }


    @Inherited
    @Target({TYPE, FIELD, METHOD})
    @Retention(RUNTIME)
    public @interface MyAnno01 {
    }


    @MyAnno01
    @Target({TYPE, FIELD, METHOD})
    @Retention(RUNTIME)
    @interface MyAnno02 {
    }


    @MyAnno01
    @Target({TYPE, FIELD, METHOD})
    @Retention(RUNTIME)
    @interface MyAnno03 {
    }

}

// Java有5中class
// There are five kinds of classes (or interfaces):
// a) Top level classes
// b) Nested classes (static member classes)
// c) Inner classes (non-static member classes)
// d) Local classes (named classes declared within a method)
// e) Anonymous classes


class TopClazz {
    static class NestedClazz {
    }


    class InnerClazz {
    }

    void method() {
        class LocalClazz {
        }
    }

    Runnable anonymousClazz = new Runnable() {
        @Override
        public void run() {
            System.out.println("Anonymous classes");
        }
    };
}
