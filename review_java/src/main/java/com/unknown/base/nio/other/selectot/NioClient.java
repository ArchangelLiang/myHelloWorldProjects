package com.unknown.base.nio.other.selectot;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {

    public static void main(String[] args) throws Exception{

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress socketAddress = new InetSocketAddress("localhost", 6666);
        if (!socketChannel.connect(socketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("尝试重连中。。。");
            }
        }

        ByteBuffer buffer = ByteBuffer.wrap("helloNio".getBytes());
        socketChannel.write(buffer);
        System.in.read();//使代码挺在这里
    }

}
