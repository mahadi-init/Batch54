package com.example.batch54.models;

import android.net.Uri;

public class StoryModel {
    private String name;
    private String title;
    private String date;
    private Uri image;

    public StoryModel(String name, String title, String date, Uri image) {
        this.name = name;
        this.title = title;
        this.date = date;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}
