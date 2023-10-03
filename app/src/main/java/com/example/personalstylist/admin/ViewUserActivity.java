package com.example.personalstylist.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.personalstylist.R;
import com.example.personalstylist.Util.FirebaseUtility;
import com.example.personalstylist.admin.holder.ViewAllUserHolder;
import com.example.personalstylist.model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewUserActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseUtility firebaseUtility;
    SearchView searchView;
    List<Users> usersList = new ArrayList<>();
    ViewAllUserHolder viewAllUserHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        recyclerView= findViewById(R.id.viewUserRv);
        firebaseUtility= new FirebaseUtility(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        firebaseUtility.readWholeData("users", new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usersList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Users users = dataSnapshot.getValue(Users.class);
                    usersList.add(users);
                }
                viewAllUserHolder = new ViewAllUserHolder(usersList);
                recyclerView.setAdapter(viewAllUserHolder);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewUserActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}