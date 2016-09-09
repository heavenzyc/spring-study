package com.heaven.spring.study.aop;

/**
 * Created by heaven.zyc on 2016/3/22.
 */
public interface IHelloWorldService {

    void sayHello();

    void sayHelloParam(String params, Integer age);

    boolean sayAfterReturning();

    void sayAfterException();

    void sayAroundHello(String param);

}
