package com.unknown.framework.spring.bean.testAop;

public class UserService {

    public void saveUser(){
        System.out.println("save user");
        int i = 1/0;
    };
}
