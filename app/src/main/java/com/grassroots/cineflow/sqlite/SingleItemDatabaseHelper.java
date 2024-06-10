package com.grassroots.cineflow.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SingleItemDatabaseHelper extends SQLiteOpenHelper {

    /**
     * 单例
     */
    private static SingleItemDatabaseHelper instance;

    private SingleItemDatabaseHelper(Context context) {
        super(context, "single_item.db", null, 1);
    }

    /**
     * 获取单例
     *
     * @param context
     * @return
     */
    public static void init(Context context) {
        if (instance == null) {
            instance = new SingleItemDatabaseHelper(context);
        }
    }

    /**
     * 插入
     */
    public static void insert(SingleItem item) {
        SQLiteDatabase db = instance.getWritableDatabase();
        String sql = "INSERT INTO single_item(userId,type,title,score,imageId,content1,content2,price) VALUES(?,?,?,?,?,?,?,?)";
        String[] args = new String[]{item.getUserId().toString(), item.getType().toString(), item.getTitle(), item.getScore(), item.getImageId().toString(), item.getContent1(), item.getContent2(), item.getPrice()};
        db.execSQL(sql, args);
        db.close();
    }

    /**
     * 根据用户id查询该用户的所有电影
     */
    public static List<SingleItem> getSingleItemsByUserId(Integer userId, Integer type) {
        SQLiteDatabase db = instance.getWritableDatabase();
        String sql = "SELECT * FROM single_item WHERE userId=? AND type=?";
        String[] args = new String[]{userId.toString(), type.toString()};
        Cursor cursor = db.rawQuery(sql, args);
        List<SingleItem> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            SingleItem item = new SingleItem();
            item.setId(cursor.getInt(0));
            item.setUserId(cursor.getInt(1));
            item.setType(cursor.getInt(2));
            item.setTitle(cursor.getString(3));
            item.setScore(cursor.getString(4));
            item.setImageId(cursor.getInt(5));
            item.setContent1(cursor.getString(6));
            item.setContent2(cursor.getString(7));
            item.setPrice(cursor.getString(8));
            result.add(item);
        }
        cursor.close();
        db.close();
        return result;
    }

    /**
     * 根据id删除
     */
    public static void deleteById(Integer id) {
        SQLiteDatabase db = instance.getWritableDatabase();
        String sql = "DELETE FROM single_item WHERE _id=?";
        String[] args = new String[]{id.toString()};
        db.execSQL(sql, args);
        db.close();
    }


    /**
     * 创建数据库
     * 字段：_id,userId,type,title,score,imageId,content1,content2,price
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE single_item(_id INTEGER PRIMARY KEY AUTOINCREMENT,userId INTEGER,type INTEGER,title VARCHAR(20),score VARCHAR(20),imageId INTEGER,content1 VARCHAR(20),content2 VARCHAR(20),price VARCHAR(20))";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
