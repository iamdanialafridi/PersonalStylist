package com.example.personalstylist.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personalstylist.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserLoginActivity extends AppCompatActivity {
    EditText email,password;

    Button btnLogin;
    ProgressBar progressBar;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        email = findViewById(R.id.inputUserEmail_login);
        password = findViewById(R.id.inputUserPass_login);
        btnLogin = findViewById(R.id.btnLoginNowUser);
        progressBar = findViewById(R.id.userLoginProgressBar);
        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLogin.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                String Email = email.getText().toString().trim().toLowerCase();
                String Password = password.getText().toString().trim().toLowerCase();
                if (TextUtils.isEmpty(Email)) {
                    email.setError("Required");
                } else if (TextUtils.isEmpty(Password)) {
                    password.setError("Required");
                } else {
                    auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(UserLoginActivity.this, "logged in successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(UserLoginActivity.this, UserMainActivity.class));
                                        progressBar.setVisibility(View.GONE);
                                        finish();
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressBar.setVisibility(View.GONE);
                                    btnLogin.setVisibility(View.VISIBLE);

                                    Toast.makeText(UserLoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }
}