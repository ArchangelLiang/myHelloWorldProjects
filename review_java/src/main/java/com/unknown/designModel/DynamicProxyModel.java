package com.unknown.designModel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyModel {

    public static void main(String[] args) {
        ActualClass actualClass = new ActualClass();
        Object proxyClass = DynamicProxyClass.getProxyClass(actualClass);
        if (proxyClass instanceof MyInterface) {
            MyInterface m = (MyInterface) proxyClass;
            m.sayHello();
        }
    }
}

class DynamicProxyClass {

    public static Object getProxyClass(Object o) {
        return Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(o, args);
            }
        });
    }
}
