package com.heaven.spring.study;

/**
 * Created by heaven.zyc on 2016/7/5.
 * 饿汉式
 */
public class Singleton3 {

    private Singleton3(){}

    private static Singleton3 instance = new Singleton3();

    private static Singleton3 getInstance() {
        return instance;
    }
}
