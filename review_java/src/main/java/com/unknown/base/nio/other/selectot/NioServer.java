package com.unknown.base.nio.other.selectot;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    public static void main(String[] args) throws Exception {

        ServerSocketChannel sc = ServerSocketChannel.open();
        sc.bind(new InetSocketAddress(6666));
        sc.configureBlocking(false);
        Selector selector = Selector.open();
        sc.register(selector, SelectionKey.OP_ACCEPT);

        while (true){

            if (selector.select(1000) == 0){
                System.out.println("no client connect.....");
                continue;
            }

            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

            while (keyIterator.hasNext()){

                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()){
                    SocketChannel socket = sc.accept();
                    socket.configureBlocking(false);
                    socket.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (key.isReadable()){
                    SocketChannel socket = (SocketChannel)key.channel();
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    socket.read(buffer);
                    System.out.println("客户端发送的消息为："+new String(buffer.array()));
                }
                keyIterator.remove();
            }
        }

    }

}
