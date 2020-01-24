package com.unknown.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.unknown.bean.User;
import com.unknown.service.OrderService;
import com.unknown.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Reference
    private UserService userService;

    public List<User> queryUserInfo() {
        List<User> all = userService.findAll();
        all.forEach(System.out::println);
        return all;
    }

}
