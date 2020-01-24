package com.unknown.framework.spring.bean.testAop;

import org.aspectj.lang.ProceedingJoinPoint;

public class AdviceClass {

    public void myBeforeAdvice(){
        System.out.println("hello.this is before advice method");
    }

    public void myAfterAdvice(){
        System.out.println("hello.this is after advice method");
    }

    public void myCatchAdvice(){
        System.out.println("hello.this is catch advice method");
    }

    public void myFinallyAdvice(){
        System.out.println("hello.this is finally advice method");
    }

    public Object myAroundAdvice(ProceedingJoinPoint pj){
        System.out.println("this is around advice method");
        Object returnValue = null;
        Object[] args = pj.getArgs();//获取切入点方法执行时的需要参数
        try {
            myBeforeAdvice();//自定义前置通知的位置
            returnValue = pj.proceed(args);//相当于明确调用切入点方法
            myAfterAdvice();//自定义后置通知的位置
        } catch (Throwable throwable) {//该处异常必须这么写
            throwable.printStackTrace();
            myCatchAdvice();//自定义异常通知的位置
        } finally {
            myFinallyAdvice();//自定义最终通知的位置
        }
        return returnValue;
    }

}
