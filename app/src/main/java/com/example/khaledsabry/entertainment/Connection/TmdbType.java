package com.example.khaledsabry.entertainment.Connection;

import com.example.khaledsabry.entertainment.Controllers.MovieController;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieList;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;

import org.json.JSONObject;

/**
 * Created by KhALeD SaBrY on 26-Jun-18.
 */

public class TmdbType {
    private String Movie;
    private String MovieID;
    private String movieGetDetails;
    private String movieCast;
    private TmdbConnection connection;

    private MovieController controller;

    public TmdbType() {
        connection = TmdbConnection.getInstance();
        controller = MovieController.getInstance();
    }

   /* public String getMovieCast(String movieID) {
        return makeBaseUrl(movieCast);
    }
*/
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
               // getMovieCast(jsonObject);
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
                // getMovieCast(jsonObject);
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

/*
    public void getMovieCast(final JSONObject movieDetails)
    {
        movieCast =  movieGetDetails+"/credits";
        connection.connect(makeBaseUrl(movieCast), new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                controller.showMovie(movieDetails,jsonObject);
            }
        });


    }

    */
}
