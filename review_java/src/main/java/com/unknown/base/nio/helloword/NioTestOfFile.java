package com.unknown.base.nio.helloword;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTestOfFile {

    /**
     * 测试NIO写入数据到文件
     * @throws Exception
     */
    @Test
    public void write() throws Exception {
        //创建文件输出流
        FileOutputStream stream = new FileOutputStream("test_io\\nio.txt");
        //从流中获取通道
        FileChannel channel = stream.getChannel();
        //创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(10);
        //往缓冲区添加数据
        buffer.put("hello,nio".getBytes());
        //翻转指针到初始位置，否则无法有效写入数据，因为指针位置已经在填充数据时移动到最后了
        buffer.flip();
        //将缓冲区的数据写入通道
        channel.write(buffer);
        //关闭流
        stream.close();
    }

    /**
     * 测试NIO从文件中读取数据
     * @throws Exception
     */
    @Test
    public void read() throws Exception {
        File file = new File("test_io" + File.separator + "nio.txt");
        //创建文件输入流
        FileInputStream stream = new FileInputStream(file);
        //从流中获取通道
        FileChannel channel = stream.getChannel();
        //创建一个和文件相同大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
        //通道将数据读取到缓冲区内
        channel.read(buffer);
        byte[] bytes = buffer.array();
        //输出读取到的数据
        System.out.println(new String(bytes));
        //关闭流
        stream.close();
    }

    /**
     * 测试NIO文件复制
     * @throws Exception
     */
    @Test
    public void copy() throws Exception {
        File read_file = new File("test_io" + File.separator + "decade.txt");
        File destination_file = new File("test_io" + File.separator + "decade_backup.txt");
        //分别创建文件输入流和文件输出流
        FileInputStream inputStream = new FileInputStream(read_file);
        FileOutputStream outputStream = new FileOutputStream(destination_file);
        //得到两个通道
        FileChannel sc = inputStream.getChannel();
        FileChannel ds = outputStream.getChannel();
        //从文件输入流的通道中将数据读取到文件输出流的通道，后面两个参数的含义为从头开始拿所有数据
        ds.transferFrom(sc, 0, sc.size());
        //流的关闭
        inputStream.close();
        outputStream.close();
    }


}
