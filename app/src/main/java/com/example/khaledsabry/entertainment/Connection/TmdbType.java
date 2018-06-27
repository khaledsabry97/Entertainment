package com.example.khaledsabry.entertainment.Connection;

import com.example.khaledsabry.entertainment.Controllers.MovieController;
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


    public void getMovieGetDetails(int movieID) {
        movieGetDetails = "movie/"+movieID;
        connection.connect(makeBaseUrl(movieGetDetails), new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                getMovieCast(jsonObject);
            //    controller.showMovie(jsonObject);

            }
        });
    }


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
}
