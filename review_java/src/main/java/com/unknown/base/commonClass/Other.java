package com.unknown.base.commonClass;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Other {

    public static void main(String[] args) {

        /*System.exit(0);
        System.gc();*/
        /*String version = System.getProperty("os.version");
        System.out.println(version);*/

        BigInteger integer = new BigInteger("999");
        BigInteger subtract = integer.subtract(new BigInteger("100"));
        System.out.println(integer);
        System.out.println(subtract);
        BigDecimal bigDecimal = new BigDecimal("99.9");
        bigDecimal.divide(new BigDecimal("2.2"), BigDecimal.ROUND_HALF_DOWN);
    }
}
