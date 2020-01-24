package superclass.test;

import java.util.ArrayList;

public class Person {

    {
        System.out.println("父类的打印："+this);
    }

    protected void sayHello() throws NullPointerException{
        System.out.println("hello");
    }

}
