package com.heaven.spring.study.test;

import com.heaven.spring.study.Singleton1;

import java.util.concurrent.*;

/**
 * Created by heaven.zyc on 2016/7/5.
 */
public class SingletonDemo1 {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        SingCallable1 task1 = new SingCallable1();
        Future<Singleton1> future1 = es.submit(task1);

        SingCallable1 task2 = new SingCallable1();
        Future<Singleton1> future2 = es.submit(task2);
        try {
            Singleton1 s1 = future1.get();
            Singleton1 s2 = future2.get();
            System.out.println(s1 == s2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        es.shutdownNow();
    }

}

class SingCallable1 implements Callable<Singleton1> {

    @Override
    public Singleton1 call() throws Exception {
        return Singleton1.getInstance();
    }
}