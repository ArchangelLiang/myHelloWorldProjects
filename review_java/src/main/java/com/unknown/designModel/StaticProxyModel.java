package com.unknown.designModel;

public class StaticProxyModel {

    public static void main(String[] args) {

        ProxyClass proxyClass = new ProxyClass(new ActualClass());
        proxyClass.sayHello();

    }
}

interface MyInterface {
    void sayHello();
}

class ProxyClass implements MyInterface {

    private MyInterface myInterface;

    public ProxyClass() {
    }

    public ProxyClass(MyInterface myInterface) {
        this.myInterface = myInterface;
    }

    @Override
    public void sayHello() {
        System.out.println("代理类做的前置业务");
        myInterface.sayHello();
        System.out.println("代理类做的后置业务");
    }
}

class ActualClass implements MyInterface {

    @Override
    public void sayHello() {
        System.out.println("实际类做的事情");
    }
}