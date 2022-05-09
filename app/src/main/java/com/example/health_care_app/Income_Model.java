package com.example.health_care_app;

public class Income_Model {

    String key;
    String id;
    String value;
    String reason;
    String date;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Income_Model() {
    }

    public Income_Model(String key, String id, String value, String reason, String date) {
        this.key = key;
        this.id = id;
        this.value = value;
        this.reason = reason;
        this.date = date;
    }

    public Income_Model(String id, String value, String reason, String date) {
        this.id = id;
        this.value = value;
        this.reason = reason;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
