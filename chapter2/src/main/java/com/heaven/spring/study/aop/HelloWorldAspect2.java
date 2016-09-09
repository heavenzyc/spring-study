package com.heaven.spring.study.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by heaven.zyc on 2016/3/23.
 */
@Aspect
public class HelloWorldAspect2 {

    @DeclareParents(value = "com.heaven..*.IHelloWorldService+", defaultImpl = com.heaven.spring.study.aop.IntroduceService.class)
    private IIntroduceService introduceService;

    @Pointcut(value = "execution(* com.heaven..*.sayHelloParam(..)) && args(param, age)", argNames = "param, age")
    public void beforePointcut(String param, Integer age) {}

    @Before(value = "beforePointcut(param, age)", argNames = "param, age")
    public void beforeAdvice(String param, Integer age) {
        System.out.println("==== before advice param:" + param + "  age:" +age);
    }

    @After(value = "execution(* com.heaven..*.sayHello(..))" )
    public void afterAdvice() {
        System.out.println("=== after advice ==");
    }

    @AfterReturning(value = "execution(* com.heaven..*.sayAfterReturning(..))",
            argNames = "reVal", returning = "reVal")
    public void afterReturning(Object reVal){
        System.out.println("== after return advice reVal:" + reVal);
    }

    @AfterThrowing(value = "execution(* com.heaven..*.sayAfterException(..))",
            argNames = "e", throwing = "e")
    public void afterException(Exception e) {
        System.out.println("== afterException advice e:" + e);
    }

    @Around(value = "execution(* com.heaven..*.sayAroundHello(..))", argNames = "point")
    public void aroundAdvice(ProceedingJoinPoint point) throws Throwable{
        System.out.println("== around advice before");
        point.proceed(new Object[]{"replace"});
        System.out.println("== around advice after");
    }

}
