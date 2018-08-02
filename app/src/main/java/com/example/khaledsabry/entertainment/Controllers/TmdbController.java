package com.example.khaledsabry.entertainment.Controllers;

import android.support.v4.view.PagerAdapter;

import com.example.khaledsabry.entertainment.Connection.Tmdb;
import com.example.khaledsabry.entertainment.Connection.ApiConnections;
import com.example.khaledsabry.entertainment.Interfaces.OnArtistDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieList;
import com.example.khaledsabry.entertainment.Interfaces.OnSearchSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnSeasonSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnTvList;
import com.example.khaledsabry.entertainment.Interfaces.OnTvSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.SearchItem;
import com.example.khaledsabry.entertainment.Items.Tv;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

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

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.showMovie(jsonObject));

            }
        });
    }


    public void getRecommendations(int movieId, final OnMovieList listener) {
        movieGetDetails = "movie/" + movieId + "/recommendations";
        String URL = makeBaseUrl(movieGetDetails);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onMovieList(controller.showRecommendationsAndSimilar(jsonObject));

            }
        });


    }


    public void getSimilar(int movieId, final OnMovieList listener) {
        movieGetDetails = "movie/" + movieId + "/similar";
        String URL = makeBaseUrl(movieGetDetails);
        connection.connect(URL, new OnSuccess.Json() {
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

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getMovieVideosCreditsCategories(jsonObject));

            }
        });
    }

    public void getMovieImages(int movieID, final OnMovieDataSuccess listener) {
        movieGetDetails = "movie/" + movieID + "/images";
        String URL = makeBaseUrl(movieGetDetails);

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getPostersBackdrops(jsonObject, new Movie()));

            }
        });
    }

    public void getReviews(int movieID, final OnMovieDataSuccess listener) {
        movieGetDetails = "movie/" + movieID + "/reviews";
        String URL = makeBaseUrl(movieGetDetails);

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                Movie movie = new Movie();
               movie.setReviews(controller.getReviews(jsonObject));
                listener.onSuccess(movie);

            }
        });
    }

    public void search(String query, final OnSearchSuccess listener) {
        if(query.equals(""))
            return;
        movieGetDetails = "search/multi";
        String URL = makeBaseUrl(movieGetDetails)+"&query="+query+"&page=1";
connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getSearchDone(jsonObject));

            }
        });
    }

    public void getGenres(final OnMovieDataSuccess listener) {
        movieGetDetails = "genre/movie/list";
        String URL = makeBaseUrl(movieGetDetails);

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getGenres(jsonObject));

            }
        });
    }

    public void getPersonDetails(int personId,final OnArtistDataSuccess listener) {
        movieGetDetails = "person/"+personId;
        String URL = makeBaseUrl(movieGetDetails);

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getPersonDetails(jsonObject));

            }
        });
    }

    public void getPersonRoles(int personId,final OnArtistDataSuccess listener) {
        movieGetDetails = "person/"+personId+"/combined_credits";
        String URL = makeBaseUrl(movieGetDetails);

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getPersonRoles(jsonObject));

            }
        });
    }

    public void getPersonImages(int personId,final OnArtistDataSuccess listener) {
        movieGetDetails = "person/"+personId+"/images";
        String URL = makeBaseUrl(movieGetDetails);

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getPersonImages(jsonObject));

            }
        });
    }


    public void getTv(int tvId, final OnTvSuccess listener) {
        movieGetDetails = "tv/" + tvId;
        String URL = makeBaseUrl(movieGetDetails) + "&append_to_response=credits,images,videos,reviews";

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getTv(jsonObject,new Tv()));

            }
        });
    }

    public void getTvImages(int tvId, final OnTvSuccess listener) {
        movieGetDetails = "tv/" + tvId + "/images";
        String URL = makeBaseUrl(movieGetDetails);

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getPostersBackdrops(jsonObject, new Tv()));

            }
        });
    }

    public void getTvSeason(int tvId,int seasonNo, final OnSeasonSuccess listener) {
        movieGetDetails = "tv/" + tvId + "/season/"+seasonNo;
        String URL = makeBaseUrl(movieGetDetails);

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getSeason(jsonObject));

            }
        });
    }





    public void getMoviesNowPlaying(final OnMovieList listener) {
        movieGetDetails = "movie/now_playing";
        String URL = makeBaseUrl(movieGetDetails);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                // getMovieCast(jsonObject);
                listener.onMovieList(controller.getMovieNowPlaying(jsonObject));

            }
        });


    }

    public void getMoviesPopular(final OnMovieList listener) {
        movieGetDetails = "movie/popular";
        String URL = makeBaseUrl(movieGetDetails);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onMovieList(controller.getMoviePopular(jsonObject));

            }
        });


    }


    public void getMoviesTopRated(final OnMovieList listener) {
        movieGetDetails = "movie/top_rated";
        String URL = makeBaseUrl(movieGetDetails);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onMovieList(controller.getMovieTopRated(jsonObject));

            }
        });


    }

    public void getMoviesUpComing(final OnMovieList listener) {
        movieGetDetails = "movie/upcoming";
        String URL = makeBaseUrl(movieGetDetails);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onMovieList(controller.getMovieUpComing(jsonObject));

            }
        });


    }


    public void getTvOnAir(final OnTvList listener) {
        movieGetDetails = "tv/on_the_air";
        String URL = makeBaseUrl(movieGetDetails);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                // getMovieCast(jsonObject);
                listener.onSuccess(controller.getTvOnAir(jsonObject));

            }
        });
    }

    public void getTvAirToday(final OnTvList listener) {
        movieGetDetails = "tv/airing_today";
        String URL = makeBaseUrl(movieGetDetails);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getTvAiringToday(jsonObject));

            }
        });
    }



    public void getTvPopular(final OnTvList listener) {
        movieGetDetails = "tv/popular";
        String URL = makeBaseUrl(movieGetDetails);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                // getMovieCast(jsonObject);
                listener.onSuccess(controller.getTvPopular(jsonObject));

            }
        });
    }

    public void getTvTopRated(final OnTvList listener) {
        movieGetDetails = "tv/top_rated";
        String URL = makeBaseUrl(movieGetDetails);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                // getMovieCast(jsonObject);
                listener.onSuccess(controller.getTvTopRated(jsonObject));

            }
        });
    }




    public void getArtistPopular(final OnArtistDataSuccess.List listener) {
        movieGetDetails = "person/popular";
        String URL = makeBaseUrl(movieGetDetails);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getArtistPopular(jsonObject));

            }
        });


    }



    public void getSearchOneMovie(String movieName, String year , final OnMovieDataSuccess listener)
    {
        movieGetDetails = "search/movie";
        String URL = makeBaseUrl(movieGetDetails)+"&query="+movieName;
        if(year != null)
            URL+="&year="+year;
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                ArrayList<SearchItem> searchItems = new ArrayList<>();
              searchItems.addAll( controller.getSearchDoneMovie(jsonObject));
if(searchItems.size() == 0)
    return;
listener.onSuccess(searchItems.get(0).getMovie());

            }
        });
    }



    public void getGenresFromIds(final Movie movie , final OnMovieDataSuccess listener)
    {
        getGenres(new OnMovieDataSuccess() {
            @Override
            public void onSuccess(Movie movie1) {

                HashMap<Integer,String> map = new HashMap<>();
                for(int i = 0 ;i < movie1.getGenres().size();i++)
                {
                    map.put(movie1.getGenres().get(i).getId(),movie1.getGenres().get(i).getName());
                }
                for(int i =0;i<movie.getGenres().size();i++)
                {
                    movie.getGenres().get(i).setName(map.get(movie.getGenres().get(i).getId()));
                }
listener.onSuccess(movie);
            }
        });
    }
}
