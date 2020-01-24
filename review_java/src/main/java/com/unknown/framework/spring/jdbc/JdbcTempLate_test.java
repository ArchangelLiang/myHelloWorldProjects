package com.unknown.framework.spring.jdbc;

import com.unknown.framework.spring.bean.testJdbc_tx.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Date;
import java.util.List;

public class JdbcTempLate_test {

    public static void main(String[] args) {
        //使用spring的内置数据源
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://localhost:3306/changel");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        //创建对象
        JdbcTemplate tp = new JdbcTemplate(dataSource);
        //保存
        tp.update("insert into user(username, birthday, sex, address) values (?,?,?,?)", "zio", new Date(new java.util.Date().getTime()), "男", "上海长宁");
        //更新
        tp.update("update user set sex = ? where username = ?", "女", "zio");
        //删除
        tp.update("delete from user where username = ?", "zio");
        //查询
        //方式一
       /* List<User> result = tp.query("select * from user where username = ?", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setUserName(resultSet.getString("username"));
                user.setAddress(resultSet.getString("address"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setSex(resultSet.getString("sex"));
                return user;
            }
        }, "zio");*/
        //方式二
        List<User> result = tp.query("select * from user where username = ?", new BeanPropertyRowMapper<User>(User.class), "zio");
        //查询返回一行一列的
        Integer male_count = tp.queryForObject("select count(*) from user where sex = ?", Integer.class, "男");
    }

}
