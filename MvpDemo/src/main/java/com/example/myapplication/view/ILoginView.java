package com.example.myapplication.view;

import com.example.myapplication.model.bean.UserEntity;

/**
 * Created by jun on 2016/11/3.
 */

public interface ILoginView {

    String getName();

    String getPassword();

    void toMainActivity(UserEntity userEntity);

    void showFailedMsg();

    void clearLoginInfo();

}
