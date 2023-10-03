package com.example.personalstylist.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.personalstylist.MainActivity;
import com.example.personalstylist.R;
import com.example.personalstylist.Util.FirebaseUtility;
import com.example.personalstylist.admin.ViewUserActivity;
import com.example.personalstylist.model.Measurements;
import com.example.personalstylist.model.Users;
import com.example.personalstylist.user.holder.ViewAllMeasurementHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewMeasurementActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseUtility firebaseUtility;

    List<Measurements> measurementsList = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_measurement);
        recyclerView=findViewById(R.id.meansurementRv);
        firebaseUtility= new FirebaseUtility(this);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(ViewMeasurementActivity.this, MainActivity.class));
            finish();
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        firebaseUtility.readWholeData("measurement", new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                measurementsList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Measurements measurements = dataSnapshot.getValue(Measurements.class);
                    assert measurements != null;
                    if(user.getUid().equals(measurements.getUid())){
                       measurementsList.add(measurements);
                   }
                }
                ViewAllMeasurementHolder viewAllMeasurementHolder= new ViewAllMeasurementHolder(measurementsList);
                recyclerView.setAdapter(viewAllMeasurementHolder);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewMeasurementActivity.this, "Database Error", Toast.LENGTH_SHORT).show();

            }
        });

    }
}