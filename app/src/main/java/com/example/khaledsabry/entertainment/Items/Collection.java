package com.example.khaledsabry.entertainment.Items;

/**
 * Created by KhALeD SaBrY on 03-Jul-18.
 */

public class Collection {
    private  int id;
    private String name;
    private String poster;
    private String  backdrop;

    public Collection(int id, String name, String poster, String backdrop) {
        this.id = id;
        this.name = name;
        this.poster = poster;
        this.backdrop = backdrop;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }
}
