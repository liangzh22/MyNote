package com.example.administrator.mynote.Model;

/**
 * Created by Administrator on 2016/6/30.
 */
public class User {
    private String userName;
    private String password;
    private String email;
    public User(){}
    public User(String username,String password){
        this.userName=username;
        this.password=password;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
