package com.example.khaledsabry.entertainment.Items;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public class Genre {
    private int id;
    private String name;
    ArrayList<Genre> genres;

    public Genre() {
    }

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
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

    public Genre(int id) {
        this.id = id;
    }

    public String getMovieGenreName() {
        String value = "";
        switch (id) {
            case 28:
                value = "Action";
                break;
            case 12:
                value = "Adventure";
                break;
            case 16:
                value = "Animation";
                break;
            case 35:
                value = "Comedy";
                break;
            case 80:
                value = "Crime";
                break;
            case 99:
                value = "Documentary";
                break;
            case 18:
                value = "Drama";
                break;
            case 10751:
                value = "Family";
                break;
            case 14:
                value = "Fantasy";
                break;
            case 36:
                value = "History";
                break;
            case 27:
                value = "Horror";
                break;
            case 10402:
                value = "Music";
                break;
            case 9648:
                value = "Mystery";
                break;
            case 10749:
                value = "Romance";
                break;
            case 878:
                value = "Sci-Fi ";
                break;
            case 10770:
                value = "TV Movie";
                break;
            case 53:
                value = "Thriller";
                break;
            case 10752:
                value = "War";
                break;
            case 37:
                value = "Western";
                break;
                default:
        }
        return value;
    }

    public String getTvGenreName() {
        String value = "";
        switch (id) {
            case 10759:
                value = "Action & Adventure";
                break;
            case 10764:
                value = "Reality";
                break;
            case 16:
                value = "Animation";
                break;
            case 35:
                value = "Comedy";
                break;
            case 80:
                value = "Crime";
                break;
            case 99:
                value = "Documentary";
                break;
            case 18:
                value = "Drama";
                break;
            case 10751:
                value = "Family";
                break;
            case 10762:
                value = "Kids";
                break;
            case 9648:
                value = "Mystery";
                break;
            case 10763:
                value = "News";
                break;
            case 10765:
                value = "Sci-Fi ";
                break;
            case 10766:
                value = "Soap";
                break;
            case 10767:
                value = "Talk";
                break;
            case 10768:
                value = "War";
                break;
            case 37:
                value = "Western";
                break;
            default:
        }
        return value;
    }

}
