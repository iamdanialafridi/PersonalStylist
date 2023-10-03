package com.example.personalstylist.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.personalstylist.R;

public class AdminLoginActivity extends AppCompatActivity {
    EditText email,password;
    SessionManager sessionManager;
    private final static String  AdminEmail = "admin@gmail.com";
    private final static String  AdminPassword = "admin123456";
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        email = findViewById(R.id.inputAdminEmail);
        password = findViewById(R.id.inputAdminPassword);
        btnLogin = findViewById(R.id.btnLoginAdmin);
        sessionManager = new SessionManager(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString().trim().toLowerCase();
                String Password = password.getText().toString().trim().toLowerCase();
                if(TextUtils.isEmpty(Email)){
                    email.setError("Required");
                } else if(TextUtils.isEmpty(Password)){
                    password.setError("Required");
                } else {
                    if(Email.equals(AdminEmail) && Password.equals(AdminPassword)){
                        sessionManager.createLoginSession(Email,Password);
                        Toast.makeText(AdminLoginActivity.this, "Admin Logged Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AdminLoginActivity.this,AdminMainActivity.class));
                    } else {
                        Toast.makeText(AdminLoginActivity.this, "wrong email/password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}