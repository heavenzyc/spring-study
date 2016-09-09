package com.heaven.spring.study.jdbc.service;

/**
 * Created by heaven.zyc on 2016/3/31.
 */
public interface UserService extends JdbcService{

    int saveUser(String name, int age) throws Exception;

    int updateUser(String name, int age, Integer id) throws Exception;
}
