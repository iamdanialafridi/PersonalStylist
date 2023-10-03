package com.example.personalstylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.personalstylist.admin.AdminLoginActivity;
import com.example.personalstylist.user.UserLoginActivity;

public class MainActivity extends AppCompatActivity {
Button btnAdmin,btnUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdmin= findViewById(R.id.btnAdmin);
        btnUser= findViewById(R.id.btnUser);
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AdminLoginActivity.class));
            }
        });
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserLoginActivity.class));
            }
        });
    }
}