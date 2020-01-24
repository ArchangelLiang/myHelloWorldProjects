package com.unknown.base.io;

import org.junit.Test;

import java.io.*;

public class BufferInput_BufferOutput {

    @Test
    public void testBufferInputStream() {
        File file = new File("test_io" + File.separator + "zio.txt");
        BufferedReader read = null;
        try {
            read = new BufferedReader(new FileReader(file));
            String content;
            while ((content = read.readLine()) != null) {
                System.out.println(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
