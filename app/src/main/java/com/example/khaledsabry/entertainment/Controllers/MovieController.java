package com.example.khaledsabry.entertainment.Controllers;

import com.example.khaledsabry.entertainment.Fragments.BlankFragment;
import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.Items.Character;
import com.example.khaledsabry.entertainment.Items.Crew;
import com.example.khaledsabry.entertainment.Items.Genre;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.ProductionCompany;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Settings;

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
    private String images = "images";
    private String posters = "posters";
    private String backdrops = "backdrops";
    private String file_path = "file_path";
    private String videos = "videos";
    private String results = "results";
    private String key = "key";
    private String genre_ids = "genre_ids";


    private MainActivity mainActivity;
    private BlankFragment blankFragment;


    private static final MovieController ourInstance = new MovieController();

    public static MovieController getInstance() {
        return ourInstance;
    }

    private MovieController() {
    }


    public void setBlankFragment(BlankFragment blankFragment) {
        this.blankFragment = blankFragment;
    }

    public void showMovie(JSONObject movieDetails) {
        try {

            JSONObject cast = movieDetails.getJSONObject(credits);
            JSONObject images = movieDetails.getJSONObject(this.images);
            JSONObject videos = movieDetails.getJSONObject(this.videos);


            Movie movie = new Movie();
            JSONArray jsonArray;
            int i;
            ArrayList<ProductionCompany> productionCompanies = new ArrayList<>();
            ArrayList<Genre> genre = new ArrayList<>();
            ArrayList<Character> characters = new ArrayList<>();
            ArrayList<Crew> crews = new ArrayList<>();
            ArrayList<String> posters = new ArrayList<>();
            ArrayList<String> backdrops = new ArrayList<>();
            ArrayList<String> trailers = new ArrayList<>();

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

            jsonArray = images.getJSONArray(this.posters);
            i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                String filepath = object.getString(this.file_path);
                posters.add(filepath);
                i++;
            }


            jsonArray = images.getJSONArray(this.backdrops);
            i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                String filepath = object.getString(this.file_path);
                backdrops.add(filepath);
                i++;
            }

            jsonArray = videos.getJSONArray(this.results);
            i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                String filepath = object.getString(this.key);
                trailers.add(filepath);
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
            movie.setPosters(posters);
            movie.setBackdrops(backdrops);
            movie.setTrailers(trailers);

            movie.setCharacters(characters);
            movie.setGenres(genre);
            movie.setProductionCompanies(productionCompanies);
            movie.setCrews(crews);


            blankFragment.loadMovieDetails(movie);

        } catch (JSONException e) {
            String s = e.toString();

        }
    }


    public ArrayList<Movie> showRecommendationsAndSimilar(JSONObject movieDetails) {
        ArrayList<Movie> movies = new ArrayList<>();
        JSONArray jsonArray = null;
        try {
            int i;
            jsonArray = movieDetails.getJSONArray(this.results);
            i = 0;
            while (!jsonArray.isNull(i)) {
                JSONObject object = jsonArray.getJSONObject(i);
                getMovies(object, movies);


                i++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

return movies;
    }


    private void getMovies(JSONObject movieDetails, ArrayList<Movie> movies) {
        try {

            Movie movie = new Movie();
            JSONArray jsonArray;
            int i;

            ArrayList<Genre> genre = new ArrayList<>();



            jsonArray = movieDetails.getJSONArray(genre_ids);
            i = 0;

            while (!jsonArray.isNull(i)) {
                int ids =jsonArray.getInt(i);

                Genre gen = new Genre(ids);
                genre.add(gen);
                i++;
            }


            movie.setTitle(movieDetails.getString(title));
            movie.setLanguage(movieDetails.getString(original_language));
            movie.setOverView(movieDetails.getString(overview));
            movie.setMovieId(movieDetails.getInt(id));
            movie.setPosterImage(movieDetails.getString(poster_path));
            movie.setTmdbRate(movieDetails.getInt(vote_average));
            movie.setAdult(movieDetails.getBoolean(adult));
            movie.setReleaseDate(movieDetails.getString(release_date));

            movie.setGenres(genre);
            movies.add(movie);

        } catch (JSONException e) {
            String s = e.toString();

        }
    }


}
