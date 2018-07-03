package com.example.khaledsabry.entertainment.Controllers;

import com.example.khaledsabry.entertainment.Connection.Tmdb;
import com.example.khaledsabry.entertainment.Connection.TmdbConnection;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieList;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;

import org.json.JSONObject;

/**
 * Created by KhALeD SaBrY on 26-Jun-18.
 */

public class MovieController {
    private String Movie;
    private String MovieID;
    private String movieGetDetails;
    private String movieCast;
    private TmdbConnection connection;

    private Tmdb controller;

    public MovieController() {
        connection = TmdbConnection.getInstance();
        controller = Tmdb.getInstance();
    }

    private String makeBaseUrl(String type)
    {
        TmdbConnection.getInstance().setUrl(TmdbConnection.getInstance().getBaseUrl()+ type+TmdbConnection.getInstance().getApiKey());
       return TmdbConnection.getInstance().getUrl();
    }


    public void getMovieGetDetails(int movieID, final OnMovieDataSuccess listener) {
        movieGetDetails = "movie/"+movieID;
        String URL = makeBaseUrl(movieGetDetails)+"&append_to_response=credits,images,videos,reviews";

        connection.connect(URL, new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
               listener.onSuccess(controller.showMovie(jsonObject));

            }
        });
    }


    public void getRecommondations(int movieId, final OnMovieList listener)
    {
        movieGetDetails = "movie/"+movieId+"/recommendations";
        String URL = makeBaseUrl(movieGetDetails);
        connection.connect(URL, new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
              listener.onMovieList(controller.showRecommendationsAndSimilar(jsonObject));

            }
        });


    }


    public void getSimilar(int movieId, final OnMovieList listener)
    {
        movieGetDetails = "movie/"+movieId+"/similar";
        String URL = makeBaseUrl(movieGetDetails);
        connection.connect(URL, new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                // getMovieCast(jsonObject);
                listener.onMovieList(controller.showRecommendationsAndSimilar(jsonObject));

            }
        });


    }

    public void getMovieVideosCreditsCategories(int movieID, final OnMovieDataSuccess listener) {
        movieGetDetails = "movie/"+movieID;
        String URL = makeBaseUrl(movieGetDetails)+"&append_to_response=credits,videos";

        connection.connect(URL, new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getMovieVideosCreditsCategories(jsonObject));

            }
        });
    }

    public void getImages(int movieID, final OnMovieDataSuccess listener) {
        movieGetDetails = "movie/"+movieID+"/images";
        String URL = makeBaseUrl(movieGetDetails);

        connection.connect(URL, new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getPostersBackdrops(jsonObject,new Movie()));

            }
        });
    }

    public void getReviews(int movieID, final OnMovieDataSuccess listener) {
        movieGetDetails = "movie/"+movieID+"/reviews";
        String URL = makeBaseUrl(movieGetDetails);

        connection.connect(URL, new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getReviews(jsonObject,new Movie()));

            }
        });
    }
}
