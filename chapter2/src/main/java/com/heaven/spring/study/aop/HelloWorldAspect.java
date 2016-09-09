package com.heaven.spring.study.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by heaven.zyc on 2016/3/22.
 */
public class HelloWorldAspect {

    public void beforeAdvice() {
        System.out.println("====before advice======");
    }

    public void afterFinallyAdvice(){
        System.out.println("====after finally advice=====");
    }

    public void beforeAdvice(String params, Integer age) {
        System.out.println("====before advice params:"+ params + " age:" +age);
    }

    public void afterReturning(Object reVal){
        System.out.println("== afterReturning advice reVal:" + reVal);
    }

    public void afterException(Exception e) {
        System.out.println("== afterException advice :" + e);
    }

    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("== aroundAdvice before==");
        Object reVal = proceedingJoinPoint.proceed();
//        Object reVal = proceedingJoinPoint.proceed(new Object[]{"replace haha"});
        System.out.println("== aroundAdvice after ==");
        return reVal;
    }
}
