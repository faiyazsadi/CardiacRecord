package com.example.cardiacrecorder.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cardiacrecorder.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    TextView date,time;
    EditText heartRate,systolic,diastolic,commentDetails;
    Button deleteButton,saveButton;


    DatabaseReference databaseReference;

    private String Date,Time,HeartRate,Systolic,Diastolic,Comment,userId,recordId,userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);






        Intent intent = getIntent();
        Bundle b = intent.getExtras();


        Date = b.get("Date").toString();
        Time = b.get("Time").toString();
        HeartRate = b.get("Heartrate").toString();
        Systolic = b.get("Systolic").toString();
        Diastolic = b.get("Diastolic").toString();
        Comment = b.get("Comment").toString();
        userId = b.get("UserId").toString();
        recordId = b.get("recordId").toString();
        userName = b.get("UserName").toString();




        databaseReference = FirebaseDatabase.getInstance().getReference("CardiacRecords").child(userId).child(recordId);





        date = findViewById(R.id.dateForDetails);
        time = findViewById(R.id.timeDetails);
        heartRate = findViewById(R.id.heartRateForDetails);
        systolic = findViewById(R.id.systolicForDetails);
        diastolic = findViewById(R.id.diastolicForDetails);
        commentDetails = findViewById(R.id.CommentDetails);

        deleteButton = findViewById(R.id.deleteRecordButtonDetails);

        saveButton = findViewById(R.id.saveForDetails);






        date.setText(Date);
        time.setText(Time);
        heartRate.setText(HeartRate);
        systolic.setText(Systolic);
        diastolic.setText(Diastolic);
        commentDetails.setText(Comment);


        int HeartInt = Integer.parseInt(HeartRate);
        int SystoInt = Integer.parseInt(Systolic);
        int DiastoInt = Integer.parseInt(Diastolic);



        if(HeartInt >= 70 && HeartInt <= 80)
        {
            heartRate.setTextColor(Color.parseColor("#92f10d"));
        }
        else
        {
            heartRate.setTextColor(Color.RED);
        }

        if (SystoInt >=100 && SystoInt <=120)
        {
           systolic.setTextColor(Color.parseColor("#92f10d"));
        }
        else if(SystoInt >120 && SystoInt <= 129)
        {

            systolic.setTextColor(Color.parseColor("#febd46"));
        }
        else if(SystoInt >= 130 && SystoInt <= 140)
        {

            systolic.setTextColor(Color.parseColor("#f99f2c"));
        }
        else
        {
            systolic.setTextColor(Color.RED);
        }


        if(DiastoInt >= 70 && DiastoInt <= 80)
        {
            diastolic.setTextColor(Color.parseColor("#92f10d"));
        }
        else if(DiastoInt > 80 && DiastoInt <= 89)
        {
            diastolic.setTextColor(Color.parseColor("#f99f2c"));
        }
        else
        {
            diastolic.setTextColor(Color.RED);
        }









        saveButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.deleteRecordButtonDetails:

                databaseReference.removeValue();

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("UserName",userName);
                intent.putExtra("UserId",userId);
                startActivity(intent);
                finish();
                break;
            case R.id.saveForDetails:
                EditDetails();
                break;
        }
    }

    private void EditDetails() {
        String Heart = heartRate.getText().toString().trim();
        String Sys = systolic.getText().toString().trim();
        String Dias = diastolic.getText().toString().trim();
        String Cmt = commentDetails.getText().toString().trim();

        Map<String,Object> map = new HashMap<>();
        map.put("heartrate",Heart);
        map.put("systolic",Sys);
        map.put("diastolic",Dias);
        map.put("comment",Cmt);

        databaseReference.updateChildren(map);

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("UserName",userName);
        intent.putExtra("UserId",userId);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        finish();


    }
}