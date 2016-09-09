package com.heaven.spring.study.aop;

/**
 * Created by heaven.zyc on 2016/3/22.
 */
public class HelloWorldService implements IHelloWorldService {
    public void sayHello() {
        System.out.println("======== say hello world");
    }

    public void sayHelloParam(String params, Integer age) {
        System.out.println("==== say hello world:params " + params + " age:" + age);
    }

    public boolean sayAfterReturning() {
        System.out.println("=== sayAfterReturning ==");
        return false;
    }

    public void sayAfterException() {
        System.out.println("=== sayAfterException ==");
        throw new RuntimeException("ddd");
    }

    public void sayAroundHello(String param) {
        System.out.println("== sayAroundHello param:" + param);
    }
}
