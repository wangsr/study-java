package org.wsr.stu.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.wsr.stu.proxy.HelloWorld;
import org.wsr.stu.proxy.HelloWorldImpl;

import java.lang.reflect.Method;

/**
 * Created by wangshengren on 2017/9/7.
 */
public class CglibProxy {
    public static void main(String[] args) {

        HelloWorld h = (HelloWorld) new CglibHandler().getInstance(new HelloWorldImpl());
        h.sayHello();
    }

    public static class CglibHandler implements MethodInterceptor {

        private Object target;

        //相当于JDK动态代理中的绑定
        public Object getInstance(Object target) {
            this.target = target;  //给业务对象赋值
            Enhancer enhancer = new Enhancer(); //创建加强器，用来创建动态代理类
            enhancer.setSuperclass(this.target.getClass());  //为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
            //设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
            enhancer.setCallback(this);
            // 创建动态代理类对象并返回
            return enhancer.create();
        }

        //这里定义要对target扩展的逻辑，代理类的执行逻辑在intercept中执行
        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            System.out.println("预处理——————");
            System.out.println(method);
            methodProxy.invokeSuper(o, args); //调用业务类（父类中）的方法
            System.out.println("调用后操作——————");
            return null;
        }
    }
}
