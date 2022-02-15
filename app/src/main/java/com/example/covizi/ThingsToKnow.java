package com.example.covizi;

public class ThingsToKnow {
    private String title;
    private String date;
    private int photo;

    public ThingsToKnow(){
    }

    public ThingsToKnow(String title, String date, int photo) {
        this.title = title;
        this.date = date;
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }
}
