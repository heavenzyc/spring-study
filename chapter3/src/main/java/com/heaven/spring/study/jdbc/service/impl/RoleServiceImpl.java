package com.heaven.spring.study.jdbc.service.impl;

import com.heaven.spring.study.jdbc.service.RoleService;

/**
 * Created by heaven.zyc on 2016/3/31.
 */
public class RoleServiceImpl extends JdbcServiceImpl implements RoleService {
    public int saveRole(String name, Integer status) {
        int i = getJdbcTemplate().update("INSERT INTO role (role_name,status) VALUES ("+name+",1)");
        if (i > 0) {
            System.out.println("save role success");
        }
//        int j = 1/0;
        return i;
    }

    public int updateRole(String name, Integer status, Integer id) {
        int i = getJdbcTemplate().update("UPDATE role SET role_name=" + name + ", age=" + status + " WHERE id=" + id);
        if (i > 0) {
            System.out.println("update role success");
        }
        return i;
    }
}
