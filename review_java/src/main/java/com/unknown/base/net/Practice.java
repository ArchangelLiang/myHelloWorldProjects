package com.unknown.base.net;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Practice {

    @Test
    public void TcpClient() throws Exception {
        InetAddress localHost = InetAddress.getLocalHost();
        Socket socket = new Socket(localHost, 9001);
        OutputStream outputStream = socket.getOutputStream();
        Scanner sc = new Scanner(System.in);
        String message = sc.next();
        outputStream.write(message.getBytes());
        socket.shutdownOutput();
        outputStream.close();
        socket.close();
    }

    @Test
    public void TcpServer() throws Exception {
        ServerSocket serverSocket = new ServerSocket(9001);
        while (true) {
            System.out.println("服务器等待连接");
            Socket accept = serverSocket.accept();//阻塞
            System.out.println("接受到一个连接");
            InputStream inputStream = accept.getInputStream();
            System.out.println("开始读取客户端消息");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[5];
            int num;
            while ((num = inputStream.read(bytes)) != -1) {//阻塞
                System.out.println("========循环读取消息中=========");
                byteArrayOutputStream.write(bytes, 0, num);
            }
            System.out.println("消息读取完毕---打印消息:");
            System.out.println(byteArrayOutputStream.toString());
            System.out.println("消息打印完毕，开始关闭资源");
            byteArrayOutputStream.close();
            inputStream.close();
            accept.close();
            System.out.println("资源关闭完毕，进入下一次通信！");
        }
    }

}
