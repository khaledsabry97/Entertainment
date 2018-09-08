package com.example.khaledsabry.entertainment.Controllers;

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

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by KhALeD SaBrY on 26-Jun-18.
 */

public class TmdbController {
    private String urlAddition;
    private ApiConnections connection;

    private Tmdb controller;

    public TmdbController() {
        connection = ApiConnections.getInstance();
        controller = Tmdb.getInstance();
    }

    /**
     * concatenate the urlAddition to form url
     *
     * @param type the urlAddition
     * @return url to the website
     */
    private String makeBaseUrl(String type) {
        return Constants.tmdbBaseUrl + type + "?api_key=" + Constants.TmdbApiKey;
    }

    /**
     * get movie details,creditis,images,videos and reviews
     *
     * @param movieID  the id of the tmdb
     * @param listener returns movie with the details
     */
    public void getMovieGetDetails(int movieID, final OnMovieDataSuccess listener) {
        urlAddition = "movie/" + movieID;
        String URL = makeBaseUrl(urlAddition) + "&append_to_response=credits,images,videos,reviews";

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getMovieDetails(jsonObject));

            }
        });
    }


    /**
     * get the recomonded movies depending on a movie
     *
     * @param movieId  the movie id you want to get recommendation movies for it
     * @param listener return a list of recommended movies
     */
    public void getRecommendations(int movieId, final OnMovieList listener) {
        urlAddition = "movie/" + movieId + "/recommendations";
        String URL = makeBaseUrl(urlAddition);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onMovieList(controller.showRecommendationsOrSimilar(jsonObject));

            }
        });


    }

    /**
     * get the similar movies for a movie
     *
     * @param movieId  movie id you want to get the similar movies for it
     * @param listener returns a list of similar movies
     */
    public void getSimilar(int movieId, final OnMovieList listener) {
        urlAddition = "movie/" + movieId + "/similar";
        String URL = makeBaseUrl(urlAddition);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                // getMovieCast(jsonObject);
                listener.onMovieList(controller.showRecommendationsOrSimilar(jsonObject));

            }
        });


    }


    /**
     * get posters and backdrops for a movie
     *
     * @param movieID  the movie id you want to get info about
     * @param listener returns a movie with images
     */
    public void getMovieImages(int movieID, final OnMovieDataSuccess listener) {
        urlAddition = "movie/" + movieID + "/images";
        String URL = makeBaseUrl(urlAddition);

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getPostersBackdrops(jsonObject, new Movie()));

            }
        });
    }

    /**
     * get reviews from tmdb
     *
     * @param movieId  movie Id you want to get to it reviews
     * @param listener returns a movie with reviews
     */
    public void getReviews(int movieId, final OnMovieDataSuccess listener) {
        urlAddition = "movie/" + movieId + "/reviews";
        String URL = makeBaseUrl(urlAddition);

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                Movie movie = new Movie();
                movie.setReviews(controller.getReviews(jsonObject));
                listener.onSuccess(movie);

            }
        });
    }

    /**
     * search for celebrity,tv or movie
     *
     * @param query    the search text you wrote it
     * @param listener returns a list search items
     */
    public void search(String query, final OnSearchSuccess listener) {
        if (query.equals(""))
            return;
        urlAddition = "search/multi";
        String URL = makeBaseUrl(urlAddition) + "&query=" + query + "&page=1";
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getSearchDone(jsonObject));

            }
        });
    }

    /**
     * gets all the genres in the tmdb
     *
     * @param listener returns movie with all the genres
     * @deprecated this function is not used any more because,
     * i put the genres names directly in the class
     */
    public void getGenres(final OnMovieDataSuccess listener) {
        urlAddition = "genre/movie/categoryItem";
        String URL = makeBaseUrl(urlAddition);

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getGenres(jsonObject));

            }
        });
    }

    /**
     * get details about an artist
     *
     * @param personId artist id
     * @param listener returns an artist object
     */
    public void getPersonDetails(int personId, final OnArtistDataSuccess listener) {
        urlAddition = "person/" + personId;
        String URL = makeBaseUrl(urlAddition);

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getPersonDetails(jsonObject));

            }
        });
    }

    /**
     * gets movies and tvs that the artist contributed to it
     *
     * @param personId the artist id
     * @param listener returns an artist object
     */
    public void getPersonRoles(int personId, final OnArtistDataSuccess listener) {
        urlAddition = "person/" + personId + "/combined_credits";
        String URL = makeBaseUrl(urlAddition);
        urlAddition.equals(urlAddition);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getPersonRoles(jsonObject));

            }
        });
    }

    /**
     * gets an artist images
     *
     * @param personId artist id
     * @param listener returns an artist
     */
    public void getPersonImages(int personId, final OnArtistDataSuccess listener) {
        urlAddition = "person/" + personId + "/images";
        String URL = makeBaseUrl(urlAddition);

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getPersonImages(jsonObject));

            }
        });
    }


    /**
     * get tv details
     *
     * @param tvId     tv id you want to get info about
     * @param listener returns a tv object
     */
    public void getTv(int tvId, final OnTvSuccess listener) {
        urlAddition = "tv/" + tvId;
        String URL = makeBaseUrl(urlAddition) + "&append_to_response=credits,images,videos,reviews";

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getTv(jsonObject, new Tv()));

            }
        });
    }

    /**
     * gets tv images
     *
     * @param tvId     tv id you want to get to it images
     * @param listener returns a tv object
     */
    public void getTvImages(int tvId, final OnTvSuccess listener) {
        urlAddition = "tv/" + tvId + "/images";
        String URL = makeBaseUrl(urlAddition);

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getPostersBackdrops(jsonObject, new Tv()));

            }
        });
    }

    /**
     * gets tv seasons info
     *
     * @param tvId     the tv id you want to get season to
     * @param seasonNo specific season number you want to get
     * @param listener returns season Object
     */
    public void getTvSeason(int tvId, int seasonNo, final OnSeasonSuccess listener) {
        urlAddition = "tv/" + tvId + "/season/" + seasonNo;
        String URL = makeBaseUrl(urlAddition);

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getSeason(jsonObject));

            }
        });
    }


    /**
     * get the movies that in theater right now
     *
     * @param listener return a list of movies
     */
    public void getMoviesNowPlaying(final OnMovieList listener) {
        urlAddition = "movie/now_playing";
        String URL = makeBaseUrl(urlAddition);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                // getMovieCast(jsonObject);
                listener.onMovieList(controller.getMovieNowPlaying(jsonObject));

            }
        });


    }

    /**
     * get popular movies
     *
     * @param listener return a list of movies
     */
    public void getMoviesPopular(final OnMovieList listener) {
        urlAddition = "movie/popular";
        String URL = makeBaseUrl(urlAddition);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onMovieList(controller.getMoviePopular(jsonObject));

            }
        });


    }

    /**
     * get a list of top rated movies in tmdb
     *
     * @param listener return a list of movies
     */
    public void getMoviesTopRated(final OnMovieList listener) {
        urlAddition = "movie/top_rated";
        String URL = makeBaseUrl(urlAddition);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onMovieList(controller.getMovieTopRated(jsonObject));

            }
        });


    }

    /**
     * gets a list of upcoming movies
     *
     * @param listener return a list of movies
     */
    public void getMoviesUpComing(final OnMovieList listener) {
        urlAddition = "movie/upcoming";
        String URL = makeBaseUrl(urlAddition);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onMovieList(controller.getMovieUpComing(jsonObject));

            }
        });


    }

    /**
     * get tv series that is shown on tv meanwhile
     *
     * @param listener return a list of tvs
     */
    public void getTvOnAir(final OnTvList listener) {
        urlAddition = "tv/on_the_air";
        String URL = makeBaseUrl(urlAddition);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                // getMovieCast(jsonObject);
                listener.onSuccess(controller.getTvOnAir(jsonObject));

            }
        });
    }

    /**
     * get tv series that is aired today on tv
     *
     * @param listener return a list of tv
     */
    public void getTvAirToday(final OnTvList listener) {
        urlAddition = "tv/airing_today";
        String URL = makeBaseUrl(urlAddition);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getTvAiringToday(jsonObject));

            }
        });
    }

    /**
     * gets tv that are popular meanwhile
     *
     * @param listener return a list of tv
     */
    public void getTvPopular(final OnTvList listener) {
        urlAddition = "tv/popular";
        String URL = makeBaseUrl(urlAddition);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                // getMovieCast(jsonObject);
                listener.onSuccess(controller.getTvPopular(jsonObject));

            }
        });
    }

    /**
     * gets tv series that are top rated on tmdb
     *
     * @param listener return a list of tv
     */
    public void getTvTopRated(final OnTvList listener) {
        urlAddition = "tv/top_rated";
        String URL = makeBaseUrl(urlAddition);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                // getMovieCast(jsonObject);
                listener.onSuccess(controller.getTvTopRated(jsonObject));

            }
        });
    }

    public void getTvRecommendation(int tvId,final OnTvList listener) {
        urlAddition = "tv/"+tvId+"/recommendations";
        String URL = makeBaseUrl(urlAddition);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                // getMovieCast(jsonObject);
                listener.onSuccess(controller.getTvRecommendationOrSimilar(jsonObject));

            }
        });
    }


    public void getTvSimilar(int tvId,final OnTvList listener) {
        urlAddition = "tv/"+tvId+"/similar";
        String URL = makeBaseUrl(urlAddition);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                // getMovieCast(jsonObject);
                listener.onSuccess(controller.getTvRecommendationOrSimilar(jsonObject));

            }
        });
    }

    /**
     * get artists that are popular meanwhile
     *
     * @param listener return a list of artist
     */
    public void getArtistPopular(final OnArtistDataSuccess.List listener) {
        urlAddition = "person/popular";
        String URL = makeBaseUrl(urlAddition);
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getArtistPopular(jsonObject));

            }
        });


    }

    /**
     * search on movie by its name
     *
     * @param movieName movie name
     * @param year      movie year that created
     * @param listener  return a movie
     */
    public void getSearchOneMovie(String movieName, String year, final OnMovieDataSuccess listener) {
        urlAddition = "search/movie";
        String URL = makeBaseUrl(urlAddition) + "&query=" + movieName;
        if (year != null)
            URL += "&year=" + year;
        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                ArrayList<SearchItem> searchItems = new ArrayList<>();
                searchItems.addAll(controller.getSearchDoneMovie(jsonObject));
                if (searchItems.size() == 0)
                    return;
                listener.onSuccess(searchItems.get(0).getMovie());

            }
        });
    }


    /**
     * get movie by imdb id
     *
     * @param imdbId   imdb id for a movie
     * @param listener return a movie object
     */
    public void getMovieByImdb(String imdbId, final OnMovieDataSuccess listener) {
        String url = "find/" + imdbId;
        url = makeBaseUrl(url) + "&external_source=imdb_id";
        connection.connect(url, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getMovieImdb(jsonObject));

            }
        });
    }


    public void getGenresFromIds(final Movie movie, final OnMovieDataSuccess listener) {
        getGenres(new OnMovieDataSuccess() {
            @Override
            public void onSuccess(Movie movie1) {

                HashMap<Integer, String> map = new HashMap<>();
                for (int i = 0; i < movie1.getGenres().size(); i++) {
                    map.put(movie1.getGenres().get(i).getId(), movie1.getGenres().get(i).getName());
                }
                for (int i = 0; i < movie.getGenres().size(); i++) {
                    movie.getGenres().get(i).setName(map.get(movie.getGenres().get(i).getId()));
                }
                listener.onSuccess(movie);
            }
        });
    }

    public void getMovieVideosCreditsCategories(int movieID, final OnMovieDataSuccess listener) {
        urlAddition = "movie/" + movieID;
        String URL = makeBaseUrl(urlAddition) + "&append_to_response=credits,videos";

        connection.connect(URL, new OnSuccess.Json() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                listener.onSuccess(controller.getMovieVideosCreditsCategories(jsonObject));

            }
        });
    }

}
