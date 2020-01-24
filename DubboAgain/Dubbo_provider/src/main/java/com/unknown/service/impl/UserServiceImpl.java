package com.unknown.service.impl;

import com.unknown.bean.User;
import com.unknown.service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    public List<User> findAll() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "root");
            PreparedStatement preparedStatement = conn.prepareStatement("select * from user limit 10");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"),
                        resultSet.getString("name"), resultSet.getString("gender"),
                        resultSet.getInt("age"), resultSet.getString("address")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}
