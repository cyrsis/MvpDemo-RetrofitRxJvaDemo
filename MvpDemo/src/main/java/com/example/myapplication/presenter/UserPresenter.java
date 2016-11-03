package com.example.myapplication.presenter;

import com.example.myapplication.model.IpIUser;
import com.example.myapplication.model.bean.UserEntity;
import com.example.myapplication.model.onLoginListener;
import com.example.myapplication.view.ILoginView;

/**
 * Created by jun on 2016/11/3.
 */

public class UserPresenter {

    private IpIUser ipIUser;

    private ILoginView iLoginView;


    public UserPresenter(ILoginView iLoginView) {
        this.ipIUser = new IpIUser();
        this.iLoginView = iLoginView;
    }

    public void login(){
        ipIUser.login(iLoginView.getName(), iLoginView.getPassword(), new onLoginListener() {
            @Override
            public void loginSuccess(UserEntity userEntity) {
                iLoginView.toMainActivity(userEntity);
            }

            @Override
            public void loginFailed() {
                iLoginView.showFailedMsg();
            }
        });
    }

    public void clear(){
        iLoginView.clearLoginInfo();
    }
}
