package com.example.personalstylist.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.personalstylist.MainActivity;
import com.example.personalstylist.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserMainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    Button btnAdd,BtnViewM,btnlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        btnAdd= findViewById(R.id.btnAddMeasurement);
        BtnViewM= findViewById(R.id.btnViewMeasurement);
        btnlogout= findViewById(R.id.btnLogout_User);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(UserMainActivity.this, MainActivity.class));
            finish();
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserMainActivity.this, BtnAddMeasurementActivity.class));

            }
        });

        BtnViewM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserMainActivity.this,ViewMeasurementActivity.class));
            }
        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user != null) {
                    auth.signOut();
                    Toast.makeText(UserMainActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UserMainActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        auth.signOut();
    }
}