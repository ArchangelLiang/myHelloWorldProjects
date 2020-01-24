package com.unknown.framework.spring.bean.testJdbc_tx;

import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class User {

    private String userName;
    private String sex;
    private String address;
    private Date birthday;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
