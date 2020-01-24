package com.unknown.base.commonClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Comparator;

public class Jdk8NewDate2 {

    public static void main(String[] args) {
        LocalDateTime of = LocalDateTime.of(2019, 9, 11, 3, 4, 33);
        DateTimeFormatter isoLocalDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        String format = dateTimeFormatter.format(of);
    }


}
