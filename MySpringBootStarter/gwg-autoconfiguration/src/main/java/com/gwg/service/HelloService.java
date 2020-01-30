package com.gwg.service;

import com.gwg.config_properties.HelloProperties;

public class HelloService {

    private HelloProperties helloProperties;

    public HelloService() {
    }

    public HelloService(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public void sayHello(String name){
        System.out.println(this.helloProperties.getPrefix() + name + helloProperties.getSuffix());
    }

}
