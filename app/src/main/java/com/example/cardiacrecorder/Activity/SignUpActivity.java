package com.example.cardiacrecorder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cardiacrecorder.R;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    private TextInputEditText signUpEmail;
    private TextInputEditText signUpUserName;
    private TextInputEditText signUpPassword;
    private TextInputEditText signUpConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Fetch User Input
        signUpEmail = findViewById(R.id.signUpEmail);
        signUpUserName = findViewById(R.id.signUpUserName);
        signUpPassword = findViewById(R.id.signUpPassword);
        signUpConfirmPassword = findViewById(R.id.signUpConfirmPassword);











    }



}