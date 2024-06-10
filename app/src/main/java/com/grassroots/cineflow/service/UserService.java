package com.grassroots.cineflow.service;

import android.text.TextUtils;

import com.grassroots.cineflow.sqlite.User;
import com.grassroots.cineflow.sqlite.UserDatabaseHelper;
import com.grassroots.cineflow.utils.MD5Utils;

public class UserService {

    public static BusinessResult<User> login(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            return BusinessResult.fail("用户名不能为空");
        }

        if (TextUtils.isEmpty(password)) {
            return BusinessResult.fail("密码不能为空");
        }
        User user = UserDatabaseHelper.getUserByUsername(username);
        if (user == null) {
            return BusinessResult.fail("用户名不存在");
        }
        if (TextUtils.equals(MD5Utils.md5(password), user.getPassword())) {
            return BusinessResult.success("登录成功", user);
        }
        return BusinessResult.fail("密码错误");
    }

    public static BusinessResult<User> register(String username, String password, String confirmPassword) {
        if (TextUtils.isEmpty(username)) {
            return BusinessResult.fail("用户名不能为空");
        }

        if (TextUtils.isEmpty(password)) {
            return BusinessResult.fail("密码不能为空");
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            return BusinessResult.fail("确认密码不能为空");
        }

        if (!TextUtils.equals(password, confirmPassword)) {
            return BusinessResult.fail("两次密码不一致");
        }

        if (UserDatabaseHelper.isExistByUsername(username)) {
            return BusinessResult.fail("用户名已存在");
        }

        User user = new User(username, MD5Utils.md5(password));
        UserDatabaseHelper.insert(user);
        return BusinessResult.success("注册成功", user);
    }
}
