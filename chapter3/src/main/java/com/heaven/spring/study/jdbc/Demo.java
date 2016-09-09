package com.heaven.spring.study.jdbc;

import com.heaven.spring.study.jdbc.service.UserService;
import com.heaven.spring.study.jdbc.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by heaven.zyc on 2016/3/28.
 */
public class Demo {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-jdbc.xml");
        UserService userService = context.getBean("userService", UserService.class);
        try {
            System.out.println(userService.saveUser("'zyc'", 10));
//            userService.updateUser("'zyc1'", 100, 12);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
