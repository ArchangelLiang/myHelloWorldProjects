package com.unknown.controller;

import com.unknown.bean.User;
import com.unknown.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/findAll")
    @ResponseBody
    public List<User> findAll(){
        return this.orderService.queryUserInfo();
    }

}
