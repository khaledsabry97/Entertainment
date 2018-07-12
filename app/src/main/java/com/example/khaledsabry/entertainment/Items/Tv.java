package com.example.khaledsabry.entertainment.Items;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 04-Jul-18.
 */

public class Tv {
    private int id;
    private String title;
    private String posterImage;
    private String backDrop;
    private int runTime;
    private String firstAirDate;
    private String lastAirDate;
    private String overView;
    private boolean inProduction;
    private int numberOfEpisodes;
    private int numberOfSeasons;
    private double popularity;
    private double rateTmdb;
private String status;
    private ArrayList<Genre> genres = new ArrayList<>();
    private ArrayList<ProductionCompany> networks = new ArrayList<>();
    private ArrayList<ProductionCompany> productionCompanies = new ArrayList<>();
    private ArrayList<Season> seasons = new ArrayList<>();
    private ArrayList<Artist> crew = new ArrayList<>();
    private ArrayList<Character> actors = new ArrayList<>();
    private ArrayList<String> posters = new ArrayList<>();
    private ArrayList<String> backdrops = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getPosters() {
        return posters;
    }

    public void setPosters(ArrayList<String> posters) {
        this.posters = posters;
    }

    public ArrayList<String> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(ArrayList<String> backdrops) {
        this.backdrops = backdrops;
    }

    public ArrayList<Artist> getCrew() {
        return crew;
    }

    public void setCrew(ArrayList<Artist> crew) {
        this.crew = crew;
    }

    public ArrayList<Character> getActors() {
        return actors;
    }

    public void setActors(ArrayList<Character> actors) {
        this.actors = actors;
    }

    private ArrayList<Review> reviews = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }


    public String getBackDrop() {
        return backDrop;
    }

    public void setBackDrop(String backDrop) {
        this.backDrop = backDrop;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public boolean isInProduction() {
        return inProduction;
    }

    public void setInProduction(boolean inProduction) {
        this.inProduction = inProduction;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getRateTmdb() {
        return rateTmdb;
    }

    public void setRateTmdb(double rateTmdb) {
        this.rateTmdb = rateTmdb;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public ArrayList<ProductionCompany> getNetworks() {
        return networks;
    }

    public void setNetworks(ArrayList<ProductionCompany> networks) {
        this.networks = networks;
    }

    public ArrayList<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(ArrayList<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public String  getGenreList()
    {
        String s = "";
        for(int i = 0 ;i < genres.size();i++)
        {
            s +=genres.get(i).getName();
            if(i+1 !=genres.size())
                s+= "| ";

        }
        return s;
    }
}
