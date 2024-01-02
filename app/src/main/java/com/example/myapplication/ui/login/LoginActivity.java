package com.example.myapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.database.user.User;
import com.example.myapplication.database.user.UserDatabase;
import com.example.myapplication.ui.MainActivity;
import com.example.myapplication.ui.register.RegisterActivity;
import com.example.myapplication.ui.profile.UserProfileFragment;

public class LoginActivity extends AppCompatActivity {
    private EditText userEdt, passEdt;
    private Button loginBtn;
    private Button registernow;

    private User loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        userEdt = findViewById(R.id.editTextText);
        passEdt = findViewById(R.id.editTextPassword);
        loginBtn = findViewById(R.id.loginBtn);
        registernow = findViewById(R.id.buttonRegister);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userEdt.getText().toString().isEmpty() || passEdt.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill in your username and password", Toast.LENGTH_SHORT).show();
                } else if (userEdt.getText().toString().equals("demo") && passEdt.getText().toString().equals("demo")) {
                    // Store the logged-in user information
                    loggedInUser = UserDatabase.getInstance().findUserByUsername(userEdt.getText().toString());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    String usernameToCheck = userEdt.getText().toString();
                    User foundUser = UserDatabase.getInstance().findUserByUsername(usernameToCheck);

                    if (foundUser != null && foundUser.getPassword().equals(passEdt.getText().toString())) {
                        // Store the logged-in user information
                        loggedInUser = foundUser;
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Your username or password is not correct", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    // Add the getLoggedInUser method to retrieve the logged-in user
    public User getLoggedInUser() {
        return loggedInUser;
    }
}
