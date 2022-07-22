package com.example.cardiacrecorder.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.example.cardiacrecorder.Model.RecordModel;
import com.example.cardiacrecorder.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText Date;
    private TextInputEditText Time;
    private TextInputEditText HeartRate;
    private TextInputEditText Systolic;
    private TextInputEditText Diastolic;
    private TextInputEditText Comment;
    private Button AddButton;
    private ScrollView scrollView;

    private String userName,userId;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        Bundle b  = intent.getExtras();
        userName = b.get("UserName").toString();
        userId = b.get("UserId").toString();


        databaseReference = FirebaseDatabase.getInstance().getReference("CardiacRecords").child(userId);


        // Fetch User Input
        Date = findViewById(R.id.Date);
        Time = findViewById(R.id.Time);
        HeartRate = findViewById(R.id.Heartrate);
        Systolic = findViewById(R.id.Systolic);
        Diastolic = findViewById(R.id.Diastolic);
        Comment = findViewById(R.id.Comment);
        AddButton = findViewById(R.id.addRecordButton);
        scrollView = findViewById(R.id.scrollbar);


        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setHorizontalScrollBarEnabled(false);



        AddButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        addDetails();
    }

    private void addDetails() {

        String date = Date.getText().toString().trim();
        String times = Time.getText().toString().trim();
        String heartrate = HeartRate.getText().toString().trim();
        String systolic = Systolic.getText().toString().trim();
        String diastolic = Diastolic.getText().toString().trim();
        String comment = Comment.getText().toString().trim();

        if (date.isEmpty()) {
            Date.setError("Please enter the diagnosis date");
            Date.requestFocus();
        }
        if (times.isEmpty()) {
            Time.setError("Please enter the diagnosis time.");
            Time.requestFocus();
        }
        if (heartrate.isEmpty()) {
            HeartRate.setError("Please enter a heart rate");
            HeartRate.requestFocus();
        }
        if (systolic.isEmpty()) {
            Systolic.setError("Please enter a Systolic rate");
            Systolic.requestFocus();
        }
        if (diastolic.isEmpty())
        {
            Diastolic.setError("Please enter a diastolic rate");
            Diastolic.requestFocus();
        }
        if(comment.isEmpty())
        {
            Comment.setError("You should enter some comment about this diagnosis");
            Comment.requestFocus();
        }



        if(!date.isEmpty() && !times.isEmpty() && !heartrate.isEmpty() && !systolic.isEmpty() && !diastolic.isEmpty() && !comment.isEmpty())
        {
            Long tsLong = System.currentTimeMillis()/1000;
            String timestamp = tsLong.toString();

            RecordModel recordModel = new RecordModel(date,times,timestamp,heartrate,systolic,diastolic,comment);
            databaseReference.child(timestamp).setValue(recordModel);
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("UserName",userName);
            intent.putExtra("UserId",userId);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

            finish();
        }

    }
}