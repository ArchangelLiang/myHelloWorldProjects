package com.unknown.jdbc;

import com.unknown.jdbc.pojo.User;
import com.unknown.jdbc.util.JdbcUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;

public class Jdbc_dbUtils_use_o9 {

    public static void main(String[] args) throws Exception{

    }

    public void insertOrUpdateOrDelete() throws Exception{
        QueryRunner runner = new QueryRunner();
        Connection conn = JdbcUtil.getConnection();
        String sql = "增删改语句";
        runner.update(conn,sql,"可变参数");
        conn.close();
    }

    public void query() throws Exception{
        QueryRunner runner = new QueryRunner();
        Connection conn = JdbcUtil.getConnection();
        String sql = "查询语句";
        User user = runner.query(sql, new BeanHandler<>(User.class), "可变参数");
        System.out.println(user);
        conn.close();
    }
}
