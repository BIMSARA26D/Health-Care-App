package com.example.health_care_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddExpenses extends AppCompatActivity {

    private EditText id, value, reason, date;
    private Button save;

    private ProgressDialog progressDialog;
    public DatabaseReference mDatabase;

    final Calendar myCalendar= Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Add Expenses");

        id = findViewById(R.id.txtId);
        value = findViewById(R.id.txtValue);
        reason = findViewById(R.id.txtReason);
        date = findViewById(R.id.txtDate);
        save = findViewById(R.id.btnSave);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading.....");
        progressDialog.setMessage("Please Wait!!");

        mDatabase = FirebaseDatabase.getInstance().getReference();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });


        loadDate();
    }

    private void validate(){

        String getId = id.getText().toString().trim();
        String getValue = value.getText().toString().trim();
        String getReason = reason.getText().toString().trim();
        String getDate = date.getText().toString().trim();

        if(getId.isEmpty()){
            id.setError("Please enter ID");
        }else if(getValue.isEmpty()){
            value.setError("Please enter value");
        }else if(getReason.isEmpty()){
            reason.setError("Please enter reason");
        }else if(getDate.isEmpty()){
            date.setError("Please enter date");
        }else{
            progressDialog.show();
            Expenses_Model income_model = new Expenses_Model(getId, getValue, getReason, getDate);
            String key = mDatabase.child("tblExpenses").push().getKey();
            mDatabase.child("tblExpenses").child(key).setValue(income_model).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    progressDialog.dismiss();
                    Toast.makeText(AddExpenses.this, "Successfully Saved!!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(Exception e) {
                    progressDialog.dismiss();
                    Log.d("Error", e.toString());
                    Toast.makeText(AddExpenses.this, "Fail try again.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void loadDate(){

        DatePickerDialog.OnDateSetListener date1 =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateNextChekUpDate();
            }
        };

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddExpenses.this,date1,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void updateNextChekUpDate(){
        String myFormat="MM/dd/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        date.setText(dateFormat.format(myCalendar.getTime()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}