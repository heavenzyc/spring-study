package com.heaven.spring.study.ioc;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * Created by heaven.zyc on 2016/3/13.
 */
public class Demo {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:helloworld.xml");
        /*HelloWorld helloWorld = context.getBean("helloWorld", HelloWorld.class);
        helloWorld.sayHello();
        helloWorld.getCar().action();*/

        BeanFactory factory = new ClassPathXmlApplicationContext("classpath:helloworld.xml");

        HelloWorld helloWorld = factory.getBean("helloWorld", HelloWorld.class);
        helloWorld.sayHello();
        List<String> listValue = helloWorld.getListValues();
        Map<Integer, String> mapValue = helloWorld.getMapValues();
    }
}
