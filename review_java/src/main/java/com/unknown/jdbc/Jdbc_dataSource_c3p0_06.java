package com.unknown.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.junit.Test;

import java.sql.Connection;

public class Jdbc_dataSource_c3p0_06 {

    public static void main(String[] args) {

    }

    public void getConnectionFromC3p0() throws Exception{
        ComboPooledDataSource dataSource = new ComboPooledDataSource();//初始化连接池对象
        //设置数据库连接的基本信息
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/changel");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        //通过设置相关参数对数据库连接池进行管理
        dataSource.setInitialPoolSize(10);//设置初始连接池连接数为10个
        Connection conn = dataSource.getConnection();//通过数据库连接池获取连接
        conn.close();//将连接还回池中
        DataSources.destroy(dataSource);//销毁连接池，一般不会用
    }

    @Test
    public void getConnectionFromC3p0_config() throws Exception{
        ComboPooledDataSource dataSource = new ComboPooledDataSource("myc3p0");//加载配置文件初始化连接池对象
        Connection conn = dataSource.getConnection();//通过数据库连接池获取连接
        conn.close();//将连接还回池中

    }
}
