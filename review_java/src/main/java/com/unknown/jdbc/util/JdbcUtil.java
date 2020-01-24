package com.unknown.jdbc.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {

    private static Connection conn;

    static {
        Properties info = new Properties();
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc" + File.separator + "jdbc.properties");
        try {
            info.load(inputStream);
            String driver =  info.getProperty("jdbc.driver");
            String url =  info.getProperty("jdbc.url");
            String user =  info.getProperty("jdbc.user");
            String password =  info.getProperty("jdbc.password");
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static Connection getConnection(){
        return conn;
    }

    public static void close(Connection conn, Statement statement, ResultSet resultSet){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
