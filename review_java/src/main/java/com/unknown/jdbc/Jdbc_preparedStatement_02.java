package com.unknown.jdbc;

import com.unknown.jdbc.pojo.User;
import com.unknown.jdbc.util.JdbcUtil;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Properties;

public class Jdbc_preparedStatement_02 {


    public static void main(String[] args) {

        String sql = "select id,username,birthday,sex,address from user where id = ?";
        User user = queryForUser(sql,  41);
        System.out.println(user);
    }


    public void testPreparedStatement() throws Exception{

        Properties  info = new Properties();
        InputStream inputStream = Jdbc_preparedStatement_02.class.getClassLoader().getResourceAsStream("jdbc/jdbc.properties");
        info.load(inputStream);
        String userName = info.getProperty("jdbc.user");
        String password = info.getProperty("jdbc.password");
        String driver = info.getProperty("jdbc.driver");
        String url = info.getProperty("jdbc.url");
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, userName, password);
        PreparedStatement statement = conn.prepareStatement("select * from user where username = ?");
        statement.setString(1,"decade");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("username"));
        }
        resultSet.close();
        statement.close();
        conn.close();
    }

    public static void update(String sql,Object...args){
        //使用工具类获取链接
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement statement = null;
        try {
            //使用statement进行增删改操作
            statement = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i+1,args[i]);
            }
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //使用工具类关闭资源
            JdbcUtil.close(conn,statement,null);
        }
    }

    public static User queryForUser(String sql,Object...args){
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i+1,args[i]);
            }
            resultSet = statement.executeQuery();
            //获取元数据信息
            ResultSetMetaData metaData = resultSet.getMetaData();
            //通过原数据获取查询的列数
            int columnCount = metaData.getColumnCount();
            if (resultSet.next()){
                User user = new User();
                for (int i = 0; i < columnCount; i++) {
                    //通过索引使用元数据获取列名
                    String columnName = metaData.getColumnName(i + 1);
                    Object columnValue = resultSet.getObject(i + 1);
                    if ("birthday".equals(columnName)) {
                        System.out.println(columnValue.getClass());
                    }
                    //使用反射进行赋值，前提是列名与属性名称一致
                    Class<User> userClass = User.class;
                    Field field = userClass.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(user,columnValue);
                }
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn,statement,resultSet);
        }
        return null;
    }

    public static <T> T queryForObject(String sql,Class<T> clazz,Object...args){
        //从工具类回去链接
        Connection conn = JdbcUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = conn.prepareStatement(sql);
            //根据传递的参数循环给sql语句的占位符赋值
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1,args[i]);
            }
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                T instance = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(i + 1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(instance,columnValue);
                }
                return instance;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn,statement,resultSet);
        }
        return null;
    }

}
