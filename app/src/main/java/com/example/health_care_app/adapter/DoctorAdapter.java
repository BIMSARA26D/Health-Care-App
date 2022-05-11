package com.example.health_care_app.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.health_care_app.R;
import com.example.health_care_app.UpdateActivity;
import com.example.health_care_app.db.DBHelper;
import com.example.health_care_app.model.Doctor;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorVH> {

    ArrayList<Doctor> doctors;
    Context context;

    public DoctorAdapter(ArrayList<Doctor> doctors, Context context) {
        this.doctors = doctors;
        this.context = context;
    }

    @NonNull
    @Override
    public DoctorVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_doctor, parent,false);
        DoctorVH svh = new DoctorVH(view);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorVH holder, int position) {

        Doctor s = doctors.get(position);
        holder.tvFname.setText(s.getFname());
        holder.tvFname2.setText(s.getFname());
        holder.tvLname.setText(s.getLname());
        holder.tvEmail.setText(s.getEmail());
        holder.tvPnumber.setText(s.getPnumber());
        holder.tvLnumber.setText(s.getLnumber());

        holder.cardUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, s.getFname() + " will be updated", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("DOCTOR", s);
                context.startActivity(intent);
            }
        });

        //delete
        holder.cardDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, s.getFname() + " will be deleted", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Confirmation !!!");
                builder.setMessage("Are You Sure To Delete  " + s.getFname() + " ?");
                builder.setIcon(android.R.drawable.ic_menu_delete);
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        DBHelper dbHelper = new DBHelper(context);

                        int result = dbHelper.deleteStudent(s.getId());

                        if( result > 0)
                        {
                            Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();
                            doctors.remove(s);
                            notifyDataSetChanged();
                        }
                        else
                        {
                            Toast.makeText(context, "Failled", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });
    }

    //view
    @Override
    public int getItemCount() {
        return doctors.size();
    }

    class DoctorVH extends RecyclerView.ViewHolder {

        TextView tvFname,tvFname2 , tvLname, tvEmail,tvPnumber, tvLnumber;
        CardView cardUpdate, cardDelete;

        public DoctorVH(@NonNull View v) {
            super(v);

            tvFname = v.findViewById(R.id.tvFname);
            tvFname2 = v.findViewById(R.id.tvFname2);
            tvLname = v.findViewById(R.id.tvLname);
            tvEmail = v.findViewById(R.id.tvEmail);
            tvPnumber = v.findViewById(R.id.tvPnumber);
            tvLnumber = v.findViewById(R.id.tvLnumber);

            cardDelete = v.findViewById(R.id.cardDelete);
            cardUpdate = v.findViewById(R.id.cardUpdate);
        }
    }



}
