package com.unknown.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class Jdbc_dataSource_druid_08 {


    @Test
    public void getConnectionFromDruid() throws Exception{
        Properties properties = new Properties();
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc/datasource/druid.properties");
        properties.load(inputStream);
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
        conn.close();
    }
}
