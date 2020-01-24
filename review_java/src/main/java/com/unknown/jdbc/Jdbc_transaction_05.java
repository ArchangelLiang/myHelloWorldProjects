package com.unknown.jdbc;

import com.unknown.jdbc.util.JdbcUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Jdbc_transaction_05 {

    public static void main(String[] args) {

        Connection conn = JdbcUtil.getConnection();
        try {
            conn.setAutoCommit(false);//关闭自动提交
            String sql = "update user set username = ? where id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setObject(1,"zio");
            statement.setObject(2,1);
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();//出现异常就回滚
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void testTransactionIsolationLevel() throws Exception{
        Connection conn = JdbcUtil.getConnection();
        int isolationLevel = conn.getTransactionIsolation();
        conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
    }
}
