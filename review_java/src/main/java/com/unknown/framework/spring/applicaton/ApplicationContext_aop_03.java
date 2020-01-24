package com.unknown.framework.spring.applicaton;

import com.unknown.framework.spring.bean.testAop_annotation.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContext_aop_03 {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring_config/applicationContext_aop.xml");
        UserService userService = (UserService)context.getBean("userService");

        userService.saveUser();
    }


}
