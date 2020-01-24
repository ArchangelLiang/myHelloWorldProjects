package com.unknown.base.java8.optional;

import java.time.Instant;
import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        Instant instant = Instant.now();
        //创建optional
        //方式一：of(该参数不可为null)
        Optional<Instant> optional = Optional.of(instant);
        System.out.println(optional);
        //方式二：ofNullable(参数可以为null)
        instant = null;
        Optional<Instant> o1 = Optional.ofNullable(instant);
        System.out.println(o1);
        Instant orElse = o1.orElse(Instant.now());
    }

}
