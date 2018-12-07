package org.wsr.stu.proxy.jdk;

import org.wsr.stu.proxy.HelloWorld;
import org.wsr.stu.proxy.HelloWorldImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wangshengren on 2017/9/7.
 */
public class JdkProxy {
    public static void main(String[] args) {
        HelloWorld p = (HelloWorld) Proxy.newProxyInstance(
                HelloWorld.class.getClassLoader(),//加载要代理接口的classloader
                new Class[] {HelloWorld.class},//要代理的接口
                new JdkInvocationHandler(new HelloWorldImpl())//代理的真正执行逻辑
        );
        p.sayHello();
    }

    //这里定义要对target扩展的逻辑，代理类的执行逻辑在invoke中执行
    public static class JdkInvocationHandler implements InvocationHandler {

        private Object target;

        public JdkInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Proxy className is:" + proxy.getClass().getCanonicalName());
            System.out.println("before");
            method.invoke(target, args);
            System.out.println("end");
            return null;
        }
    }
}
