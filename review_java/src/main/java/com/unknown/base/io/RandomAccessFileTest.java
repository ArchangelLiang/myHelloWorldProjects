package com.unknown.base.io;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {

    @Test
    public void testRandomAccessFile() throws Exception {

        File file = new File("test_io\\decade.txt");
        RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
        byte[] bytes = "test random".getBytes();
        accessFile.write(bytes);
        accessFile.close();
    }

    @Test
    public void testInsertData() {
        File file = new File("test_io\\decade.txt");
        RandomAccessFile rw = null;
        StringBuilder builder = new StringBuilder((int) file.length());
        try {
            rw = new RandomAccessFile(file, "rw");
            rw.seek(5);
            byte[] bytes = new byte[5];
            int len;
            while ((len = rw.read(bytes)) != -1) {
                builder.append(new String(bytes, 0, len));
            }
            rw.seek(5);
            rw.write("decade".getBytes());
            rw.write(builder.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rw != null) {
                try {
                    rw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testInsertDataOfByteArray() {
        File file = new File("test_io\\decade.txt");
        RandomAccessFile rw = null;
        ByteArrayOutputStream bs = null;
        try {
            rw = new RandomAccessFile(file, "rw");
            bs = new ByteArrayOutputStream();
            rw.seek(5);
            byte[] bytes = new byte[5];
            int len;
            while ((len = rw.read(bytes)) != -1) {
                bs.write(bytes, 0, len);
            }
            rw.seek(5);
            rw.write("decade".getBytes());
            rw.write(bs.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rw != null) {
                try {
                    rw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bs != null) {
                try {
                    bs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
