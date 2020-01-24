package com.unknown.base.nio.other.channel;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class SocketChannelTest {

    public static void println(Object o) {
        System.out.println(o);
    }

    public static void main(String[] args) throws Exception {
        //初始化serverSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress socketAddress = new InetSocketAddress(9999);
        serverSocketChannel.bind(socketAddress);
        //创建一个buffer数组，用来将数据读取到数组和将数组中的数据写回
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(5);
        //等待客户端连接
        println("waiting connecting ....");
        SocketChannel channel = serverSocketChannel.accept();
        println("accept a connect");
        //循环读取数据
        int limitMessages = 10;
        while (true) {
            int readCount = 0;
            while (readCount < limitMessages) {
                readCount += channel.read(byteBuffers);
                println("readCount = " + readCount);
                Arrays.stream(byteBuffers).map(buffer -> "position=" + buffer.position() + ",limit=" + buffer.limit()).forEach(System.out::println);
            }

            Arrays.stream(byteBuffers).forEach(Buffer::flip);

            int writeCount = 0;
            while (writeCount < limitMessages) {
                writeCount += (int) channel.write(byteBuffers);
            }

            Arrays.stream(byteBuffers).forEach(ByteBuffer::clear);

        }
    }

}
