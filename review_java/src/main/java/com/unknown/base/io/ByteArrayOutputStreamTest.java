package com.unknown.base.io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ByteArrayOutputStreamTest {

    @Test
    public void testByteArrayOutputStream() throws Exception {
        String message = "hello,my is kamen rider";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        stream.write(message.getBytes());
        stream.flush();
        byte[] bytes = stream.toByteArray();
        String result = new String(bytes);
        System.out.println(result);
        stream.close();
    }

    @Test
    public void testByteArrayIutputStream() throws Exception {
        String content = "my have a dream";
        ByteArrayInputStream read = new ByteArrayInputStream(content.getBytes());
        byte[] bytes = new byte[5];
        int num;
        while ((num = read.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, num));
        }

        read.close();
    }
}
