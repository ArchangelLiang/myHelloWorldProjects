package jvm;

public class JvmTest02 {

    public static void main(String[] args) {
        System.out.println(MyClass.a);
    }
}
interface MyInterface {
    int id = 1;
}
interface MyClass extends MyInterface{
    public static final int a = 3;
}