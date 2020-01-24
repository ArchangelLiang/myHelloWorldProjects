package superclass.test;

public class Son extends Person {

    @Override
    protected void sayHello() {
        super.sayHello();
        System.out.println("这是我子类的打印");
    }

    public static void main(String[] args) {

        Son son = new Son();
        Son son2 = new Son();
        son.sayHello();
    }

}
