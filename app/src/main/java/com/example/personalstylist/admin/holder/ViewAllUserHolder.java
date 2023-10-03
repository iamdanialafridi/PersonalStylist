package com.example.personalstylist.admin.holder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalstylist.R;
import com.example.personalstylist.Util.FirebaseUtility;
import com.example.personalstylist.admin.UserUpdateProfileActivity;
import com.example.personalstylist.admin.ViewUserActivity;
import com.example.personalstylist.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.ArrayList;
import java.util.List;

public class ViewAllUserHolder extends RecyclerView.Adapter<ViewAllUserHolder.ViewUserHolder>  {
    List<Users> usersList;

    Context context;

    public ViewAllUserHolder(List<Users> usersList) {
        this.usersList = usersList;


    }

    @NonNull
    @Override
    public ViewUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.user_row,parent,false);
        return new ViewUserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewUserHolder holder, int position) {
        Users users= usersList.get(position);
        holder.name.setText(users.getName());
        holder.email.setText(users.getEmail());
        holder.phone.setText(users.getPhone());
        holder.addr.setText(users.getAddress());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }



    class ViewUserHolder extends RecyclerView.ViewHolder {
        TextView name,email,phone,addr;
        Button btndel,btnupdate;

        public ViewUserHolder(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.txtUserName);
            email= itemView.findViewById(R.id.txtUserEmail);
            phone= itemView.findViewById(R.id.txtUserPhone);
            addr= itemView.findViewById(R.id.txtUserAddr);
            btnupdate= itemView.findViewById(R.id.btnUpdateUser);
            btndel= itemView.findViewById(R.id.btnUserDelete);
            btndel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    int pos = getAdapterPosition();
                    String _id = usersList.get(pos).getCid();
                    String Email = usersList.get(pos).getEmail().trim().toLowerCase();
                    String Password = usersList.get(pos).getPassword().trim().toLowerCase();
                    FirebaseUtility firebaseUtility = new FirebaseUtility(context);
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        FirebaseUser user = auth.getCurrentUser();
                                        if(user !=null){
                                            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if(task.isSuccessful()){
                                                                firebaseUtility.deleteData("users",_id, ViewUserActivity.class);
                                                                auth.signOut();
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
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });


                }
            });
            btnupdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
int pos = getAdapterPosition();
String id = usersList.get(pos).getCid();
                    Intent intent= new Intent(context, UserUpdateProfileActivity.class);
                    intent.putExtra("USERID",id);
                    context.startActivity(intent);

                }
            });
        }
    }
}
