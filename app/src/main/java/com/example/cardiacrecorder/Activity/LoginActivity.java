package com.example.cardiacrecorder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cardiacrecorder.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText LoginEmail, loginPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Fetch User Input
        LoginEmail = findViewById(R.id.LoginEmail);
        loginPassword = findViewById(R.id.loginPassword);
    }
}