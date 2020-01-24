package com.unknown.jdbc;

import com.unknown.jdbc.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Jdbc_batch_insert_04 {

    public static void main(String[] args) {

    }

    public void testBatchInset() throws Exception{
        Connection conn = JdbcUtil.getConnection();
        String sql = "insert into user(username) values (?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        conn.setAutoCommit(false);//因为每次插入都自动提交也会影响性能，所以设置为手动提交
        for (int i = 0; i < 50; i++) {
            statement.setObject(1,i+"");
            //缓存sql参数，减少与数据库交互的次数从而提高性能
            statement.addBatch();
            if (i % 10 == 0){
                statement.executeBatch();//批处理执行
                //执行之后的缓存sql参数清空掉，以免影响下次执行
                statement.clearBatch();
            }
        }
        conn.commit();
        JdbcUtil.close(conn,statement,null);
    }
}
