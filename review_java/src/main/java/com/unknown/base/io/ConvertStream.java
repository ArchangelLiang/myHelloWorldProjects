package com.unknown.base.io;

import org.junit.Test;

import java.io.*;

public class ConvertStream {

    public static void main(String[] args) {

    }

    @Test
    public void testInputStreamReader() {
        File file = new File("test_io" + File.separator + "zio.txt");
        FileInputStream inputStream = null;
        InputStreamReader in_reader = null;
        BufferedReader reader = null;
        try {
            inputStream = new FileInputStream(file);
            in_reader = new InputStreamReader(inputStream);
            reader = new BufferedReader(in_reader);
            String content = null;
            while ((content = reader.readLine()) != null) {
                System.out.print(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                    ;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testOutputStream() throws Exception {
        File file = new File("test_io" + File.separator + "zio.txt");
        FileOutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "GBK");
        writer.write("i don`t no,你是哪位");
        writer.close();
    }
}
