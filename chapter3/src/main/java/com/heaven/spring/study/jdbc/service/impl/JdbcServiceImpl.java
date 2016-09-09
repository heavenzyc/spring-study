package com.heaven.spring.study.jdbc.service.impl;

import com.heaven.spring.study.jdbc.service.JdbcService;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by heaven.zyc on 2016/3/28.
 */
public class JdbcServiceImpl implements JdbcService{

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
