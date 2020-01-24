package com.unknown.jdbc;

import com.unknown.jdbc.util.JdbcUtil;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.sql.*;

public class Jdbc_insert_blob_03 {

    public static void main(String[] args) throws Exception{
    }


    @Test
    public void testInsetBlob(){

        Connection conn = JdbcUtil.getConnection();
        String sql = "insert into image_storage(imageName,imageContent) values (?,?)";
        PreparedStatement preparedStatement = null;
        BufferedInputStream stream = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setObject(1,"测试插入");
            File file = new File("test_io\\ROG.jpg");
            System.out.println(file.getAbsolutePath());
            stream = new BufferedInputStream(new FileInputStream(file));
            if (file.exists()){
                preparedStatement.setBinaryStream(2,stream);
                int i = preparedStatement.executeUpdate();
                if (i>0){
                    System.out.println("插入成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            JdbcUtil.close(conn,preparedStatement,null);
        }
    }

    @Test
    public void testReadBlob() throws Exception{
        Connection conn = JdbcUtil.getConnection();
        String sql = "select * from image_storage where id = 1";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Blob imageContent = resultSet.getBlob("imageContent");
            InputStream stream = imageContent.getBinaryStream();
            File file = new File("test_io\\Blob.jpg");
            FileOutputStream outputStream = new FileOutputStream(file);
            BufferedInputStream ip = new BufferedInputStream(stream);
            BufferedOutputStream op = new BufferedOutputStream(outputStream);
            byte[] bytes = new byte[1024];
            int num;
            while ((num = ip.read(bytes)) != -1) {
                op.write(bytes,0,num);
            }
            op.close();
            ip.close();
            resultSet.close();
            statement.close();
            conn.close();
        }
    }
}
