package com.unknown.base.net;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Url_test {

    @Test
    public void testUrl() throws Exception {
        URL url = new URL("https://www.bilibili.com/video/av48144058/?p=627");

        String authority = url.getAuthority();
        System.out.println("authority=" + authority);
        String path = url.getPath();
        System.out.println("path=" + path);
        /*Object content = url.getContent();
        System.out.println("content="+content);*/
        int port = url.getDefaultPort();
        System.out.println("port=" + port);
        String host = url.getHost();
        System.out.println("host=" + host);
        String protocol = url.getProtocol();
        System.out.println("protocol=" + protocol);
        String file = url.getFile();
        System.out.println("file=" + file);
        String query = url.getQuery();
        System.out.println("query=" + query);
    }

    @Test
    public void testUrlConnection() throws Exception {

        URL url = new URL("https://i0.hdslb.com/bfs/sycp/creative_img/201910/31807e3255b137052d0fed27511b46e2.jpg");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.connect();
        InputStream inputStream = urlConnection.getInputStream();
        byte[] bytes = new byte[512];
        int num;
        FileOutputStream writer = new FileOutputStream("test_io\\picture.jpg");
        while ((num = inputStream.read(bytes)) != -1) {
            writer.write(bytes, 0, num);
        }
        writer.close();
        inputStream.close();
        urlConnection.disconnect();
    }
}
