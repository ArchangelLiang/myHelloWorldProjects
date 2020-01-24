package com.unknown.base.io;

import org.junit.Test;

import java.io.*;

public class ObjectInputStream_ObjectOutputStream {

    @Test
    public void testObjectOutputStream() {
        File file = new File("test_io" + File.separator + "testObject.txt");
        FileOutputStream w = null;
        ObjectOutputStream write = null;
        try {
            w = new FileOutputStream(file);
            write = new ObjectOutputStream(w);
            write.writeObject(new TestObjectBean("decade", 33));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (write != null) {
                try {
                    write.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testObjectInputStream() {
        File file = new File("test_io" + File.separator + "testObject.txt");
        FileInputStream r = null;
        ObjectInputStream read = null;
        try {
            r = new FileInputStream(file);
            read = new ObjectInputStream(r);
            Object o = read.readObject();
            System.out.println(o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
