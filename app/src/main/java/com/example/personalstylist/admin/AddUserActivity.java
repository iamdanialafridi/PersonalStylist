package com.example.personalstylist.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personalstylist.R;
import com.example.personalstylist.Util.FirebaseUtility;
import com.example.personalstylist.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class AddUserActivity extends AppCompatActivity {
    EditText name,email,phone,address,password;
    Button btnRegister;

    DatabaseReference databaseReference;
    FirebaseUtility firebaseUtility;
    ProgressBar progressBar;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        name= findViewById(R.id.inputUserName);
        email= findViewById(R.id.inputUserEmail);
        phone= findViewById(R.id.inputUserPhone);
        address= findViewById(R.id.inputUserAddr);
        password= findViewById(R.id.inputUserPassword);
        progressBar= findViewById(R.id.userRegisterProgressBar);
        btnRegister= findViewById(R.id.btnRegisterUser);

        firebaseUtility = new FirebaseUtility(this);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        auth= FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                btnRegister.setVisibility(View.GONE);
                String Name = name.getText().toString().trim();
                String Email = email.getText().toString().trim().toLowerCase();
                String Phone = phone.getText().toString().trim();
                String Address = address.getText().toString().trim();
                String Password = password.getText().toString().trim().toLowerCase();
                if (TextUtils.isEmpty(Name)) {
                    name.setError("Required");
                } else if (TextUtils.isEmpty(Email)) {
                    email.setError("Required");
                } else if (TextUtils.isEmpty(Phone)) {
                    phone.setError("Required");
                } else if (TextUtils.isEmpty(Address)) {
                    address.setError("Required");
                } else if (TextUtils.isEmpty(Password)) {
                    password.setError("Required");

                } else {

                    auth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        String uid = Objects.requireNonNull(auth.getCurrentUser()).getUid();
                                        Users users = new Users(uid,Name,Email,Phone,Address,Password);
                                        firebaseUtility.createData("users",uid,users,AdminMainActivity.class);
                                        btnRegister.setVisibility(View.VISIBLE);

                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AddUserActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                    btnRegister.setVisibility(View.VISIBLE);
                                }
                            });
                }
            }
        });
    }
}