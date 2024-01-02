package com.example.myapplication.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.database.user.User;
import com.example.myapplication.database.user.UserDatabase;
import com.example.myapplication.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText userEdt, passEdt, emailEdt, phoneEdt;
    private Button registerBtn;
    private UserDatabase userDatabase;

    private int countUser = 0;

    private User newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userDatabase = userDatabase.getInstance();

        initView();
    }

    private void initView() {
        emailEdt = findViewById(R.id.editTextEmailAddress);
        phoneEdt = findViewById(R.id.editTextPhone);
        userEdt = findViewById(R.id.registerText);
        passEdt = findViewById(R.id.registerPassword);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countUser++;
                if (userEdt.getText().toString().isEmpty() || passEdt.getText().toString().isEmpty()
                        || emailEdt.getText().toString().isEmpty() || phoneEdt.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please fill your email, phone, username and password", Toast.LENGTH_SHORT).show();
                } else {
                    newUser = new User(countUser, emailEdt.getText().toString(), phoneEdt.getText().toString(),
                            userEdt.getText().toString(), passEdt.getText().toString());
                    userDatabase.getInstance().addUser(newUser);
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
            }
        });
    }
}
