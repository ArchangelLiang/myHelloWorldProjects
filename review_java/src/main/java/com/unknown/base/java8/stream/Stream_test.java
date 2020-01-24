package com.unknown.base.java8.stream;

import com.alibaba.druid.sql.visitor.functions.Char;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stream_test {

    @Test
    public void test_constructor() {
        ArrayList<String> list = new ArrayList<>();
        //实例化方式一：顺序Stream
        Stream<String> stream = list.stream();
        //并行Stream
        Stream<String> parallelStream = list.parallelStream();
        //实例化方式二：通过数组
        Stream<String> arrayStream = Arrays.stream(new String[]{"zio", "decade"});
        //实例化方式三：
        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        //实例化方式四：创建无限流
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::print);//限制只生成10个偶数，否则无限个
        Stream.generate(Math::random).limit(10).forEach(System.out::print);//限制只生成10个随机数，否则无限个
    }

    @Test
    public void test_operation(){
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 8);
//        stream.filter(i->i>5).forEach(System.out::println);
//        stream.skip(2).forEach(System.out::println);
//        stream.map(Integer::toBinaryString).forEach(System.out::println);
        List<Integer> list = stream.sorted().collect(Collectors.toList());
        System.out.println(list);
    }
}
