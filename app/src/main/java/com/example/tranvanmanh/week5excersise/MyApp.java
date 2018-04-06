package com.example.tranvanmanh.week5excersise;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by tranvanmanh on 4/5/2018.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
