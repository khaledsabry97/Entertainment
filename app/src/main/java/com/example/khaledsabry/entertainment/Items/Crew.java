package com.example.khaledsabry.entertainment.Items;

/**
 * Created by KhALeD SaBrY on 28-Jun-18.
 */

public class Crew {

    private Artist artist;
    private String jobTitle;

    public Crew(Artist artist, String jobTitle) {
        this.artist = artist;
        this.jobTitle = jobTitle;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getJobTitle() {
        return jobTitle;
    }
}
