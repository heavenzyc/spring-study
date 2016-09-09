package com.heaven.es.study;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by heaven.zyc on 2016/8/14.
 */
public class StoreService {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getStoreList() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM store");
        return list;
    }
}
