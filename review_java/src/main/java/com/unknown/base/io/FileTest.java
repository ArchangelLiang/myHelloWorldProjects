package com.unknown.base.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileTest {


    public static boolean deleteDirOrFile(File file) {
        return file.delete();
    }

    public static void traversalDir(File file) {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                if (f.isDirectory()) {
                    traversalDir(f);
                } else {
                    System.out.println(f.getAbsolutePath());
                }
            }
        } else {
            System.out.println(file.getAbsolutePath());
        }
    }

    public static void deleteDir(File file) {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                if (f.isDirectory()) {
                    if (f.list().length > 0) {
                        deleteDir(f);
                    } else {
                        f.delete();
                    }
                } else {
                    f.delete();
                }
            }
        } else {
            file.delete();
        }
        file.delete();
    }

    public static void main(String[] args) throws IOException {

     /*   String separator = File.separator;

        File file = new File("hello.txt");
        File file1 = new File("D:"+separator+"Work", "zio"+separator+"a.txt");
        File file2 = new File("D:\\test");
        File file3 = new File(file2, "zio.txt");
        File f = new File("D:\\");
        File relative = new File("D:");*/
//        System.out.println(file3);

       /* System.out.println(file.getAbsolutePath());
        System.out.println(file.getCanonicalPath());
        System.out.println(file.getPath());*/

//        System.out.println(file.length());

        /*FileInputStream inputStream = new FileInputStream(file);
        byte[] bs = new byte[10];
        int num = 0;
        while ((num = inputStream.read(bs, 0, bs.length))!=-1){
            System.out.print(new String(bs,0,num));
        }*/
     /*   System.out.println(relative.getAbsolutePath());
        System.out.println("---------------");
        for (String s : relative.list()) {
            System.out.println(s);
        }*/

    /*    File file = new File("zio.txt");
        if (!file.exists()){
            boolean b = file.createNewFile();
            System.out.println(b);
        } else {
            file.delete();
        }*/

       /* File file = new File("TestIo");
        if (!file.exists()){
            System.out.println(file.mkdir()+"外部文件创建");
            for (int i = 1; i < 4; i++) {
                if (i%2 == 0){
                    File file_txt = new File(file, "text.txt");
                    System.out.println("内部文件创建"+file_txt.createNewFile());
                }
                File internal_dir = new File(file, String.valueOf(i));
                System.out.println("内部文件夹创建"+internal_dir.mkdir());
            }
        }

        boolean b = deleteDirOrFile(new File("TestIo" + File.separator + "text.txt"));
        if (b){
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }

        for (String testIo : new File("TestIo").list()) {
            if (testIo.contains(".jpg")){
                int indexOf = testIo.lastIndexOf(".");
                String last_name = testIo.substring(indexOf);
                if (last_name.equals(".jpg")){
                    System.out.println(testIo);
                }
            }
        }*/

//       traversalDir(new File("D:\\TEST"));

//        deleteDir(new File("D:\\TEST"));


    }

    @Test
    public void test() {
        File file = new File("hello.txt");
        System.out.println(file.getAbsolutePath());
    }
}
