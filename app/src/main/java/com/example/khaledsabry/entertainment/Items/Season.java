package com.example.khaledsabry.entertainment.Items;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 11-Jul-18.
 */

public class Season {
    private  int id;
    private String poster;
    private String airDate;
    private String name;
    private String overView;
    private int seasonNumber;
    private ArrayList<Episode> episodes = new ArrayList<>();
    private ArrayList<Artist> crews = new ArrayList<>();
    private ArrayList<Character> actors = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<String> videos = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }
}
