package com.unknown.base.io;

import org.junit.Test;

import java.io.*;

public class OtherStream {

    public static void main(String[] args) throws Exception {

/*        InputStream is = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String message;
        System.out.println("please input!");
        message = reader.readLine();
        System.out.println("你输入的内容是:" + message);

        reader.close();
        inputStreamReader.close();
        is.close();*/

       /* MyInput myInput = new MyInput();
        System.out.println("请输入消息");
        String message = myInput.readerString();
        System.out.println("您输入的消息为："+message.toUpperCase());*/

        MyInput myInput = new MyInput();
        System.out.println("please input");
        Integer integer = myInput.readInt();
        System.out.println("reading you input message is" + integer);
    }

    @Test
    public void testStandardStreamOfIn() throws Exception {

    }

    @Test
    public void testPrintStream() throws FileNotFoundException {
        File file = new File("test_io" + File.separator + "zio.txt");

        PrintStream stream = new PrintStream(file);

        System.setOut(stream);

        System.out.println("这是一条测试消息");

        stream.close();
    }
}

class MyInput {

    private BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    public String readerString() {
        String content = "empty message!";
        try {
            content = read.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        close();
        return content;
    }

    public Integer readInt() {
        try {
            String int_str = read.readLine();
            return Integer.parseInt(int_str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("输入不合法");
    }

    public void close() {
        try {
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}