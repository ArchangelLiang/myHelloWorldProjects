package jvm;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;

public class JvmTest03 {

    public static void main(String[] args) throws Exception {

       /* MyClassLoader myClassLoader = new MyClassLoader("decadeClassLoader");
        Class<?> clazz = myClassLoader.loadClass("jvm.JvmTest");
        Object o = clazz.newInstance();
        System.out.println(o.getClass().getClassLoader());*/
        MyClassLoader zio = new MyClassLoader("zio");
        MyClassLoader decade = new MyClassLoader("decade");
        System.out.println(decade.getParent() == zio.getParent());  
    }
}

class MyClassLoader extends ClassLoader {

    private String classLoaderName;
    private static final String SUFFIX = ".class";

    public MyClassLoader(String name) {
        this.classLoaderName = name;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = loadClassData(name);
        return defineClass(name,bytes,0,bytes.length);
    }

    private byte[] loadClassData(String path) {
        String filePath = path.replace(".", "/")+SUFFIX;
        InputStream ip = null;
        ByteArrayOutputStream op = null;
        try {
            ip = new FileInputStream(new File(filePath));
            op = new ByteArrayOutputStream();
            int b;
            while ((b = ip.read()) != -1){
                op.write(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert op != null;
                op.close();
                ip.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return op.toByteArray();
    }

    @Override
    public String toString() {
        return "MyClassLoader{" +
                "classLoaderName='" + classLoaderName + '\'' +
                '}';
    }
}