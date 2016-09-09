package com.heaven.spring.study.jdbc.service;

/**
 * Created by heaven.zyc on 2016/3/31.
 */
public interface RoleService extends JdbcService{

    int saveRole(String name, Integer status) throws RuntimeException;

    int updateRole(String name, Integer status, Integer id);
}
