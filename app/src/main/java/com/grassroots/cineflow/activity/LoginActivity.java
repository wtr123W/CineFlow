package com.grassroots.cineflow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.grassroots.cineflow.R;
import com.grassroots.cineflow.service.BusinessResult;
import com.grassroots.cineflow.service.UserService;
import com.grassroots.cineflow.sqlite.User;
import com.grassroots.cineflow.utils.CurrentUserUtils;

public class LoginActivity extends AppCompatActivity {

    /**
     * 登录按钮,注册按钮
     */
    private Button btnLogin, btnRegister;

    /**
     * 用户名输入框,密码输入框
     */
    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // 初始化标题
        initTitle();
        // 初始化视图
        initView();
        // 初始化监听器
        initListener();
    }

    private void initTitle() {
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("登录");
        TextView tvBack = findViewById(R.id.tv_back);
        tvBack.setVisibility(View.GONE);
    }

    private void initView() {
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
    }

    private void initListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                BusinessResult<User> login = UserService.login(username, password);
                if (login.isSuccess()) {
                    CurrentUserUtils.setCurrentUser(login.getData());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    // 登录失败
                     Toast.makeText(LoginActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}