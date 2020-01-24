package com.unknown.framework.spring.bean.testIoc;

public class Test_static_factory {

    public static Test_bean getBean() {
        return new Test_bean();
    }
}
