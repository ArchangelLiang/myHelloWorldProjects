package com.unknown.base.nio.multiPersonChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

public class MultiPersonChatOfServer {

    private ServerSocketChannel ssc;
    private Selector selector;

    public MultiPersonChatOfServer() {
        try {
            //创建一个服务端通道对象
            ssc = ServerSocketChannel.open();
            //绑定端口号
            ssc.bind(new InetSocketAddress(9999));
            //设置为非阻塞式
            ssc.configureBlocking(false);
            //创建一个选择器对象
            selector = Selector.open();
            //将服务端通道对象注册给选择器，并设置监测事件为连接事件
            ssc.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void start(){
        try {
            //让选择器一直监控客户端连接情况
            while (true){
                //判断是否无连接
                if (selector.select(2000) == 0){
                    System.out.println("等待连接中。。。。。。");
                    continue;
                }
                //获取所有的selectionKey,判断通道里的事件,每个selectionKey都包含一个连接的相关信息
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                //遍历所有的selectionKey进行事件判断
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    //判断是否为客户端连接事件
                    if (key.isAcceptable()){
                        //接受客户端的连接通道
                        SocketChannel sc = ssc.accept();
                        //设置为非阻塞式
                        sc.configureBlocking(false);
                        //将这个客户端的通道注册给选择器，监控读取事件，并传入一个缓冲区用来读取客户端发送过来的附件数据
                        sc.register(selector,SelectionKey.OP_READ);
                        System.out.println(sc.getRemoteAddress()+":上线了");
                    }
                    //判断是否为读取事件
                    if (key.isReadable()){
                        //读取客户端发送过来的消息并广播给其它客户端
                        readMsgAndMulticast(key);
                    }
                    //处理完后，移除这个事件，防止重复处理
                    iterator.remove();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void readMsgAndMulticast(SelectionKey key) {
        try {
            //获取客户端的连接通道
            SocketChannel sc = (SocketChannel) key.channel();
            //创建一个缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //将客户端的数据读取到缓冲区
            int count = sc.read(buffer);
            //判断是否读取到数据
            if (count > 0) {
                //打印到控制台并广播出去
                String message = new String(buffer.array());
                //打印到控制台
                printInfo(message);
                //广播到其它客户端
                multiCast(message,sc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 负责广播消息到其它客户端的方法
     * @param message 要广播的消息
     * @param exclude 消息发送者
     */
    private void multiCast(String message, SocketChannel exclude) {
        try {
            System.out.println("服务器开始广播");
            //创建缓冲区。用来存放要发送的消息
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
            //获取所有的连接信息，selectionKey
            Set<SelectionKey> keys = selector.keys();
            //遍历所有的selectionKey
            for (SelectionKey key:keys){
                //获取客户端的连接通道
                SelectableChannel channel = key.channel();
                SocketChannel sc;
                if (channel instanceof SocketChannel){
                    sc = (SocketChannel)channel;
                    //排除消息发送者
                    if (sc != exclude){
                        //发送消息
                        sc.write(buffer);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 专门用来打印消息的方法
     * @param info
     */
    private void printInfo(String info){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        System.out.println(date+"接收到消息："+info);
    }

    public static void main(String[] args) {
        MultiPersonChatOfServer multiPersonChatOfServer = new MultiPersonChatOfServer();
        multiPersonChatOfServer.start();
    }
}
