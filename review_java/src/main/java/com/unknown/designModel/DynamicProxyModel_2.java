package com.unknown.designModel;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.time.Instant;
import java.time.ZoneOffset;

public class DynamicProxyModel_2 {

    public static void main(String[] args) {
    }
}

class CglibProxyClass{

    private ActualClass actualClass;

    public void setActualClass(ActualClass actualClass) {
        this.actualClass = actualClass;
    }

    private Instant instant;

    public Object getProxy(Object o){

       return Enhancer.create(o.getClass(), new MethodInterceptor() {
          @Override
          public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
              System.out.println("代理类做的事情");
              instant.atOffset(ZoneOffset.MAX);
              return method.invoke(o,args);
          }
      });
    }

}

