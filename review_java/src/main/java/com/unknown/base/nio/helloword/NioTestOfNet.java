package com.unknown.base.nio.helloword;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioTestOfNet {

    /**
     * NIO客户端
     * @throws Exception
     */
    @Test
    public void client() throws Exception {
        //获取一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置为非阻塞式
        socketChannel.configureBlocking(false);
        //提供服务端端的IP地址和端口号
        InetSocketAddress localhost = new InetSocketAddress("localhost", 9001);
        //连接服务器端
        if (!socketChannel.connect(localhost)) {
            //如果连接失败，就无限重连，直到连接成功
            while (!socketChannel.finishConnect()) {
                System.out.println("链接中。。。。");
            }
        }
        //创建一个缓冲区并放入数据
        ByteBuffer buffer = ByteBuffer.wrap("my is god".getBytes());
        //使用网络通道发送数据
        socketChannel.write(buffer);

        System.in.read();
    }

    /**
     * NIO服务端
     * @throws Exception
     */
    @Test
    public void server() throws Exception {
        //创建一个服务端通道对象
        ServerSocketChannel channel = ServerSocketChannel.open();

        InetSocketAddress socketAddress = new InetSocketAddress(9001);
        //绑定端口号
        channel.bind(socketAddress);
        //设置为非阻塞式
        channel.configureBlocking(false);
        //创建一个选择器对象
        Selector selector = Selector.open();
        //将服务端通道对象注册给选择器，并设置监测事件为连接事件
        channel.register(selector, SelectionKey.OP_ACCEPT);
        //让选择器一直监控客户端连接情况
        while (true) {
            //判断是否无连接
            if (selector.select(2000) == 0) {
                System.out.println("暂时无连接");
                continue;
            }
            //获取所有的selectionKey,判断通道里的事件,每个selectionKey都包含一个连接的相关信息
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            //遍历所有的selectionKey进行事件判断
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //判断是否为客户端连接事件
                if (key.isAcceptable()) {
                    System.out.println("发生了OP_ACCEPT事件");
                    //接受客户端的连接通道
                    SocketChannel accept = channel.accept();
                    //设置为非阻塞式
                    accept.configureBlocking(false);
                    //将这个客户端的通道注册给选择器，监控读取事件，并传入一个缓冲区用来读取客户端发送过来的附件数据
                    accept.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                //判断是否为读取事件
                if (key.isReadable()) {
                    //获取客户端的连接通道
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    //获取附件
                    ByteBuffer attachment = (ByteBuffer) key.attachment();
                    //读取客户端的数据
                    socketChannel.read(attachment);
                    System.out.println("客户端发送的数据："+new String(attachment.array()));
                }
                //处理完后，移除这个事件，防止重复处理
                iterator.remove();
            }


        }
    }
}
