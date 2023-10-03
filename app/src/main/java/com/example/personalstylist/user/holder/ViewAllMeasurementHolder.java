package com.example.personalstylist.user.holder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalstylist.R;
import com.example.personalstylist.Util.FirebaseUtility;
import com.example.personalstylist.admin.UserUpdateProfileActivity;
import com.example.personalstylist.admin.ViewUserActivity;
import com.example.personalstylist.model.Measurements;
import com.example.personalstylist.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class ViewAllMeasurementHolder extends RecyclerView.Adapter<ViewAllMeasurementHolder.ViewMeasureHolder>  {
    List<Measurements> measurementsList;

    Context context;

    public ViewAllMeasurementHolder(List<Measurements> measurementsList) {
        this.measurementsList = measurementsList;


    }

    @NonNull
    @Override
    public ViewMeasureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.measure_row,parent,false);
        return new ViewMeasureHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewMeasureHolder holder, int position) {
        Measurements measurements= measurementsList.get(position);
        holder.neckwidth.setText(measurements.getNeckwidth());
        holder.shoulderlength.setText(measurements.getShoulderlength());
        holder.armlength.setText(measurements.getArmlength());
        holder.armwidth.setText(measurements.getArmwidth());
        holder.shirtlength.setText(measurements.getShirtlength());
        holder.shirtwidth.setText(measurements.getShirtwidth());
        holder.paintlength.setText(measurements.getPaintlength());
    }

    @Override
    public int getItemCount() {
        return measurementsList.size();
    }



    class ViewMeasureHolder extends RecyclerView.ViewHolder {
        TextView neckwidth, shoulderlength, armlength,  armwidth, shirtwidth, shirtlength, paintlength ;


        public ViewMeasureHolder(@NonNull View itemView) {
            super(itemView);
            neckwidth= itemView.findViewById(R.id.txtUserNeckwidth);
            shoulderlength= itemView.findViewById(R.id.txtUserShoulderlength);
            armlength= itemView.findViewById(R.id.txtUserArmlength);
            armwidth= itemView.findViewById(R.id.txtUserArmwidth);
            shirtwidth= itemView.findViewById(R.id.txtUserShirtwidth);
            shirtlength= itemView.findViewById(R.id.txtUserShirtLength);
            paintlength= itemView.findViewById(R.id.txtUserPaintLength);

        }
    }
}
