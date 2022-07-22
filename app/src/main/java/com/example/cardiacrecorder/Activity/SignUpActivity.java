package com.example.cardiacrecorder.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.cardiacrecorder.Model.UserSignUpModel;
import com.example.cardiacrecorder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText signUpEmail;
    private TextInputEditText signUpUserName;
    private TextInputEditText signUpPassword;
    private TextInputEditText signUpConfirmPassword;
    private Button buttonSignUp;
    private ScrollView scrollView;


    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);





        mAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("UserInfo");



        // Fetch User Input
        signUpEmail = findViewById(R.id.signUpEmail);
        signUpUserName = findViewById(R.id.signUpUserName);
        signUpPassword = findViewById(R.id.signUpPassword);
        signUpConfirmPassword = findViewById(R.id.signUpConfirmPassword);
        buttonSignUp = findViewById(R.id.signupButton);
        scrollView = findViewById(R.id.scrollbar);
        progressBar = findViewById(R.id.progressbar);

        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setHorizontalScrollBarEnabled(false);


        buttonSignUp.setOnClickListener(this);
        progressBar.setVisibility(View.GONE);


    }


    @Override
    public void onClick(View v) {
        userRegister();
    }


    private void userRegister() {
        String email = signUpEmail.getText().toString().trim();
        String userName = signUpUserName.getText().toString().trim();
        String password = signUpPassword.getText().toString().trim();
        String confirmPass = signUpConfirmPassword.getText().toString().trim();




        if(email.isEmpty())
        {
            signUpEmail.setError("Enter an Email Address");
            signUpEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            signUpEmail.setError("Enter a valid Email Address");
            signUpEmail.requestFocus();
            return;
        }
        if(userName.isEmpty())
        {
            signUpUserName.setError("Enter your name");
            signUpUserName.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            signUpPassword.setError("Enter a password");
            signUpPassword.requestFocus();
            return;
        }

        if(password.length() < 6)
        {
            signUpPassword.setError("Minimum length of a password should be 6");
            signUpPassword.requestFocus();
            return;
        }

        if(confirmPass.isEmpty())
        {
            signUpConfirmPassword.setError("Enter a password");
            signUpConfirmPassword.requestFocus();
            return;
        }

        if(!password.contains(confirmPass))
        {
            signUpConfirmPassword.setError("Password is not matched");
            signUpConfirmPassword.requestFocus();
            return;
        }





                if(!userName.isEmpty() && !email.isEmpty() && !password.isEmpty() && password.contains(confirmPass) && !confirmPass.isEmpty())
                {
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                progressBar.setVisibility(View.GONE);
                                String userKey = databaseReference.push().getKey();
                                UserSignUpModel userSignUpModel = new UserSignUpModel(email,userName,password,userKey);
                                databaseReference.child(userKey).setValue(userSignUpModel);



                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                intent.putExtra("UserId",userKey);
                                intent.putExtra("UserName",userName);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);

                                finish();



                            }
                            else {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {

                                    finish();
                                    Intent intent = new Intent(getApplicationContext(), InitialPage.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(SignUpActivity.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }






    }



}