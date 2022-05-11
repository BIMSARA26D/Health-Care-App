package com.example.health_care_app.model;

import java.io.Serializable;

public class Doctor implements Serializable {

    int id;
    String fname;
    String lname;
    String email;
    String lnumber;
    String pnumber;


    public Doctor(int id, String fname, String lname, String email, String lnumber, String pnumber) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.lnumber = lnumber;
        this.pnumber = pnumber;
    }

    public Doctor(String fname, String lname, String email, String lnumber, String pnumber) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.lnumber = lnumber;
        this.pnumber = pnumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLnumber() {
        return lnumber;
    }

    public void setLnumber(String lnumber) {
        this.lnumber = lnumber;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }
}
