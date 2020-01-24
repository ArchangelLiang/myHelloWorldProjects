package com.unknown.base.reflectinStudy;

import com.unknown.base.io.TestObjectBean;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Reflection_test01 {

    public static void main(String[] args) throws Exception {

        Class clazz = TestObjectBean.class;
        Constructor constructor = clazz.getConstructor();
        Object instance = constructor.newInstance();
        System.out.println(instance);
    }

    @Test
    public void testClassInstance() throws ClassNotFoundException {
        Class clazz = TestObjectBean.class;//方式一
        Class<? extends TestObjectBean> aClass = new TestObjectBean().getClass();//方式二
        Class<?> aClass1 = Class.forName("com.unknown.base.io.TestObjectBean");//方式三
        ClassLoader classLoader = Reflection_test01.class.getClassLoader();
        Class<?> aClass2 = classLoader.loadClass("com.unknown.base.io.MyInput");//方式四
    }

    @Test
    public void testOther() throws Exception {
        Class c1 = int[][].class;
        Class c2 = int[].class;
        System.out.println(c1 == c2);//false
    }

    @Test
    public void testGetGenericSuperClass() throws Exception {
        Class<?> clazz = Class.forName("com.unknown.base.io.TestObjectBean");//获取指定类的Class对象
        Type genericSuperclass = clazz.getGenericSuperclass();//获取其带泛型的父类
        String typeName = genericSuperclass.getTypeName();//获取泛型父类的类名
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;//强转为其子类
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();//获取其所包含的所有泛型的类
        for (Type actualTypeArgument : actualTypeArguments) {
            System.out.println(actualTypeArgument.getTypeName());
        }
    }
}
