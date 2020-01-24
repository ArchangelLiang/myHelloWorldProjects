package com.unknown.base.net;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

    @Test
    public void test01() throws UnknownHostException {
        InetAddress archangel = InetAddress.getByName("Archangel");
        System.out.println(archangel);
        System.out.println("_______________");
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);
        System.out.println("__________________");
        InetAddress localComputer = InetAddress.getByName("127.0.0.1");
        System.out.println(localComputer);
        System.out.println("__________________");
        InetAddress local = InetAddress.getByName("localhost");
        System.out.println(local);
        System.out.println("___________________________");
        System.out.println(localHost.getHostName());
        System.out.println(localHost.getHostAddress());
    }
}
