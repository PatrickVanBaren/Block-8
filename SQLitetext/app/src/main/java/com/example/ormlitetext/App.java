package com.example.ormlitetext;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseHelper.createInstance(this);
    }
}
