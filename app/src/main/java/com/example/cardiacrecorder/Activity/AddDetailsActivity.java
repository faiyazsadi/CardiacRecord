package com.example.cardiacrecorder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cardiacrecorder.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddDetailsActivity extends AppCompatActivity {

    private TextInputEditText date;
    private TextInputEditText time;
    private TextInputEditText heartRate;
    private TextInputEditText systolic;
    private TextInputEditText diastolic;
    private TextInputEditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        // Fetch User Input
        date = findViewById(R.id.Date);
        time = findViewById(R.id.Time);
        heartRate = findViewById(R.id.Heartrate);
        systolic = findViewById(R.id.Systolic);
        diastolic = findViewById(R.id.Diastolic);
        comment = findViewById(R.id.Comment);
    }
}