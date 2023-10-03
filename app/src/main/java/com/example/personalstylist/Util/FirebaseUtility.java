package com.example.personalstylist.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public  class FirebaseUtility {

private DatabaseReference databaseReference;

private Context context;

     public FirebaseUtility(Context context
) {
         this.context=context;
         // Initialize the database reference
         FirebaseDatabase database = FirebaseDatabase.getInstance();
         databaseReference = database.getReference();
     }

    // Create operation
    public void createData(String path,String key, Object data,Class<?> targetActivityClass) {

        assert key != null;
        databaseReference.child(path).child(key).setValue(data).addOnSuccessListener(aVoid -> {
                    Toast.makeText(context, "Information Saved successfully", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context,targetActivityClass));
                    ((Activity) context).finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Failed to save information"+e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    // Read single operation
    public void readSingleData(String path,String key, ValueEventListener valueEventListener) {
        databaseReference.child(path).child(key).addListenerForSingleValueEvent(valueEventListener);
    }

    public void readWholeData(String path, ValueEventListener valueEventListener) {
        databaseReference.child(path).addValueEventListener(valueEventListener);
    }


    //updatedata
    public void updateData(String path,String key,Map<String, Object> updateMap,Class<?> targetActivityClass) {
        databaseReference.child(path).child(key).updateChildren(updateMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(context, "Information updated", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context,targetActivityClass));

                    ((Activity)context).finish();

                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    // Delete operation
    public void deleteData(String path,String key,Class<?> targetActivityClass) {
        databaseReference.child(path).child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context,targetActivityClass));
                    ((Activity) context).finish();
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Unable to delete"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
 }
