package com.example.khaledsabry.entertainment.Items;

import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public class Movie {
    private int movieId;
    private String movieImdbId;
    private String title;
    private String overView;
    private String posterImage;
    private String releaseDate;
    private String language;
    private String status;
    private String tagLine;
    private float tmdbRate;
    private float imdbRate;
    private int budget;
    private int revneue;
    private int runTime;
    private Double popularity;
    private ArrayList<ProductionCompany> productionCompanies = new ArrayList<>();
    private ArrayList<Genre> genres = new ArrayList<>();
    private ArrayList<Character>characters = new ArrayList<>();



    public Movie() {
    }


    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieImdbId() {
        return movieImdbId;
    }

    public void setMovieImdbId(String movieImdbId) {
        this.movieImdbId = movieImdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public void setPosterImage(String posterImage) {
         this.posterImage = posterImage;
    }

    public String getPosterImage() {
      return posterImage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public float getTmdbRate() {
        return tmdbRate;
    }

    public void setTmdbRate(float tmdbRate) {
        this.tmdbRate = tmdbRate;
    }

    public float getImdbRate() {
        return imdbRate;
    }

    public void setImdbRate(float imdbRate) {
        this.imdbRate = imdbRate;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getRevneue() {
        return revneue;
    }

    public void setRevneue(int revneue) {
        this.revneue = revneue;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public ArrayList<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(ArrayList<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }


    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }

}
