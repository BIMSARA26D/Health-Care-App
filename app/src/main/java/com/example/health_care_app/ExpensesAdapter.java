package com.example.health_care_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ExpensesAdapter extends ArrayAdapter<Expenses_Model> {

    ArrayList<Expenses_Model> dataList;
    Context context;
    int resource;


    public ExpensesAdapter(@NonNull Context context, int resource, @NonNull List<Expenses_Model> objects) {
        super(context, resource, objects);

        this.dataList = (ArrayList<Expenses_Model>) objects;
        this.context = context;
        this.resource = resource;

    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Nullable
    @Override
    public Expenses_Model getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.ly_income_card, null, true);
        }

        Expenses_Model item_model = getItem(position);

        TextView id = convertView.findViewById(R.id.txtId);
        TextView value = convertView.findViewById(R.id.txtValue);
        TextView reason = convertView.findViewById(R.id.txtReason);
        TextView date = convertView.findViewById(R.id.txtDate);

        Button edit = convertView.findViewById(R.id.btnEdit);

        String getKey = item_model.getKey();
        String getId = item_model.getId();
        String getValue = item_model.getValue();
        String getReason = item_model.getReason();
        String getDate = item_model.getDate();

        id.setText(getId);
        value.setText(getValue);
        reason.setText(getReason);
        date.setText(getDate);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, EditDeleteExpenses.class);
                intent.putExtra("key", getKey);
                intent.putExtra("id", getId);
                intent.putExtra("reason", getReason);
                intent.putExtra("value", getValue);
                intent.putExtra("date", getDate);
                context.startActivity(intent);

            }
        });




        return convertView;

    }
}
