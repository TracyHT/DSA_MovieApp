// MyApplication.java
package com.example.myapplication;

import android.app.Application;

public class MyApplication extends Application {
    // No need to manually initialize Glide in newer versions

    @Override
    public void onCreate() {
        super.onCreate();
        // Your other application-level initialization code, if any
    }
}
