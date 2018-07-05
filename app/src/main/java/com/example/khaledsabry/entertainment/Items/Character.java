package com.example.khaledsabry.entertainment.Items;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public class Character {
    private Artist artist;
    private String characterName;
    private Movie movie;
private Tv tv;
    public Character(Artist artist, String characterName) {
        this.artist = artist;
        this.characterName = characterName;
    }

    public Character() {
    }

    public Tv getTv() {
        return tv;
    }

    public void setTv(Tv tv) {
        this.tv = tv;
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

    public String getCharacterName() {
        return characterName;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
}
