package com.unknown.base.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Practice {

    public static void main(String[] args) {

        File file = new File("review_jdbc\\test_io\\zio.txt");
        FileReader read = null;
        HashMap<String, Integer> map = new HashMap<>();
        try {
            read = new FileReader(file);
            char[] chars = new char[5];
            int data_num;
            while ((data_num = read.read(chars)) != -1) {
                for (int i = 0; i < data_num; i++) {
                    String key = String.valueOf(chars[i]);
                    if (map.containsKey(key)) {
                        Integer num = map.get(key);
                        num++;
                        map.put(key, num);
                    } else {
                        if (!key.equals(" "))
                            map.put(key, 1);
                    }
                }
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
        System.out.println(map);
    }
}
