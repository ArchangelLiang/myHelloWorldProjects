package com.unknown.base.java8.lambda;

import org.junit.Test;

import java.io.PrintStream;
import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MethodReference {

    @Test
    public void test_1() {
        PrintStream ps = System.out;
        Runnable run = ps::println;

        Supplier<Instant> sp = Instant::now;
        Instant instant = sp.get();
        System.out.println(instant);

        Comparator<Integer> cp = Integer::compare;

        Comparator<String> c = String::compareTo;

        BiPredicate<String, String> bp = String::equals;

        Function<Instant, Long> fun = Instant::toEpochMilli;

        Supplier<Date> sd = Date::new;

        Function<Integer, String[]> fus = String[]::new;

        String[] apply = fus.apply(10);
        System.out.println(apply.length);
    }
}
