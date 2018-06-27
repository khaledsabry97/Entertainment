package com.example.khaledsabry.entertainment.Connection;

import com.example.khaledsabry.entertainment.Controllers.Controller;
import com.example.khaledsabry.entertainment.OnSuccess;

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

    private Controller controller;

    public TmdbType() {
        connection = TmdbConnection.getInstance();
        controller = Controller.getInstance();
    }

    public String getMovieCast(String movieID) {
       movieCast =  movieGetDetails+"/credits";
        return makeBaseUrl(movieCast);
    }

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
                controller.showMovie(jsonObject);

            }
        });
    }
}
