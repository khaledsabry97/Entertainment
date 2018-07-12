package com.example.khaledsabry.entertainment.Items;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 11-Jul-18.
 */

public class Episode {
    private String airDate;
    private int episodeNumber;

    private String name;

    private String overView;
    private String productionCode;
    private int seasonNumber;
    private String poster;
    private double rateTmdb;
    private int id;
    private ArrayList<Artist> crews = new ArrayList<>();
    private ArrayList<Character> guests = new ArrayList<>();
    private ArrayList<Character> actors = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();
    private ArrayList<String> videos = new ArrayList<>();


    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
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

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public double getRateTmdb() {
        return rateTmdb;
    }

    public void setRateTmdb(double rateTmdb) {
        this.rateTmdb = rateTmdb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Artist> getCrews() {
        return crews;
    }

    public void setCrews(ArrayList<Artist> crews) {
        this.crews = crews;
    }

    public ArrayList<Character> getGuests() {
        return guests;
    }

    public void setGuests(ArrayList<Character> guests) {
        this.guests = guests;
    }
}
