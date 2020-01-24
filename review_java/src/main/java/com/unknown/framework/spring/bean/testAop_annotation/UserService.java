package com.unknown.framework.spring.bean.testAop_annotation;

import org.springframework.stereotype.Component;

@Component
public class UserService {

    public void saveUser(){
        System.out.println("save user");
        int i = 1/0;
    };
}
