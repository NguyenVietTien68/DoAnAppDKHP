package com.example.doanappdkhp.data_local;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class MySharePreferrences {
    private static final String MY_SHARED_FREFERENCES = "MY_SHARED_FREFERENCES";
    private Context mContext;

    public MySharePreferrences(Context context) {
        this.mContext = context;
    }

    public void putString(String key, String value){
        SharedPreferences sharedPreferences =mContext.getSharedPreferences(MY_SHARED_FREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public String getString(String key){
        SharedPreferences sharedPreferences =mContext.getSharedPreferences(MY_SHARED_FREFERENCES, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

}
