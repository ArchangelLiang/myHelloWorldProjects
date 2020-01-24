package com.unknown.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class MainApplication {

    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer.xml");
        OrderService orderService = applicationContext.getBean(OrderService.class);
        orderService.queryUserInfo();

        System.in.read();
    }

}
