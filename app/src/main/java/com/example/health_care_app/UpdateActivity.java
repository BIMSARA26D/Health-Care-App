package com.example.health_care_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.health_care_app.db.DBHelper;
import com.example.health_care_app.model.Doctor;

public class UpdateActivity extends AppCompatActivity {

    EditText edFName, edLName, edEmail, edLNumber, edPNumber;
    int id;
    private Object Doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Doctor s = (Doctor) getIntent().getExtras().getSerializable("DOCTOR");

        id = s.getId();
        edFName = findViewById(R.id.et_FName);
        edLName = findViewById(R.id.et_LName);
        edEmail = findViewById(R.id.et_Email);
        edLNumber = findViewById(R.id.et_LNumber);
        edPNumber = findViewById(R.id.et_PNumber);

//        edFName.setText(s.getFname());
//        edLName.setText(s.getLname());
//        edEmail.setText(s.getEmail());
//        edLNumber.setText(s.getLnumber());
//        edPNumber.setText(s.getPnumber());
    }
    //Update
    public void update(View view){
//        String fname = edFName.getText().toString().trim();
//        String lname = edLName.getText().toString().trim();
//        String email = edEmail.getText().toString().trim();
//        String lnumber = edLNumber.getText().toString().trim();
//        String pnumber = edPNumber.getText().toString().trim();
//
//        Doctor = new Doctor(id, fname, lname, email, lnumber, pnumber);
//        DBHelper dbHelper = new DBHelper(this);
//        int result = dbHelper.updateDoctor(s);
//
//        if( result > 0)
//        {
//            Toast.makeText(this, "Update" + result, Toast.LENGTH_SHORT).show();
//            finish();
//        }
//        else
//        {
//            Toast.makeText(this,"failed" + result, Toast.LENGTH_SHORT).show();
//        }

    }


}