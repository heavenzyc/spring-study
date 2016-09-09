package com.heaven.spring.study;

/**
 * Created by heaven.zyc on 2016/7/5.
 * 懒汉式 非线程安全
 */
public class Singleton1 {

    private Singleton1() {}
    private static Singleton1 instance;

    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}
