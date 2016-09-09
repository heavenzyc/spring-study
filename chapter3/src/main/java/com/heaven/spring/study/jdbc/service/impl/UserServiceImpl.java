package com.heaven.spring.study.jdbc.service.impl;

import com.heaven.spring.study.jdbc.service.RoleService;
import com.heaven.spring.study.jdbc.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * Created by heaven.zyc on 2016/3/31.
 */
public class UserServiceImpl extends JdbcServiceImpl implements UserService {

    private RoleService roleService;

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public int saveUser(String name, int age) throws Exception {
        int i = getJdbcTemplate().update("INSERT INTO user (name,age) VALUES ("+name+",10)");
        System.out.println("事务隔离级别：" + getJdbcTemplate().getDataSource().getConnection().getTransactionIsolation());
        try {
            roleService.saveRole("'role1'", 2);
        } catch (RuntimeException e) {

        }
        if (i > 0) {
            System.out.println("save user success");
        }
        return i;
    }

    public int updateUser(String name, int age, Integer id) throws Exception {
        int i = getJdbcTemplate().update("UPDATE user SET name=" + name + ", age=" + age + " WHERE id=" + id);
        if (i > 0) {
            System.out.println("update user success");
        }
        return i;
    }
}
