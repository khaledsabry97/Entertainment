package com.example.khaledsabry.entertainment.Items;

/**
 * Created by KhALeD SaBrY on 04-Jul-18.
 */

public class SearchItem {
  public   enum Type
    {
        movie,
        artist,
        tv
    }
    private Type type;
  private String typeString;

    public String getTypeString() {
        return typeString.toLowerCase();
    }

    public void setType(String type)
    {
        if(type.equals("movie"))
            this.type = Type.movie;
        else if(type.equals("tv"))
            this.type = Type.tv;
        else if(type.equals("person"))
            this.type = Type.artist;
        typeString = type;
    }
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

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
