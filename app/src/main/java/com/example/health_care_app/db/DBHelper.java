package com.example.health_care_app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.health_care_app.model.Doctor;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper( Context context) {
        super(context, "health.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE tbl_doctor (id INTEGER PRIMARY KEY AUTOINCREMENT, fname TEXT, lname TEXT, email TEXT, lnumber TEXT, pnumber TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbl_doctor");
        onCreate(sqLiteDatabase);
    }

    public long registerdoctor(Doctor s) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("fname", s.getFname());
        cv.put("lname", s.getLname());
        cv.put("email", s.getEmail());
        cv.put("lnumber", s.getLnumber());
        cv.put("pnumber", s.getPnumber());

        return db.insert("tbl_doctor", null, cv);


    }

    public ArrayList<Doctor> getAllDoctors(){

        ArrayList<Doctor> doctors = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * from tbl_doctor", null );

        if(cursor.moveToFirst()) {
            do {

                int id = cursor.getInt(0);
                String fname = cursor.getString(1);
                String lname = cursor.getString(2);
                String email = cursor.getString(3);
                String lnumber = cursor.getString(4);
                String pnumber = cursor.getString(5);

                Doctor s = new Doctor(id, fname, lname, email, lnumber, pnumber);
                doctors.add(s);


            } while (cursor.moveToNext());
        }

        return doctors;

    }

    public int deleteStudent(int id) {

        SQLiteDatabase db = getWritableDatabase();

        return db.delete("tbl_doctor", "id=?",new String[]{ String.valueOf(id)});

    }
}

