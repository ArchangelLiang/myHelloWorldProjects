package com.unknown.base.nio.other.channel;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class FileChannelTest {

    public static void main(String[] args) throws Exception {

        File file = new File("test_io2/testChannel.txt");
        FileInputStream read = new FileInputStream(file);
        FileChannel channel = read.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
        buffer.asReadOnlyBuffer();
        buffer.clear();
        int i = channel.read(buffer);
        System.out.println(i);
        buffer.clear();
        int i2 = channel.read(buffer);
        System.out.println(i2);
    }

    @Test
    public void write() throws IOException {
        File dir = new File("test_io2/testChannel.txt");
        FileOutputStream stream = new FileOutputStream(dir);
        FileChannel fileChannel = stream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String message = "hello,channel";
        byteBuffer.put(message.getBytes());
        byteBuffer.flip();
        int i = fileChannel.write(byteBuffer);
        System.out.println(i);
        stream.close();
    }

    @Test
    public void read() throws Exception {

        File file = new File("test_io2/testChannel.txt");
        FileInputStream read = new FileInputStream(file);
        FileChannel channel = read.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
        int i = channel.read(buffer);
        System.out.println(i);
        buffer.flip();
        byte[] bytes = buffer.array();
        System.out.println(new String(bytes));
    }

}
