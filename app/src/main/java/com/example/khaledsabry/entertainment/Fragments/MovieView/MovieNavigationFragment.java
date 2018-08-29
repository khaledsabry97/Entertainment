
package com.example.khaledsabry.entertainment.Fragments.MovieView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.Functions;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Fragments.ImagesFragment;
import com.example.khaledsabry.entertainment.Fragments.TorrentFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieList;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;
import com.example.khaledsabry.entertainment.Fragments.YoutubeFragment;

import java.util.ArrayList;


public class MovieNavigationFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {

    //to set movie id
    int movieId;

    //to set latter the index of the navigation items
    static int index = -1;

    //navigation item ids
    int NavigationId;

    //mainMovie get the details for (MovieMainFragment,DownloadFragment)
    private Movie mainMovie = null;
    //imagesMovie gets the details for ImagesFragment
    private Movie imagesMovie = null;
    private ArrayList<Movie> recommendedMovies = new ArrayList<>();
    private ArrayList<Movie> similarMovies = new ArrayList<>();
    //to get info from the tmdb
    TmdbController tmdbController = new TmdbController();

    //to navigate to different topics for movie
    BottomNavigationView bottomNavigationView;

    public static MovieNavigationFragment newInstance(int movieId, int index) {
        MovieNavigationFragment fragment = new MovieNavigationFragment();
        fragment.movieId = movieId;
        fragment.index = index;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie_navigation, container, false);
        bottomNavigationView = v.findViewById(R.id.navigation);

        setUpBottomNavigation();
        return v;
    }

    /**
     * setup the settings of the navigation view
     */
    private void setUpBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        setNavigationIndex(index);
        bottomNavigationView.setSelectedItemId(NavigationId);
        bottomNavigationView.setItemTextColor(ColorStateList.valueOf(Color.WHITE));

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Functions.stopConnectionsAndStartImageGlide();
        NavigationId = item.getItemId();
        switch (item.getItemId()) {
            case R.id.navigation_home:
                index = 0;
                loadMovieMainFragment();
                break;
            case R.id.navigation_back:
                index = 1;
                loadRecommendationSimilarFragment();
                break;
            case R.id.navigation_images:
                index = 2;
                loadImagesFragment();
                break;
            case R.id.navigation_download:
                index = 3;
                loadTorrentFragment();
                break;
            case R.id.navigation_youtube:
                index = 4;
                loadYoutubeFragment();
                break;
            default:
        }

        return true;
    }


    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {
        return;
    }

    /**
     * to load all the fragments in view
     *
     * @param fragment the fragment you want to show it
     */
    void loadFragment(Fragment fragment) {
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.moviedetailid, fragment).commit();
    }

    /**
     * load the movie main view
     */
    void loadMovieMainFragment() {
        if (mainMovie == null)
            tmdbController.getMovieGetDetails(movieId, new OnMovieDataSuccess() {
                @Override
                public void onSuccess(Movie movie) {
                    mainMovie = movie;
                    loadFragment(MovieMainFragment.newInstance(mainMovie));
                }
            });
        else
            loadFragment(MovieMainFragment.newInstance(mainMovie));

    }

    /**
     * if there is no recommended movies go and get it from tmdb server
     * if there is no similar movies go and get it from tmdb server
     * when you get one of them check if the other is found or not if it's found
     * load the fragment if not wait and the other will repeat this check
     * if you are already have the two lists then you will load with the third option
     */
    private void loadRecommendationSimilarFragment() {
        if (recommendedMovies.size() == 0) {
            tmdbController.getRecommendations(movieId, new OnMovieList() {
                @Override
                public void onMovieList(ArrayList<Movie> movies) {
                    recommendedMovies = movies;
                    if (similarMovies.size() != 0)
                        loadFragment(RecommendedAndSimilarFragment.newInstance(recommendedMovies, similarMovies));
                }
            });
        }
        if (similarMovies.size() == 0) {
            tmdbController.getSimilar(movieId, new OnMovieList() {
                @Override
                public void onMovieList(ArrayList<Movie> movies) {
                    similarMovies = movies;
                    if (recommendedMovies.size() != 0)
                        loadFragment(RecommendedAndSimilarFragment.newInstance(recommendedMovies, similarMovies));
                }
            });
        }


        if (similarMovies.size() != 0 && recommendedMovies.size() != 0)
            loadFragment(RecommendedAndSimilarFragment.newInstance(recommendedMovies, similarMovies));


    }

    /**
     * load the torrent download view
     */
    private void loadTorrentFragment() {
        if (mainMovie == null)
            tmdbController.getMovieGetDetails(movieId, new OnMovieDataSuccess() {
                @Override
                public void onSuccess(Movie movie) {
                    mainMovie = movie;
                    loadFragment(TorrentFragment.newInstance(movie.getTitle() + " " + movie.getYear()));
                }
            });
        else
            loadFragment(TorrentFragment.newInstance(mainMovie.getTitle() + " " + mainMovie.getYear()));
    }

    /**
     * load posters and backdrop images
     */
    void loadImagesFragment() {
        if (imagesMovie == null) {
            tmdbController.getMovieImages(movieId, new OnMovieDataSuccess() {
                @Override
                public void onSuccess(Movie movies) {
                    imagesMovie = movies;
                    MainActivity.loadFragmentWithReturn(R.id.moviedetailid, ImagesFragment.newInstance(imagesMovie.getPosters(), imagesMovie.getBackdrops()));
                }
            });
        } else {
            MainActivity.loadFragmentWithReturn(R.id.moviedetailid, ImagesFragment.newInstance(imagesMovie.getPosters(), imagesMovie.getBackdrops()));

        }
    }

    /**
     * load youtube for trailers
     */
    void loadYoutubeFragment() {
        if (mainMovie == null)
            tmdbController.getMovieGetDetails(movieId, new OnMovieDataSuccess() {
                @Override
                public void onSuccess(Movie movie) {
                    mainMovie = movie;
                    loadFragment(YoutubeFragment.newInstance(mainMovie, YoutubeFragment.Type.movie));

                }
            });
        else
            loadFragment(YoutubeFragment.newInstance(mainMovie, YoutubeFragment.Type.movie));
    }

    /**
     * to set navigationId to set later the index to show automatically
     *
     * @param index
     */
    private void setNavigationIndex(int index) {
        switch (index) {
            case 0:
                NavigationId = R.id.navigation_home;
                break;
            case 1:
                NavigationId = R.id.navigation_back;
                break;
            case 2:
                NavigationId = R.id.navigation_images;
                break;
            case 3:
                NavigationId = R.id.navigation_download;
                break;
            case 4:
                NavigationId = R.id.navigation_youtube;
                break;
            default:
        }
    }
}
