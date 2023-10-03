package com.example.personalstylist.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.personalstylist.R;
import com.example.personalstylist.Util.FirebaseUtility;
import com.example.personalstylist.model.Users;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserUpdateProfileActivity extends AppCompatActivity {
    EditText name,email,phone,address,password;
    Button btnUpdate;

    DatabaseReference databaseReference;
    FirebaseUtility firebaseUtility;
    ProgressBar progressBar;
    FirebaseAuth auth;
    FirebaseUser user;
    String uid;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update_profile);
        name= findViewById(R.id.inputUserName_update);
        phone= findViewById(R.id.inputUserPhone_update);
        address= findViewById(R.id.inputUserAddr_update);
        password= findViewById(R.id.inputUserPassword_update);
        progressBar= findViewById(R.id.userRegisterProgressBar_update);
        btnUpdate= findViewById(R.id.btnUpdateUser);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        Intent intent= getIntent();
        if(intent != null){
            uid = intent.getStringExtra("USERID");



        firebaseUtility = new FirebaseUtility(this);
        if (user != null) {
            uid = Objects.requireNonNull(auth.getCurrentUser()).getUid();

            firebaseUtility.readSingleData("users", uid, new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Users users= snapshot.getValue(Users.class);
                    assert users != null;
                    name.setText(users.getName());
                    phone.setText(users.getPhone());
                    address.setText(users.getAddress());
                    password.setText(users.getPassword());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(UserUpdateProfileActivity.this, "Database Connection Error", Toast.LENGTH_SHORT).show();
                }
            });


            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressBar.setVisibility(View.VISIBLE);
                    btnUpdate.setVisibility(View.GONE);
                    String Name = name.getText().toString().trim();
                    String Phone = phone.getText().toString().trim();
                    String Address = address.getText().toString().trim();
                    String Password = password.getText().toString().trim().toLowerCase();
                    if (TextUtils.isEmpty(Name)) {
                        name.setError("Required");
                    }  else if (TextUtils.isEmpty(Phone)) {
                        phone.setError("Required");
                    } else if (TextUtils.isEmpty(Address)) {
                        address.setError("Required");
                    } else if (TextUtils.isEmpty(Password)) {
                        password.setError("Required");

                    } else {

                        user.updatePassword(Password).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){

                                            Map<String ,Object> updateMap = new HashMap<>();
                                            updateMap.put("name",Name);
                                            updateMap.put("phone",Phone);
                                            updateMap.put("address",Address);
                                            updateMap.put("password",Password);
                                            firebaseUtility.updateData("users",uid,updateMap, ViewUserActivity.class);
                                            auth.signOut();


                                        }
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        progressBar.setVisibility(View.GONE);
                                        btnUpdate.setVisibility(View.VISIBLE);
                                        Toast.makeText(UserUpdateProfileActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                }
            });

        }

    } else {
            finish();
        }
    }
}