package com.example.khaledsabry.entertainment.Items;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.text.DecimalFormat;
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
    private boolean isAdult;
    private Collection collection;
    private String backDropPoster;
    private String domesticBudget;
    private String overseasBudget;
    private String worldWideBudget;

    public String getOverseasBudget() {
        return overseasBudget;
    }

    public void setOverseasBudget(String overseasBudget) {
        this.overseasBudget = overseasBudget;
    }

    public String getWorldWideBudget() {
        return worldWideBudget;
    }

    public void setWorldWideBudget(String worldWideBudget) {
        this.worldWideBudget = worldWideBudget;
    }

    public String getDomesticBudget() {
        return domesticBudget;
    }

    public void setDomesticBudget(String domesticBudget) {
        this.domesticBudget = domesticBudget;
    }

    private ArrayList<ProductionCompany> productionCompanies = new ArrayList<>();
    private ArrayList<Genre> genres = new ArrayList<>();
    private ArrayList<Character>characters = new ArrayList<>();
    private ArrayList<Artist> crews = new ArrayList<>();
    private ArrayList<String> posters = new ArrayList<>();
    private ArrayList<String> backdrops = new ArrayList<>();
    private ArrayList<String> trailers = new ArrayList<>();
    private ArrayList<Review> reviews = new ArrayList<>();
    private ArrayList<Torrent> torrents = new ArrayList<>();


    public String getBackDropPoster() {
        return backDropPoster;
    }

    public void setBackDropPoster(String backDropPoster) {
        this.backDropPoster = backDropPoster;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public String isAdult() {
        if(isAdult)
            return "this is for adults";
        else
            return "you can view it freely";
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }



    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<String> getTrailers() {
        return trailers;
    }

    public void setTrailers(ArrayList<String> trailers) {
        this.trailers = trailers;
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

    public String getBudget() {

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        String numberAsString = decimalFormat.format(budget);
        numberAsString = "$"+numberAsString;
        return numberAsString;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getRevneue() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        String numberAsString = decimalFormat.format(revneue);
        numberAsString = "$"+numberAsString;
        return numberAsString;
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


    public ArrayList<Artist> getCrews() {
        return crews;
    }

    public void setCrews(ArrayList<Artist> crews) {
        this.crews = crews;
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


    public String formatDecimal(float number) {
        float epsilon = 0.004f; // 4 tenths of a cent
        if (Math.abs(Math.round(number) - number) < epsilon) {
            return String.format("%10.0f", number); // sdb
        } else {
            return String.format("%10.2f", number); // dj_segfault
        }
    }


    public ArrayList<Torrent> getTorrents() {
        return torrents;
    }

    public void setTorrents(ArrayList<Torrent> torrents) {
        this.torrents = torrents;
    }

    public String getYear()
    {
        String date = releaseDate;
        if(date == null)
            return null;
        char[] d = new char[4];
        d[0] = date.charAt(0);
        d[1] = date.charAt(1);
        d[2] = date.charAt(2);
        d[3] = date.charAt(3);
        date = String.copyValueOf(d);
        return date;
    }
}
