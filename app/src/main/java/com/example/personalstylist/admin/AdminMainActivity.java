package com.example.personalstylist.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.personalstylist.MainActivity;
import com.example.personalstylist.R;

public class AdminMainActivity extends AppCompatActivity {
    SessionManager sessionManager;
    Button btnAddUser,btnLogut,btnViewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        btnAddUser= findViewById(R.id.btnAddUser);
        btnViewUser= findViewById(R.id.btnViewUser);
        btnLogut= findViewById(R.id.btnLogoutAdmin);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        btnViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminMainActivity.this, ViewUserActivity.class));

            }
        });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminMainActivity.this, AddUserActivity.class));

            }
        });
        btnLogut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logoutUser();
                finish();
            }
        });
    }
    protected void onDestroy() {
        super.onDestroy();
        sessionManager.logoutUser();

    }
}