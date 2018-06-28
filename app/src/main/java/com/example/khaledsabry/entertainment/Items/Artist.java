package com.example.khaledsabry.entertainment.Items;

import android.graphics.Bitmap;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public class Artist {
    private String name;
    private int id;
    private char gender;
    private String posterImage;
    private String job;


    public Artist(String name, int id, int gender, String posterImage) {
        this.name = name;
        this.id = id;
        setGender(gender);
        this.posterImage = posterImage;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(int gender) {

        if(gender == 2)
            this.gender = 'M';
        else
            this.gender = 'F';
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
