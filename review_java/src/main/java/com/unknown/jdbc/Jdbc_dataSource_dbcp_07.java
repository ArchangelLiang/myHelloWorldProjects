package com.unknown.jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class Jdbc_dataSource_dbcp_07 {

    public static void main(String[] args) throws Exception{

    }

    @Test
    public void getConnectionFromDbcp() throws Exception{
        BasicDataSource dataSource = new BasicDataSource();//初始化连接池对象
        //设置数据库连接的基本信息
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/changel");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        //通过设置相关参数对数据库连接池进行管理
        dataSource.setInitialSize(10);
        dataSource.setMaxActive(10);
        Connection conn = dataSource.getConnection();//从连接池获取连接
        conn.close();//将连接还回池中
    }

    @Test
    public void getConnectionFromDbcp_config() throws Exception{
        Properties properties = new Properties();
        //使用系统加载器获取配置文件的输入流
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc/datasource/dbcp.properties");
        properties.load(inputStream);
        //通过加载了配置文件的properties创建连接池
        DataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
        conn.close();//将连接还回池中
    }
}
