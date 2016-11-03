package com.example.myapplication.model.bean;

/**
 * Created by jun on 2016/11/2.
 */

public class UserEntity {

    public UserEntity() {
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username ;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassworld(String password) {
        this.password = password;
    }
}
