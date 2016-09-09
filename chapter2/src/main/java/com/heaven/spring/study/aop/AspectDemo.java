package com.heaven.spring.study.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by heaven.zyc on 2016/3/23.
 */
public class AspectDemo {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:helloworld-aspect.xml");
        IHelloWorldService helloWorldService = context.getBean("helloWorldService", IHelloWorldService.class);
//        helloWorldService.sayHelloParam("aspect", 20);
//        helloWorldService.sayHello();
//        helloWorldService.sayAfterReturning();
//        helloWorldService.sayAfterException();
//        helloWorldService.sayAroundHello("ao hao");

        IIntroduceService introduceService = context.getBean("helloWorldService", IIntroduceService.class);
        introduceService.introduce();
    }
}
