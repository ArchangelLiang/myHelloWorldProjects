package com.unknown.base.annotationTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyAnnotation {
    String value() default "zio";
}

enum test {
    zio("decade"), decade("zio");
    private final String name;

    private test(String name) {
        this.name = name;
    }
}