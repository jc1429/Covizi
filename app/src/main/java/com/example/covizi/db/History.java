package com.example.covizi.db;


//entity or data model
public class History {

    public int id;

    private String date;
    private String time;
    private String location;

    public History(String date, String time, String location) {
        this.date = date;
        this.time = time;
        this.location = location;
    }

    //Setter and getter functions for each property
    public int getId() {
        return this.id;
    }

    public void setId(int id) { this.id = id; }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) { this.date = date; }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) { this.time = time; }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) { this.location = location; }
}