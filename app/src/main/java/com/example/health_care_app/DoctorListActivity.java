package com.example.health_care_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.health_care_app.adapter.DoctorAdapter;
import com.example.health_care_app.db.DBHelper;
import com.example.health_care_app.model.Doctor;

import java.util.ArrayList;

public class DoctorListActivity extends AppCompatActivity {

    TextView tvTotal;
    RecyclerView recyclerView;
    DoctorAdapter doctorAdapter;
    ArrayList<Doctor> doctors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        recyclerView = findViewById(R.id.recyclerView);
        tvTotal = findViewById(R.id.tvTotal);

        DBHelper dbHelper = new DBHelper(this);

        doctors = dbHelper.getAllDoctors();
        tvTotal.setText("Total Doctors : " + doctors.size());

        doctorAdapter = new DoctorAdapter(doctors, this);
        recyclerView.setAdapter(doctorAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);

    }

}