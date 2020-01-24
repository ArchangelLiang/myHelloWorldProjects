package com.unknown.framework.spring.applicaton;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContext_annotation_02 {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.unknown.framework.spring.bean.testIoc_annotation");
        Object test_bean = applicationContext.getBean("test_bean");
        System.out.println(test_bean);

    }

    @Test
    public void test01() {

    }
}
