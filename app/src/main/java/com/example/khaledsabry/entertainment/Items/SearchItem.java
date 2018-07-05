package com.example.khaledsabry.entertainment.Items;

/**
 * Created by KhALeD SaBrY on 04-Jul-18.
 */

public class SearchItem {
    private String type;

    private Movie movie;
    private Artist artist;
    private Tv tv;

    public SearchItem() {
        movie = new Movie();
        artist = new Artist();
        tv = new Tv();
    }

    public Tv getTv() {
        return tv;
    }

    public void setTv(Tv tv) {
        this.tv = tv;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
