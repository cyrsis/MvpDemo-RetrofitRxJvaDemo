package com.example.myapplication.model;

import com.example.myapplication.model.bean.UserEntity;

/**
 * Created by jun on 2016/11/2.
 */

public interface IUser {

    //登录
    public void login(String name, String password, onLoginListener onLoginListener);

}
