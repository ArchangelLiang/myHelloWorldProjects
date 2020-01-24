package jvm;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

public class JvmTest05 {

    public static void main(String[] args) {

        ServiceLoader<Driver> drivers = ServiceLoader.load(Driver.class);
        Iterator<Driver> driverIterator = drivers.iterator();

        while(driverIterator.hasNext()){
            Driver driver = driverIterator.next();
            System.out.println("class = "+driver.getClass()+",classLoader = "+driver.getClass().getClassLoader());
        }
        System.out.println("end");
    }

}
