package com.example.myapplication.model;

/**
 * Created by jun on 2016/11/2.
 */

import com.example.myapplication.model.bean.UserEntity;

/**
 * 登录回调事件 接口
 */
public interface onLoginListener {
    //登录成功
    public void loginSuccess(UserEntity userEntity);
    //登录失败
    public void loginFailed();
}
