package com.example.personalstylist.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.personalstylist.MainActivity;
import com.example.personalstylist.R;
import com.example.personalstylist.Util.FirebaseUtility;
import com.example.personalstylist.admin.AdminMainActivity;
import com.example.personalstylist.model.Measurements;
import com.example.personalstylist.model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class BtnAddMeasurementActivity extends AppCompatActivity {
    EditText neckwidth, shoulderlength, armlength,  armwidth, shirtwidth, shirtlength, paintlength ;
    Button btnRegister;

    DatabaseReference databaseReference;
    FirebaseUtility firebaseUtility;
    ProgressBar progressBar;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn_add_measurement);
        neckwidth = findViewById(R.id.inputUserNeckWidth);
        shoulderlength = findViewById(R.id.inputUserShoulderlength);
        armlength = findViewById(R.id.inputUserArmlength);
        armwidth = findViewById(R.id.inputUserArmwidth);
        shirtwidth = findViewById(R.id.inputUserShirtwidth);
        shirtlength = findViewById(R.id.inputUserSshirtlength);
        paintlength = findViewById(R.id.inputUserPaintlength);
        progressBar= findViewById(R.id.progressMeasure);
        btnRegister= findViewById(R.id.btnRegMeasurement);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(BtnAddMeasurementActivity.this, MainActivity.class));
            finish();
        }
        firebaseUtility = new FirebaseUtility(this);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("measurement");
btnRegister.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        progressBar.setVisibility(View.VISIBLE);
        btnRegister.setVisibility(View.GONE);
        String Neckwidth = neckwidth.getText().toString().trim();
        String Shoulderlength = shoulderlength.getText().toString().trim().toLowerCase();
        String Armlength = armlength.getText().toString().trim();
        String Armwidth = armwidth.getText().toString().trim();
        String Shirtwidth = shirtwidth.getText().toString().trim().toLowerCase();
        String Shirtlength = shirtlength.getText().toString().trim().toLowerCase();
        String Paintlength = paintlength.getText().toString().trim().toLowerCase();
        if (TextUtils.isEmpty(Neckwidth)) {
            neckwidth.setError("Required");
        } else if (TextUtils.isEmpty(Shoulderlength)) {
            shoulderlength.setError("Required");
        } else if (TextUtils.isEmpty(Armlength)) {
            armlength.setError("Required");
        } else if (TextUtils.isEmpty(Armwidth)) {
            armwidth.setError("Required");
        } else if (TextUtils.isEmpty(Shirtwidth)) {
            shirtwidth.setError("Required");

        } else if (TextUtils.isEmpty(Shirtlength)) {
            shirtlength.setError("Required");

        } else if (TextUtils.isEmpty(Paintlength)) {
            paintlength.setError("Required");

        } else {
            String key = databaseReference.push().getKey();
            Measurements measurements = new Measurements(key,user.getUid(),Neckwidth,Shoulderlength,Armlength,Armwidth,Shirtwidth,Shirtlength,Paintlength);
            firebaseUtility.createData("measurement",key,measurements, ViewMeasurementActivity.class);
            btnRegister.setVisibility(View.VISIBLE);

            progressBar.setVisibility(View.GONE);

        }
    }
});
    }
}