package com.example.cardiacrecorder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cardiacrecorder.R;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView date,time,heartRate,systolic,diastolic;
        ImageButton deleteButton,editHeartRate,editSystolic,editDiastolic,editComment;
        Button backButton,saveButton;

        date = findViewById(R.id.dateForDetails);
        time = findViewById(R.id.timeDetails);
        heartRate = findViewById(R.id.heartRateForDetails);
        systolic = findViewById(R.id.systolicForDetails);
        diastolic = findViewById(R.id.diastolicForDetails);

        deleteButton = findViewById(R.id.deleteRecordButtonDetails);
        editHeartRate = findViewById(R.id.editHeartRateDetails);
        editSystolic = findViewById(R.id.editSystolicDetails);
        editDiastolic = findViewById(R.id.editDiastolicDetails);
        editComment = findViewById(R.id.imgButtonForCommentDetails);

        backButton = findViewById(R.id.backForDetails);
        saveButton = findViewById(R.id.saveForDetails);


    }
}