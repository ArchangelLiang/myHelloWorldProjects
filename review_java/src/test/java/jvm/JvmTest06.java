package jvm;

import java.net.URL;
import java.util.Enumeration;

public class JvmTest06 {

    public static void main(String[] args) throws Exception {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources("java/lang/string");

        while (resources.hasMoreElements()){
            URL element = resources.nextElement();
            System.out.println(element);
        }

    }

}
