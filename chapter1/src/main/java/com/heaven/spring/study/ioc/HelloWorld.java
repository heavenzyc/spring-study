package com.heaven.spring.study.ioc;

import java.util.List;
import java.util.Map;

/**
 * Created by heaven.zyc on 2016/3/13.
 */
public class HelloWorld {

    private String hello;

    private List<String> listValues;

    private Map<Integer, String> mapValues;

   /* private Car car;

    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }*/

    public List<String> getListValues() {
        return listValues;
    }

    public void setListValues(List<String> listValues) {
        this.listValues = listValues;
    }

    public HelloWorld(String hello) {
        this.hello = hello;
    }

    public Map<Integer, String> getMapValues() {
        return mapValues;
    }

    public void setMapValues(Map<Integer, String> mapValues) {
        this.mapValues = mapValues;
    }

    public void sayHello() {
        System.out.println(hello);
    }
}
