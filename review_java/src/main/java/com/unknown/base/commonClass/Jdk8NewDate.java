package com.unknown.base.commonClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Jdk8NewDate {

    public static void main(String[] args) {

      /*  LocalDateTime now = LocalDateTime.now();
        System.out.println(now.getDayOfWeek());
        LocalDateTime localDateTime = now.withDayOfMonth(2);
        LocalDateTime plusDays = now.plusDays(3);*/

      /*  Instant instant = Instant.now();
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        long l = instant.toEpochMilli();*/
        /*  DateTimeFormatter isoLocalDateTime = DateTimeFormatter.ISO_LOCAL_DATE_TIME;*/
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = LocalDate.now();
//        String fTime = isoLocalDateTime.format(now);
//        System.out.println(fTime);
//        System.out.println("________________");
//        TemporalAccessor parse = isoLocalDateTime.parse("2019-10-18T11:30:07.822");
//        System.out.println(parse);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        String format = timeFormatter.format(localDateTime);
        System.out.println(format);
        System.out.println("________________");
        DateTimeFormatter localizedDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        String localTime = localizedDate.format(localDate);
        System.out.println(localTime);
        System.out.println("______________________");
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyy_MM_dd HH:mm:ss:SSS");
        String format1 = ofPattern.format(localDateTime);
        System.out.println(format1);
    }
}
