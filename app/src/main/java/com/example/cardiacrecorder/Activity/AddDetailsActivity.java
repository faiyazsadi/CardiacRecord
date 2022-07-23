package com.example.cardiacrecorder.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.cardiacrecorder.Model.RecordModel;
import com.example.cardiacrecorder.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText Date;
    private TextInputEditText Time;
    private TextInputEditText HeartRate;
    private TextInputEditText Systolic;
    private TextInputEditText Diastolic;
    private TextInputEditText Comment;
    private Button AddButton;
    private ScrollView scrollView;
    private ProgressBar progressBar;
    private String userName,userId;
    private int mYear, mMonth, mDay;
    DatabaseReference databaseReference;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;
    TimePickerDialog timePickerDialog;
    final Calendar myCalendar= Calendar.getInstance();

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
        progressBar = findViewById(R.id.progressbar);

        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setHorizontalScrollBarEnabled(false);

        progressBar.setVisibility(View.GONE);






        String dt, timef;
        java.util.Date cal = (Date) Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeformate = new SimpleDateFormat("hh:mm a");
        dt = sdf.format(cal);
        timef = timeformate.format(cal);
        Date.setText(dt);
        Time.setText(timef);


        AddButton.setOnClickListener(this);
        Date.setOnClickListener(this);
        Time.setOnClickListener(this);





    }







    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addRecordButton)
        {
            addDetails();
        }
        else if(v.getId() == R.id.Date)
        {
            final Calendar calendar = Calendar.getInstance ();
            mYear = calendar.get ( Calendar.YEAR );
            mMonth = calendar.get ( Calendar.MONTH );
            mDay = calendar.get ( Calendar.DAY_OF_MONTH );

            //show dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog ( this, new DatePickerDialog.OnDateSetListener () {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Date.setText ( dayOfMonth + "/" + (month + 1) + "/" + year );
                }
            }, mYear, mMonth, mDay );
            datePickerDialog.show ();
        }
        else if(v.getId() == R.id.Time)
        {
            calendar = Calendar.getInstance();
            currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            currentMinute = calendar.get(Calendar.MINUTE);

            timePickerDialog = new TimePickerDialog(AddDetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                    if (hourOfDay >= 12) {
                        amPm = "PM";
                    } else {
                        amPm = "AM";
                    }
                    Time.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                }
            }, currentHour, currentMinute, false);

            timePickerDialog.show();
        }



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
            progressBar.setVisibility(View.VISIBLE);

            Long tsLong = System.currentTimeMillis()/1000;
            String timestamp = tsLong.toString();

            RecordModel recordModel = new RecordModel(date,times,timestamp,heartrate,systolic,diastolic,comment);
            databaseReference.child(timestamp).setValue(recordModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                    progressBar.setVisibility(View.GONE);





                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("UserName",userName);
                    intent.putExtra("UserId",userId);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                    finish();




                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddDetailsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
}