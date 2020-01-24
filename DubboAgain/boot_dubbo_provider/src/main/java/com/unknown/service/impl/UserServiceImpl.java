package com.unknown.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.unknown.bean.User;
import com.unknown.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findAll() {

        return this.jdbcTemplate.query("select * from user limit 10", new BeanPropertyRowMapper<User>(User.class));

    }
}
