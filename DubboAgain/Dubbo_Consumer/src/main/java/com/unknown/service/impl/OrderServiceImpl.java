package com.unknown.service.impl;

import com.unknown.bean.User;
import com.unknown.service.OrderService;
import com.unknown.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserService userService;

    public void queryUserInfo() {
        List<User> all = userService.findAll();
        all.forEach(System.out::println);
    }

}
