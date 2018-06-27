package com.example.khaledsabry.entertainment.Controllers;

import com.example.khaledsabry.entertainment.Items.Genre;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.ProductionCompany;
import com.example.khaledsabry.entertainment.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public class Controller {
    private String original_title="original_title";
    private String id = "id";
    private String imdb_id = "imdb_id";
    private String original_language = "original_language";
    private String overview = "overview";
    private String popularity = "popularity";
    private String poster_path = "poster_path";
    private String production_companies = "production_companies";
    private String logo_path = "logo_path";
    private String name = "name";
    private String origin_country = "origin_country";
    private String revenue = "revenue";
    private String runtime = "runtime";
    private String status = "status";
    private String tagline = "tagline";
    private String title = "title";
    private String vote_average = "vote_average";
    private String adult = "adult";
    private String budget = "budget";
   private String genres = "genres";



   private MainActivity mainActivity;

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<ProductionCompany> productionCompanies = new ArrayList<>();
    private ArrayList<Genre> genre = new ArrayList<>();
    private static final Controller ourInstance = new Controller();

    public static Controller getInstance() {
        return ourInstance;
    }

    private Controller() {
    }



    public void showMovie(JSONObject jsonObject)
    {
        try {

            Movie movie = new Movie();
            movie.setTitle(jsonObject.getString(title));
            movie.setBudget(jsonObject.getInt(budget));
            movie.setLanguage(jsonObject.getString(original_language));
            movie.setOverView(jsonObject.getString(overview));
            movie.setMovieImdbId(jsonObject.getString(imdb_id));
            movie.setMovieId( jsonObject.getInt(id));
            movie.setRevneue(jsonObject.getInt(revenue));
            movie.setPopularity(jsonObject.getDouble(popularity));
            movie.setPostorImage(jsonObject.getString(poster_path));
            movie.setTmdbRate(jsonObject.getInt(vote_average));
            movie.setStatus(jsonObject.getString(status));
            movie.setRunTime(jsonObject.getInt(runtime));
            JSONArray jsonArray = jsonObject.getJSONArray(production_companies);
            int i = 0;
         while(!jsonArray.isNull(i))
         {
             JSONObject object = jsonArray.getJSONObject(i);
             int ids =  object.getInt(id);
             String logo = object.getString(logo_path);
             String nam = object.getString(name);
             String countr = object.getString(origin_country);
             ProductionCompany productionCompany = new ProductionCompany(ids,logo,nam,countr);
             productionCompanies.add(productionCompany);
             i++;
         }
            jsonArray = jsonObject.getJSONArray(genres);
             i = 0;
            while(!jsonArray.isNull(i))
            {
                JSONObject object = jsonArray.getJSONObject(i);
                int ids =  object.getInt(id);
                String nam = object.getString(name);

                Genre genre = new Genre(ids,nam);
                this.genre.add(genre);
                i++;
            }

            movie.setGenres(genre);
            movie.setProductionCompanies(productionCompanies);
            genre.clear();
            productionCompanies.clear();
           // mainActivity.show(movie);

        }
        catch (JSONException e)
        {

        }
    }
}
