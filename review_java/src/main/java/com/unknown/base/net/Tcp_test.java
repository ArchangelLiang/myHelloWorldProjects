package com.unknown.base.net;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Tcp_test {

    @Test
    public void testClient() throws Exception {
        InetAddress localHost = InetAddress.getLocalHost();
        Socket socket = new Socket(localHost, 9001);
        OutputStream writer = socket.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("test_io" + File.separator + "decade.txt"))));
        char[] chars = new char[5];
        int num;
        while ((num = reader.read(chars)) != -1) {
            writer.write(new String(chars, 0, num).getBytes());
        }
        socket.shutdownOutput();//注意：该处用来终止数据发送，等于告诉服务器数据传输完毕
        InputStream is = socket.getInputStream();
        BufferedInputStream input = new BufferedInputStream(is);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[5];
        while ((num = input.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, num);
        }
        String message = byteArrayOutputStream.toString();
        System.out.println("服务器返回响应：" + message);
        byteArrayOutputStream.close();
        input.close();
        is.close();
        reader.close();
        writer.close();
        socket.close();
    }

    @Test
    public void testServer() throws Exception {
        ServerSocket serverSocket = new ServerSocket(9001);
        Socket accept = serverSocket.accept();
        InputStream inputStream = accept.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[5];
        int num;
        while ((num = inputStream.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, num);
        }
        System.out.println("接受完毕");
        File file = new File("test_io\\tcp.txt");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        FileOutputStream outputStream = new FileOutputStream(file);
        while ((num = byteArrayInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, num);
        }
        System.out.println("文件写入完毕");
        OutputStream acceptOutputStream = accept.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(acceptOutputStream));
        writer.write("成功通信，结束本次连接");
        writer.close();
        outputStream.close();
        byteArrayInputStream.close();
        byteArrayOutputStream.close();
        inputStream.close();
        accept.close();
        serverSocket.close();
    }

}
