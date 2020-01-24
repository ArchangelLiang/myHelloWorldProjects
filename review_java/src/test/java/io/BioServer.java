package io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {

    public static void main(String[] args) throws Exception {

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true) {
            System.out.println("等待连接");
            Socket socket = serverSocket.accept();
            System.out.println("接收到一个连接");
            cachedThreadPool.execute(() -> handler(socket));
        }
}

    public static void handler(Socket socket) {
        try {
            InputStream read = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(read);
            char[] chars = new char[20];
            int i;
            while ((i = reader.read(chars)) != -1){
                System.out.println("接收到的消息"+new String(chars,0,i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
