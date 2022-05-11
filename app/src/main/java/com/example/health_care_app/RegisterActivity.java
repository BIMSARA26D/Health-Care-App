package com.example.health_care_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.health_care_app.db.DBHelper;
import com.example.health_care_app.model.Doctor;

public class RegisterActivity extends AppCompatActivity {

    private Button button;
    boolean isAllFieldsChecked = false;

    EditText edFName, edLName, edEmail, edLNumber, edPNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        button = findViewById(R.id.btn_viewAll);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDoctorListActivity();
            }
        });

        edFName = findViewById(R.id.et_FName);
        edLName = findViewById(R.id.et_LName);
        edEmail = findViewById(R.id.et_Email);
        edLNumber = findViewById(R.id.et_LNumber);
        edPNumber = findViewById(R.id.et_PNumber);
    }

    public void openDoctorListActivity(){
        Intent intent = new Intent(this,DoctorListActivity.class);
        startActivity(intent);
    }

    //Create
    public void save(View view){
        isAllFieldsChecked = CheckAllFields();
        if(isAllFieldsChecked) {


            String fname = edFName.getText().toString().trim();
            String lname = edLName.getText().toString().trim();
            String email = edEmail.getText().toString().trim();
            String lnumber = edLNumber.getText().toString().trim();
            String pnumber = edPNumber.getText().toString().trim();

            DBHelper dbHelper = new DBHelper(RegisterActivity.this);

            Doctor s = new Doctor(fname, lname, email, lnumber, pnumber);

            long result = dbHelper.registerdoctor(s);

            if (result > 0) {
                Toast.makeText(this, "saved" + result, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "failed" + result, Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void all(View view) {startActivity(new Intent(RegisterActivity.this,DoctorListActivity.class));
    }


    //Validations
    private boolean CheckAllFields() {
        if(edFName.length() == 0){
            edFName.setError("This field is required");
            return false;
        }
        if(edLName.length() == 0){
            edLName.setError("This field is required");
            return false;
        }
        if (edEmail.length() == 0) {
            edEmail.setError("Email is required");
            return false;
        }
        if(edLNumber.length() == 0){
            edLNumber.setError("License Number is required");
            return false;
        }

        else if(edPNumber.length() < 10){
            edPNumber.setError("Please enter a valid mobile number");
            return false;
        }
        return true;
    }
}