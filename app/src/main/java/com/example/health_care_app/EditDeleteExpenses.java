package com.example.health_care_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditDeleteExpenses extends AppCompatActivity {

    private EditText id, value, reason, date;
    private Button save, delete;

    private ProgressDialog progressDialog;
    public DatabaseReference mDatabase;

    final Calendar myCalendar= Calendar.getInstance();

    private String getKey, getId, getValue, getReason, getDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_expenses);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Edit / Delete Expenses");

        id = findViewById(R.id.txtId);
        value = findViewById(R.id.txtValue);
        reason = findViewById(R.id.txtReason);
        date = findViewById(R.id.txtDate);
        save = findViewById(R.id.btnSave);
        delete = findViewById(R.id.btnDelete);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading.....");
        progressDialog.setMessage("Please Wait!!");

        mDatabase = FirebaseDatabase.getInstance().getReference();

        getKey = getIntent().getStringExtra("key");
        getId = getIntent().getStringExtra("id");
        getValue = getIntent().getStringExtra("value");
        getDate = getIntent().getStringExtra("date");
        getReason = getIntent().getStringExtra("reason");

        id.setText(getId);
        value.setText(getValue);
        date.setText(getDate);
        reason.setText(getReason);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(EditDeleteExpenses.this);
                builder.setCancelable(true);
                builder.setTitle("Confirmation");
                builder.setMessage("Do you want to remove this Data?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteData();
                            }
                        });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        loadDate();


    }

    void deleteData(){
        progressDialog.show();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        reference.child("tblExpenses").child(getKey).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(EditDeleteExpenses.this, "Successfully Deleted!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(EditDeleteExpenses.this, "Error.", Toast.LENGTH_SHORT).show();
            }
        });

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
            Income_Model income_model = new Income_Model(getId, getValue, getReason, getDate);
            String key = getKey;
            mDatabase.child("tblExpenses").child(key).setValue(income_model).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    progressDialog.dismiss();
                    Toast.makeText(EditDeleteExpenses.this, "Successfully Updated!!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(Exception e) {
                    progressDialog.dismiss();
                    Log.d("Error", e.toString());
                    Toast.makeText(EditDeleteExpenses.this, "Fail try again.", Toast.LENGTH_SHORT).show();
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
                new DatePickerDialog(EditDeleteExpenses.this,date1,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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