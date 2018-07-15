package com.example.khaledsabry.entertainment.Controllers;

import com.example.khaledsabry.entertainment.Connection.Tmdb;
import com.example.khaledsabry.entertainment.Connection.ApiConnections;
import com.example.khaledsabry.entertainment.Interfaces.OnArtistDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieList;
import com.example.khaledsabry.entertainment.Interfaces.OnSearchSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnSeasonSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnTvSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Tv;

import org.json.JSONObject;

/**
 * Created by KhALeD SaBrY on 26-Jun-18.
 */

public class TmdbController {
    private String movieGetDetails;
    private ApiConnections connection;

    private Tmdb controller;

    public TmdbController() {
        connection = ApiConnections.getInstance();
        controller = Tmdb.getInstance();
    }

    private String makeBaseUrl(String type) {
        ApiConnections.getInstance().setUrl(ApiConnections.getInstance().getTmdbBaseUrl() + type + ApiConnections.getInstance().getApiKey());
        return ApiConnections.getInstance().getUrl();
    }


    public void getMovieGetDetails(int movieID, final OnMovieDataSuccess listener) {
        movieGetDetails = "movie/" + movieID;
        String URL = makeBaseUrl(movieGetDetails) + "&append_to_response=credits,images,videos,reviews";

        connection.connect(URL, new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.showMovie(jsonObject));

            }
        });
    }


    public void getRecommendations(int movieId, final OnMovieList listener) {
        movieGetDetails = "movie/" + movieId + "/recommendations";
        String URL = makeBaseUrl(movieGetDetails);
        connection.connect(URL, new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onMovieList(controller.showRecommendationsAndSimilar(jsonObject));

            }
        });


    }


    public void getSimilar(int movieId, final OnMovieList listener) {
        movieGetDetails = "movie/" + movieId + "/similar";
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
        movieGetDetails = "movie/" + movieID;
        String URL = makeBaseUrl(movieGetDetails) + "&append_to_response=credits,videos";

        connection.connect(URL, new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getMovieVideosCreditsCategories(jsonObject));

            }
        });
    }

    public void getMovieImages(int movieID, final OnMovieDataSuccess listener) {
        movieGetDetails = "movie/" + movieID + "/images";
        String URL = makeBaseUrl(movieGetDetails);

        connection.connect(URL, new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getPostersBackdrops(jsonObject, new Movie()));

            }
        });
    }

    public void getReviews(int movieID, final OnMovieDataSuccess listener) {
        movieGetDetails = "movie/" + movieID + "/reviews";
        String URL = makeBaseUrl(movieGetDetails);

        connection.connect(URL, new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                Movie movie = new Movie();
               movie.setReviews(controller.getReviews(jsonObject));
                listener.onSuccess(movie);

            }
        });
    }

    public void search(String query, final OnSearchSuccess listener) {
        movieGetDetails = "search/multi";
        String URL = makeBaseUrl(movieGetDetails)+"&query="+query+"&page=1";
       // URL = "https://oneom.tk/serial/62";
        connection.connect(URL, new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getSearchDone(jsonObject));

            }
        });
    }

    public void getGenres(final OnMovieDataSuccess listener) {
        movieGetDetails = "genre/movie/list";
        String URL = makeBaseUrl(movieGetDetails);

        connection.connect(URL, new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getGenres(jsonObject));

            }
        });
    }

    public void getPersonDetails(int personId,final OnArtistDataSuccess listener) {
        movieGetDetails = "person/"+personId;
        String URL = makeBaseUrl(movieGetDetails);

        connection.connect(URL, new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getPersonDetails(jsonObject));

            }
        });
    }

    public void getPersonRoles(int personId,final OnArtistDataSuccess listener) {
        movieGetDetails = "person/"+personId+"/combined_credits";
        String URL = makeBaseUrl(movieGetDetails);

        connection.connect(URL, new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getPersonRoles(jsonObject));

            }
        });
    }

    public void getPersonImages(int personId,final OnArtistDataSuccess listener) {
        movieGetDetails = "person/"+personId+"/images";
        String URL = makeBaseUrl(movieGetDetails);

        connection.connect(URL, new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getPersonImages(jsonObject));

            }
        });
    }


    public void getTv(int tvId, final OnTvSuccess listener) {
        movieGetDetails = "tv/" + tvId;
        String URL = makeBaseUrl(movieGetDetails) + "&append_to_response=credits,images,videos,reviews";

        connection.connect(URL, new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getTv(jsonObject,new Tv()));

            }
        });
    }

    public void getTvImages(int tvId, final OnTvSuccess listener) {
        movieGetDetails = "tv/" + tvId + "/images";
        String URL = makeBaseUrl(movieGetDetails);

        connection.connect(URL, new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getPostersBackdrops(jsonObject, new Tv()));

            }
        });
    }

    public void getTvSeason(int tvId,int seasonNo, final OnSeasonSuccess listener) {
        movieGetDetails = "tv/" + tvId + "/season/"+seasonNo;
        String URL = makeBaseUrl(movieGetDetails);

        connection.connect(URL, new OnSuccess() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getSeason(jsonObject));

            }
        });
    }








}
