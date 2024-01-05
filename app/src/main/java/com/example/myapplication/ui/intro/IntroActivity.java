/*  GROUP 17
    Ngo Le Thien An - ITITDK21030
    Huynh Thanh Thuy - ITITIU21325
    Cao Hoang Khoi Nguyen - ITITDK21048
    Nguyen Dinh Thang - ITITIU21309
    Purpose: This Java class represents the introductory activity of an Android app, providing a button to navigate to the login screen.
*/
package com.example.myapplication.ui.intro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.myapplication.R;
import com.example.myapplication.ui.login.LoginActivity;
import com.github.javafaker.App;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        AppCompatButton getInBtn = (AppCompatButton) findViewById(R.id.getInBtn);
        getInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
            }
        });
    }
}