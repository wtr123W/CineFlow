package com.grassroots.cineflow.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.grassroots.cineflow.sqlite.SingleItem;
import com.grassroots.cineflow.sqlite.User;

public class CurrentUserUtils {

    private static final String CURRENT_USER = "CURRENT_USER";
    /**
     * 单例
     */
    private static CurrentUserUtils instance;
    private final SharedPreferences sp;

    public CurrentUserUtils(Context context) {
        sp = context.getSharedPreferences(CURRENT_USER, Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new CurrentUserUtils(context);
        }
    }

    public static User getCurrentUser() {
        String username = instance.sp.getString("username", null);
        Integer id = instance.sp.getInt("id", 0);
        if (username == null) {
            return null;
        }
        User user = new User(username, null);
        user.setId(id);
        return user;
    }

    public static void setCurrentUser(User user) {
        SharedPreferences.Editor editor = instance.sp.edit();
        editor.putString("username", user.getUsername());
        editor.putInt("id", user.getId());
        editor.apply();
    }

    public static void addMovie(SingleItem item) {

    }
}
