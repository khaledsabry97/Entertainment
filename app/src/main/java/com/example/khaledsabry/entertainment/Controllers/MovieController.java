package com.example.khaledsabry.entertainment.Controllers;

import com.example.khaledsabry.entertainment.Fragments.BlankFragment;
import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.Items.Character;
import com.example.khaledsabry.entertainment.Items.Crew;
import com.example.khaledsabry.entertainment.Items.Genre;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.ProductionCompany;
import com.example.khaledsabry.entertainment.Activities.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 27-Jun-18.
 */

public class MovieController {


    private String original_title = "original_title";
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
    private String cast = "cast";
    private String gender = "gender";
    private String order = "order";
    private String profile_path = "profile_path";
    private String character = "character";
    private String credits = "credits";
    private String crew = "crew";
    private String job = "job";
    private String release_date = "release_date";

    private MainActivity mainActivity;
    private BlankFragment blankFragment;


    private static final MovieController ourInstance = new MovieController();

    public static MovieController getInstance() {
        return ourInstance;
    }

    private MovieController() {
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setBlankFragment(BlankFragment blankFragment) {
        this.blankFragment = blankFragment;
    }

    /*
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
                movie.setPosterImage(jsonObject.getString(poster_path));
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
                blankFragment.loadMovieDetails(movie);
                // mainActivity.show(movie);

            }
            catch (JSONException e)
            {

            }
        }
    */
    public void showMovie(JSONObject movieDetails) {
        try {

            JSONObject cast = movieDetails.getJSONObject(credits);
            Movie movie = new Movie();
            JSONArray jsonArray;
            int i;
            ArrayList<ProductionCompany> productionCompanies = new ArrayList<>();
            ArrayList<Genre> genre = new ArrayList<>();
            ArrayList<Character> characters = new ArrayList<>();
            ArrayList<Crew> crews = new ArrayList<>();

            jsonArray = movieDetails.getJSONArray(production_companies);
            i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                int ids = object.getInt(id);
                String logo = object.getString(logo_path);
                String nam = object.getString(name);
                String countr = object.getString(origin_country);
                ProductionCompany productionCompany = new ProductionCompany(ids, logo, nam, countr);
                productionCompanies.add(productionCompany);
                i++;
            }
            jsonArray = movieDetails.getJSONArray(genres);
            i = 0;

            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                int ids = object.getInt(id);
                String nam = object.getString(name);

                Genre gen = new Genre(ids, nam);
                genre.add(gen);
                i++;
            }


            jsonArray = cast.getJSONArray(this.cast);
            i = 0;

            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                int ids = object.getInt(id);
                String nam = object.getString(name);
                String profilePath = object.getString(profile_path);
                int gend = object.getInt(gender);
                String characterName = object.getString(this.character);
                Artist artist = new Artist(nam, ids, gend, profilePath);
                Character character = new Character(artist, characterName);


                characters.add(character);
                i++;
            }
            i = 0;


            jsonArray = cast.getJSONArray(crew);
            i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                int ids = object.getInt(id);
                String nam = object.getString(name);
                String jobtitle = object.getString(job);
                String profilePath = object.getString(profile_path);
                int gend = object.getInt(gender);

                Artist artist = new Artist(nam, ids, gend, profilePath);
                Crew crew = new Crew(artist, jobtitle);
                crews.add(crew);


                i++;
            }
            movie.setTitle(movieDetails.getString(title));
            movie.setBudget(movieDetails.getInt(budget));
            movie.setLanguage(movieDetails.getString(original_language));
            movie.setOverView(movieDetails.getString(overview));
            movie.setMovieImdbId(movieDetails.getString(imdb_id));
            movie.setMovieId(movieDetails.getInt(id));
            movie.setRevneue(movieDetails.getInt(revenue));
            movie.setPopularity(movieDetails.getDouble(popularity));
            movie.setPosterImage(movieDetails.getString(poster_path));
            movie.setTmdbRate(movieDetails.getInt(vote_average));
            movie.setStatus(movieDetails.getString(status));
            movie.setRunTime(movieDetails.getInt(runtime));
            movie.setAdult(movieDetails.getBoolean(adult));
            movie.setReleaseDate(movieDetails.getString(release_date));

            movie.setCharacters(characters);
            movie.setGenres(genre);
            movie.setProductionCompanies(productionCompanies);
            movie.setCrews(crews);


            blankFragment.loadMovieDetails(movie);

        } catch (JSONException e) {
            String s = e.toString();

        }
    }
}
