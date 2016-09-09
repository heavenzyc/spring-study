package com.heaven.spring.study.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by heaven.zyc on 2016/3/22.
 */
public class SchemaDemo {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:helloworld-schema.xml");
        IHelloWorldService helloWorldService = context.getBean("helloWorldService", IHelloWorldService.class);
//        helloWorldService.sayHello();
//        helloWorldService.sayHelloParam("aop", 28);
//        helloWorldService.sayAfterReturning();
//        helloWorldService.sayAfterException();
//        helloWorldService.sayAroundHello("haha");
        IIntroduceService introduceService = context.getBean("helloWorldService", IIntroduceService.class);
        introduceService.introduce();
    }
}
