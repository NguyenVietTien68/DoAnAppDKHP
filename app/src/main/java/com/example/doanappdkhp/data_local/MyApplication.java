package com.example.doanappdkhp.data_local;

import android.app.Application;

import com.example.doanappdkhp.data_local.DataLocalManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
    }
}
