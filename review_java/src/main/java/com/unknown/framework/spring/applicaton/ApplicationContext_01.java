package com.unknown.framework.spring.applicaton;

import com.unknown.framework.spring.bean.testIoc.Test_bean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContext_01 {

    public static void main(String[] args) {

        //hello world基本示例
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring_config/applicationContext_ioc.xml");
        Test_bean test_bean = context.getBean("test_bean", Test_bean.class);
        System.out.println(test_bean);

    }

    @Test
    public void test01() {
        //进行容器正常关闭
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring_config/applicationContext_ioc.xml");
        applicationContext.close();
    }
}
