package com.example.myapplication.model;

/**
 * Created by jun on 2016/11/2.
 */

import com.example.myapplication.model.bean.UserEntity;

/**
 * 实现 IUser 接口类
 */
public class IpIUser implements  IUser{
    @Override
    public void login(String name, String password, onLoginListener onLoginListener) {
        if (name.equals("qiuj") && password.equals("qiuj")){
            UserEntity userEntity = new UserEntity(name,password);
            onLoginListener.loginSuccess(userEntity);
        }else{
            onLoginListener.loginFailed();
        }
    }
}
