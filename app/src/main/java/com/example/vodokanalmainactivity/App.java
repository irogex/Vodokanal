package com.example.vodokanalmainactivity;

import android.app.Application;

import com.example.vodokanalmainactivity.data.preferences.DataPreferences;

public class App extends Application {
    public DataPreferences dataPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        dataPreferences = new DataPreferences(getSharedPreferences(Constants.PREFERNCES,MODE_PRIVATE));

    }
}
