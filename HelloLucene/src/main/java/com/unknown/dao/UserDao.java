package com.unknown.dao;

import com.unknown.bean.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public List<User> findAll(){
        ArrayList<User> users = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase","root","root");

            String sql = "select * from user";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setGender(resultSet.getString("gender"));
                user.setAge(resultSet.getInt("age"));
                user.setAddress(resultSet.getString("address"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

}
