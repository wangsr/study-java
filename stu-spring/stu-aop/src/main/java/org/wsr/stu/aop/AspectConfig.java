package org.wsr.stu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by wangshengren on 2017/9/6.
 */
@Component
@Aspect
public class AspectConfig {

    @Before("execution(* org.wsr.stu..*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("before");
    }

    @After("execution(* org.wsr.stu..*(..))")
    public void after(JoinPoint joinPoint) {
        System.out.println("after");
    }

    @Around("execution(* org.wsr.stu..*(..))")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before");
        pjp.proceed();
        System.out.println("around after");
    }
}
