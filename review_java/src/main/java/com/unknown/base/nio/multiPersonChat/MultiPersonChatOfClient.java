package com.unknown.base.nio.multiPersonChat;

import org.hamcrest.core.Is;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MultiPersonChatOfClient {

    private final String HOSTNAME = "127.0.0.1";//服务器端ip
    private final int PORT = 9999;//服务器端端口
    private SocketChannel sc;//客户端连接通道
    private String userName;//用户名

    public MultiPersonChatOfClient(String userName) {
        try {
            this.userName = userName;
            sc = SocketChannel.open();
            sc.configureBlocking(true);
            //无限尝试连接服务器端，知道成功为止
            while (!sc.connect(new InetSocketAddress(HOSTNAME,PORT))){
                if (!sc.finishConnect()){
                    System.out.println("重新尝试连接. . . . . .");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("---------------Client 【"+userName+"】 is ready---------------");
    }

    /**
     * 发送消息到服务器
     * @param message 消息体
     */
    public void sendMsg(String message) throws Exception{
        //发送bye或exit退出聊天
        if ("bye".equalsIgnoreCase(message) || "exit".equalsIgnoreCase(message)){
            sc.close();
            return;
        }
        message = userName+"说："+message;
        //发送消息
        sc.write(ByteBuffer.wrap(message.getBytes()));
    }

    public void receiveMsg() throws Exception{
        System.out.println("来到接受方法");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("开始读取");
        int count = sc.read(buffer);
        System.out.println("读取完毕,count="+count);
        if (count > 0){
            String message = new String(buffer.array()).trim();
            System.out.println("接收到消息:"+message);
        }
    }

    public static void main(String[] args) {
        MultiPersonChatOfClient zio = new MultiPersonChatOfClient("decade");
        new Thread(){
            @Override
            public void run() {
                try {
                    while (true){
                        TimeUnit.SECONDS.sleep(1);
                        zio.receiveMsg();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Scanner sc = new Scanner(System.in);
        System.out.println("hasNextLine方法调用前");
        while (sc.hasNextLine()){
            System.out.println("hasNextLine调用结束");
            String msg = sc.nextLine();
            try {
                zio.sendMsg(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
