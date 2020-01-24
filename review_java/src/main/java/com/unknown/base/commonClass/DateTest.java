package com.unknown.base.commonClass;

import java.net.URL;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;

public class DateTest {

    public static void main(String[] args) throws Exception {

       /* Date date = new Date();
        System.out.println(date);

        java.sql.Date dt = new java.sql.Date(System.currentTimeMillis());
        System.out.println(dt);*/

        /*DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒");
        String format = dateFormat.format(new Date());
        System.out.println(format);*/

        /*Date date = new java.sql.Date(new Date().getTime());

        System.out.println(date);*/

       /* Scanner sc = new Scanner(System.in);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        long sourceTime = format.parse("2010年1月1日").getTime();
//        String date = sc.next();
        Date pda = format.parse("2019年8月17日");
        long time = pda.getTime();
        long computerTime = time - sourceTime;
        System.out.println("输出时间："+time);
        long day = time/1000/60/60/24;
        long result = day % 5;
        System.out.println(result);*/

        /*GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, 1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(year + "年" + month + "月" + day + "日");*/
//        System.out.println(calendar);
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Enumeration<URL> resources = classLoader.getResources("com/unknown/base/commonClass/DateTest02.class");
        while (resources.hasMoreElements()){
            System.out.println(resources.nextElement());
        }
        System.out.println("程序执行结束");
    }
}
