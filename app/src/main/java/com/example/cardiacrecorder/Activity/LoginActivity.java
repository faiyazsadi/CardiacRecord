package com.example.cardiacrecorder.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.cardiacrecorder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextInputEditText loginEmail, loginPassword;
    private Button loginButton;
    private String  userName,userId;
    private ScrollView scrollView;
    private ProgressBar progressBar;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("UserInfo");

        // Fetch User Input
        loginEmail = findViewById(R.id.LoginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.LoginButton);
        scrollView = findViewById(R.id.scrollbar);
        progressBar = findViewById(R.id.progressbar);

        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setHorizontalScrollBarEnabled(false);
        progressBar.setVisibility(View.GONE);


        loginButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        SignUpF();
    }

    private void SignUpF() {

        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();


        if(email.isEmpty())
        {
            loginEmail.setError("Enter an Email Address");
            loginEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            loginEmail.setError("Enter a valid Email Address");
            loginEmail.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            loginPassword.setError("Enter a password");
            loginPassword.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


               if(task.isSuccessful())
               {

                   progressBar.setVisibility(View.GONE);

                   databaseReference.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {


                           for(DataSnapshot data : snapshot.getChildren())
                           {
                               if(data.child("email").getValue().toString().equals(email))
                               {
                                   userName = data.child("userName").getValue().toString();
                                   userId = data.child("userKey").getValue().toString();

                                   Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                   intent.putExtra("UserName",userName);
                                   intent.putExtra("UserId",userId);
                                   intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                   startActivity(intent);
                                   finish();
                               }
                           }



                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }
                   });
               }
               else
               {
                   Toast.makeText(LoginActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
               }


            }
        });


    }
}