package com.grassroots.cineflow;

import android.app.Application;

import com.grassroots.cineflow.sqlite.SingleItemDatabaseHelper;
import com.grassroots.cineflow.sqlite.UserDatabaseHelper;
import com.grassroots.cineflow.utils.CurrentUserUtils;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UserDatabaseHelper.init(this);
        SingleItemDatabaseHelper.init(this);
        CurrentUserUtils.init(this);
    }
}
