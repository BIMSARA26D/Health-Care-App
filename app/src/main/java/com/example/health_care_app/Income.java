package com.example.health_care_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Income extends AppCompatActivity {

    private ListView loadData;
    private ProgressDialog progressDialog;
    private ArrayList<Income_Model> dataList;

    public static DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Income Manager");

        mDatabase = FirebaseDatabase.getInstance().getReference();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading..");
        progressDialog.setMessage("Please Wait!!");

        dataList = new ArrayList<>();

        Button income = findViewById(R.id.btnAddIncome);
        loadData = findViewById(R.id.lstViewData);

        EditText id = findViewById(R.id.txtId);

        getDataAllData();

        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Income.this, AddIncome.class);
                startActivity(intent);
            }
        });

        Button search = findViewById(R.id.btnSearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getID = id.getText().toString().trim();

                if(getID.isEmpty()){
                    id.setError("Enter ID");
                    getDataAllData();
                }else{
                    searchData(getID);
                }
            }
        });


    }

    void getDataAllData(){

        progressDialog.show();

        IncomeAdapter itemAdapter = new IncomeAdapter(this, R.layout.ly_income_card, dataList);

        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        loadData.setAdapter(itemAdapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("tblIncome");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot snapshot1: snapshot.getChildren()){

                    Income_Model item_model = snapshot1.getValue(Income_Model.class);
                    String getId = item_model.getId();
                    String getValue = item_model.getValue();
                    String getReason = item_model.getReason();
                    String getDate = item_model.getDate();
                    String getKey = snapshot1.getRef().getKey();

                    dataList.add(new Income_Model(getKey, getId, getValue, getReason, getDate));
                }

                itemAdapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
                Toast.makeText(Income.this, "Error!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void searchData(String id){
        progressDialog.show();

        IncomeAdapter itemAdapter = new IncomeAdapter(this, R.layout.ly_income_card, dataList);

        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        loadData.setAdapter(itemAdapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("tblIncome");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot snapshot1: snapshot.getChildren()){

                    Income_Model item_model = snapshot1.getValue(Income_Model.class);
                    String getId = item_model.getId();
                    String getValue = item_model.getValue();
                    String getReason = item_model.getReason();
                    String getDate = item_model.getDate();
                    String getKey = snapshot1.getRef().getKey();

                    if(getId.equals(id)){
                        dataList.add(new Income_Model(getKey, getId, getValue, getReason, getDate));
                    }

                }

                itemAdapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
                Toast.makeText(Income.this, "Error!!", Toast.LENGTH_SHORT).show();
            }
        });
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