package com.example.myapplication.ui.profile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class ViewProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_userprofile);

        // Check if the activity is created for the first time
        if (savedInstanceState == null) {
            // Add UserProfileFragment to the container
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new UserProfileFragment())
                    .commit();
        }
    }
}