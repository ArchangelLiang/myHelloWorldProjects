package com.unknown.base.java8.lambda;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class Lambda_test {

    public static void main(String[] args) {
        testConsumer("decade", a -> System.out.println("测试消费型接口，输出：" + a));
        String s = testSupplier(() -> "hi,this is supplier");
        String s1 = testFunction(new Date(), date -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        boolean b = testPredicate(79, i -> i >= 80);
    }

    /**
     * 测试消费型接口 Consumer<T> 返回值void 参数（T t）
     */
    public static void testConsumer(String name, Consumer<String> consumer) {
        consumer.accept(name);
    }

    /**
     * 测试供给型接口 Supplier<T> 返回值T 参数（）
     */
    public static String testSupplier(Supplier<String> supplier) {
        return supplier.get();
    }

    /**
     * 测试函数型接口 Function<T,R> 返回值R 参数（T t）
     */
    public static String testFunction(Date date, Function<Date, String> function) {
        return function.apply(date);
    }

    /**
     * 测试断定型接口 Predicate<T> 返回值boolean 参数（T t）
     */
    public static boolean testPredicate(int i, Predicate<Integer> predicate) {
        return predicate.test(i);
    }

}
