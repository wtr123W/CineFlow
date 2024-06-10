package com.grassroots.cineflow.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    /**
     * 单例
     */
    private static UserDatabaseHelper instance;

    private UserDatabaseHelper(Context context) {
        super(context, "user.db", null, 1);
    }

    /**
     * 获取单例
     *
     * @param context
     * @return
     */
    public static void init(Context context) {
        if (instance == null) {
            instance = new UserDatabaseHelper(context);
        }
    }

    /**
     * 添加用户
     */
    public static void insert(User user) {
        SQLiteDatabase db = instance.getWritableDatabase();
        String sql = "INSERT INTO user(username,password) VALUES(?,?)";
        String[] args = new String[]{user.getUsername(), user.getPassword()};
        db.execSQL(sql, args);
        db.close();
    }

    /**
     * 根据用户名查询是否存在该用户
     */
    public static boolean isExistByUsername(String username) {
        SQLiteDatabase db = instance.getWritableDatabase();
        String sql = "SELECT * FROM user WHERE username=?";
        String[] args = new String[]{username};
        Cursor cursor = db.rawQuery(sql, args);
        boolean result = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return result;
    }

    /**
     * 根据用户名查询用户
     */
    public static User getUserByUsername(String username) {
        SQLiteDatabase db = instance.getWritableDatabase();
        String sql = "SELECT * FROM user WHERE username=?";
        String[] args = new String[]{username};
        Cursor cursor = db.rawQuery(sql, args);
        User user = null;
        if (cursor.moveToNext()) {
            user = new User();
            user.setId(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
        }
        cursor.close();
        db.close();
        return user;
    }

    /**
     * 创建数据库
     * 字段：_id,username,password
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE user(_id INTEGER PRIMARY KEY AUTOINCREMENT,username VARCHAR(20) ,password VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
