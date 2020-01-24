package com.unknown.base.io;

import org.junit.Test;

import java.io.*;

public class FileInputStream_FileOutputStream {

    public static void main(String[] args) throws IOException {

        File base_dir = new File("review_jdbc\\test_io");
        File file = new File(base_dir, "zio.txt");

        if (!file.exists()) {
            boolean b = file.createNewFile();
            if (b) {
                System.out.println("创建文件成功！");
            }
        }

        FileReader reader = new FileReader(file);
      /*  int num = 0;
        while ((num = reader.read())!=-1){
            System.out.print((char)num);
        }*/

        try {
            char[] chars = new char[10];
            int data_num = 0;
            while ((data_num = reader.read(chars)) != -1) {
                System.out.print(new String(chars, 0, data_num));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }

    @Test
    public void testWriter() {
        File file = new File("test_io" + File.separator + "zio.txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write("hello what are you doing?");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testTextCopy() {
        File file = new File("test_io" + File.separator + "zio.txt");
        File target_file = new File("test_io" + File.separator + "decade.txt");
        FileReader reader = null;
        FileWriter writer = null;
        try {
            reader = new FileReader(file);
            writer = new FileWriter(target_file, true);
            char[] chars = new char[5];
            int data_num;
            while ((data_num = reader.read(chars)) != -1) {
                writer.write(new String(chars, 0, data_num));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testImgCopy() {
        File source_file = new File("test_io" + File.separator + "ROG.jpg");
        File destination_file = new File("test_io" + File.separator + "ROG_back.jpg");
        FileInputStream read = null;
        FileOutputStream writer = null;
        try {
            read = new FileInputStream(source_file);
            writer = new FileOutputStream(destination_file);
            byte[] bs = new byte[1024];
            int data_num;
            while ((data_num = read.read(bs)) != -1) {
                writer.write(bs, 0, data_num);
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
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
