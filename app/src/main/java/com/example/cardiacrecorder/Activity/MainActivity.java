package com.example.cardiacrecorder.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.cardiacrecorder.Adapter.RecyclerAdapter;
import com.example.cardiacrecorder.Model.RecordModel;
import com.example.cardiacrecorder.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageButton AddNewRecord;
    private String userName,userId;
    private List<RecordModel> recordList;
    DatabaseReference databaseReference;
    private RecyclerAdapter recyclerAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b!=null)
        {

            userName = b.get("UserName").toString();
            userId = b.get("UserId").toString();
        }
        else
        {
            userName = "User";
            userId = "-N7Wb6nnu7jtWJIfNjcc";
        }




        databaseReference = FirebaseDatabase.getInstance().getReference("CardiacRecords").child(userId);





        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AddNewRecord = findViewById(R.id.addNewRecord);



        recordList = new ArrayList<>();




        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                recordList.clear();

                for(DataSnapshot data : snapshot.getChildren())
                {
                    RecordModel record = data.getValue(RecordModel.class);
                    recordList.add(record);
                }

                Collections.reverse(recordList);
                recyclerAdapter = new RecyclerAdapter(recordList,MainActivity.this,userId,userName);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });









        AddNewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddDetailsActivity.class);
                intent.putExtra("UserName",userName);
                intent.putExtra("UserId",userId);

                startActivity(intent);

            }
        });


    }


}