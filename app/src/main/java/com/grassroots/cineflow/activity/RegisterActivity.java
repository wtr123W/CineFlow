package com.grassroots.cineflow.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.grassroots.cineflow.R;
import com.grassroots.cineflow.service.BusinessResult;
import com.grassroots.cineflow.sqlite.User;
import com.grassroots.cineflow.service.UserService;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;

    private EditText etUsername, etPassword, etPasswordAgain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initTitle();
        initView();
        initListener();
    }

    private void initTitle() {
        TextView tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("注册");
        TextView tvBack = findViewById(R.id.tv_back);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        btnRegister = findViewById(R.id.btn_register);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etPasswordAgain = findViewById(R.id.et_password_again);
    }

    private void initListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String passwordAgain = etPasswordAgain.getText().toString();
                BusinessResult<User> result = UserService.register(username, password, passwordAgain);
                Toast.makeText(RegisterActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                if (result.isSuccess()) {
                    finish();
                }
            }
        });
    }
}
