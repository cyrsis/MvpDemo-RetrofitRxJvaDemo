package com.example.myapplication.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.bean.UserEntity;
import com.example.myapplication.presenter.UserPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.bt_clear)
    Button btClear;
    @Bind(R.id.et_user)
    EditText etUser;
    @Bind(R.id.bt_login)
    Button btLogin;

    private UserPresenter userPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        userPresenter = new UserPresenter(this);
    }

    @OnClick({R.id.bt_clear, R.id.bt_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_clear:
                //清除
                userPresenter.clear();
                break;
            case R.id.bt_login:
                //登录
                userPresenter.login();
                break;
        }
    }

    @Override
    public String getName() {
        return etUser.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString().trim();
    }

    @Override
    public void toMainActivity(UserEntity userEntity) {
        Toast.makeText(this, "Login Sucessful" + ",User is :" + userEntity.getUsername() + ",Password is :" + userEntity.getPassword(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showFailedMsg() {
        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearLoginInfo() {
        etUser.setText("");
        etPassword.setText("");
    }
}
