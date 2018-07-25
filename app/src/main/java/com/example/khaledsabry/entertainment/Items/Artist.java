package com.example.khaledsabry.entertainment.Items;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public class Artist {
    private String name;
    private int id;
    private char gender;
    private String posterImage;
    private String job;
    private double popularity;
    private String birthDay;
    private String deathDay;
    private String placeOfBirth;
    private String imdbId;
    private String biography;
    private ArrayList<Character> moviesCharacters = new ArrayList<>();
    private ArrayList<Character> tvCharacters = new ArrayList<>();
    private ArrayList<String> images = new ArrayList<>();

    private ArrayList<Movie> movies = new ArrayList<>();
private ArrayList<Tv> tvs = new ArrayList<>();
    public Artist() {
    }

    public Artist(String name, int id, int gender, String posterImage) {
        this.name = name;
        this.id = id;
        setGender(gender);
        this.posterImage = posterImage;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public ArrayList<Tv> getTvs() {
        return tvs;
    }

    public void setTvs(ArrayList<Tv> tvs) {
        this.tvs = tvs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(int gender) {

        if(gender == 2)
            this.gender = 'M';
        else
            this.gender = 'F';
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getDeathDay() {
        if(deathDay == "null")
            return "-";
        return deathDay;
    }

    public void setDeathDay(String deathDay) {
        this.deathDay = deathDay;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public ArrayList<Character> getMoviesCharacters() {
        return moviesCharacters;
    }

    public void setMoviesCharacters(ArrayList<Character> moviesCharacters) {
        this.moviesCharacters = moviesCharacters;
    }

    public ArrayList<Character> getTvCharacters() {
        return tvCharacters;
    }

    public void setTvCharacters(ArrayList<Character> tvCharacters) {
        this.tvCharacters = tvCharacters;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
 /*   public ArrayList<Movie> sort()
    {
        ArrayList<Movie> years = new ArrayList<>();
        for (int i = 0 ;i  < movies.size();i++)
        {
            years = movies;
            for(int j = 0 ; j <movies.size();j++)
            {
                if(i !=j)
                {
                    if(Integer.valueOf(movies.get(i).getYear() )>Integer.valueOf( movies.get(j).getYear()))
                    {
                    Movie movie = movies.get(i);
                    years.get(i) = movie;
                    }
                }

                movies =years
            }
           years.add( Integer.valueOf(movies.get(i).getYear()));

        }

    }
    */
}
