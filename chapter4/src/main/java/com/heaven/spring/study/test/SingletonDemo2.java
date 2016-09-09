package com.heaven.spring.study.test;

import com.heaven.spring.study.Singleton2;

import java.util.concurrent.*;

/**
 * Created by heaven.zyc on 2016/7/5.
 */
public class SingletonDemo2 {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        SingCallable2 task1 = new SingCallable2();
        Future<Singleton2> future1 = es.submit(task1);

        SingCallable2 task2 = new SingCallable2();
        Future<Singleton2> future2 = es.submit(task2);
        try {
            Singleton2 s1 = future1.get();
            Singleton2 s2 = future2.get();
            System.out.println(s1 == s2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        es.shutdownNow();
    }

}

class SingCallable2 implements Callable<Singleton2> {

    @Override
    public Singleton2 call() throws Exception {
        return Singleton2.getInstance();
    }
}