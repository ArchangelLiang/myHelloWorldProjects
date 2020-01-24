package com.unknown.framework.spring.applicaton;

import com.unknown.framework.spring.bean.testAop_annotation.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContext_aop_annotation_04 {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring_config/applicationContext_aop_annotation.xml");

        UserService userService = context.getBean("userService", UserService.class);
        userService.saveUser();
    }


}
