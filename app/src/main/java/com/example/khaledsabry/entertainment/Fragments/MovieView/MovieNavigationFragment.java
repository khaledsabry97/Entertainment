
package com.example.khaledsabry.entertainment.Fragments.MovieView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.CategoryController;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Database.Origin.LiteDatabaseTables;
import com.example.khaledsabry.entertainment.Fragments.ImagesFragment;
import com.example.khaledsabry.entertainment.Fragments.TorrentFragment;
import com.example.khaledsabry.entertainment.Fragments.YoutubeFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieList;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;
import com.felix.bottomnavygation.BottomNav;
import com.felix.bottomnavygation.ItemNav;
import com.github.ybq.android.spinkit.style.Wave;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;

import java.util.ArrayList;


public class MovieNavigationFragment extends Fragment implements OnBottomNavigationItemClickListener {

    ProgressBar progressBar;
    //to set movie id
    int movieId;

    //to set later the index of the navigation items
    int index = -1;

    //navigation item ids
    int navigationId;
    //boolean must be called so you decide to reselect or not
    boolean isFirstTime = false;

    //mainMovie get the details for (MovieMainFragment,DownloadFragment)
    private Movie mainMovie = null;

    //imagesMovie gets the details for ImagesFragment
    private Movie imagesMovie = null;
    private ArrayList<Movie> recommendedMovies;
    private ArrayList<Movie> similarMovies;

    BottomNav bottomNav;
    //to get info from the tmdb
    TmdbController tmdbController = new TmdbController();
    CategoryController categoryController = new CategoryController();

    //to navigate to different topics for movie
    // BottomNavigationView bottomNavigationView;

    public static MovieNavigationFragment newInstance(final int movieId, int index) {
        final MovieNavigationFragment fragment = new MovieNavigationFragment();
        fragment.movieId = movieId;
        fragment.index = index;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                fragment.categoryController.addHistory(String.valueOf(movieId), null, LiteDatabaseTables.Category.constantMovie);

            }
        });
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_navigation, container, false);
        // bottomNavigationView = view.findViewById(R.id.navigation);
        bottomNav = view.findViewById(R.id.bottom_navigation_id);

        setUpBottomNav();
        progressBar = view.findViewById(R.id.progress_bar_id);
        progressBar.setIndeterminateDrawable(new Wave());
        setUpBottomNavigation();
        return view;
    }


    private void setUpBottomNav() {
        bottomNav.addItemNav(new ItemNav(getContext(), R.drawable.ic_home_black_24dp, "Info").addColorAtive(R.color.white));
        bottomNav.addItemNav(new ItemNav(getContext(), R.mipmap.outline_theaters_black_18, "Similars").addColorAtive(R.color.white));
        bottomNav.addItemNav(new ItemNav(getContext(), R.drawable.ic_favorite_black_24dp, "Recommendations").addColorAtive(R.color.white));
        bottomNav.addItemNav(new ItemNav(getContext(), R.drawable.images, "Images").addColorAtive(R.color.white));
        bottomNav.addItemNav(new ItemNav(getContext(), R.drawable.download, "Download").addColorAtive(R.color.white));
        bottomNav.addItemNav(new ItemNav(getContext(), R.drawable.youtube, "Youtube").addColorAtive(R.color.white));
        bottomNav.addItemNav(new ItemNav(getContext(), R.drawable.youtube, R.drawable.download).isProfileItem().addProfileColorAtive(R.color.white).addProfileColorInative(R.color.blue));


        bottomNav.setTabSelectedListener(new BottomNav.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int i) {
                setNavigationIndex(i);
            }

            @Override
            public void onTabLongSelected(int i) {

            }
        });
        bottomNav.build();
        bottomNav.selectTab(index);
    }

    private void set(View view) {
        com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView bottomNavigationView = (com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView) view.findViewById(R.id.bottom_navigation_id);
        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("Info", ContextCompat.getColor(getContext(), R.color.blue), R.drawable.black);

        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("Images", ContextCompat.getColor(getContext(), R.color.gray), R.drawable.black);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("Download", ContextCompat.getColor(getContext(), R.color.gray), R.drawable.black);

        BottomNavigationItem bottomNavigationItem3 = new BottomNavigationItem
                ("Youtube", ContextCompat.getColor(getContext(), R.color.gray), R.drawable.black);
        BottomNavigationItem bottomNavigationItem4 = new BottomNavigationItem
                ("Youtube", ContextCompat.getColor(getContext(), R.color.gray), R.drawable.black);
        BottomNavigationItem bottomNavigationItem5 = new BottomNavigationItem
                ("Youtube", ContextCompat.getColor(getContext(), R.color.gray), R.drawable.black);

        bottomNavigationView.addTab(bottomNavigationItem);
        bottomNavigationView.addTab(bottomNavigationItem1);
        bottomNavigationView.addTab(bottomNavigationItem2);
        bottomNavigationView.addTab(bottomNavigationItem3);
        bottomNavigationView.addTab(bottomNavigationItem4);
        bottomNavigationView.addTab(bottomNavigationItem5);
        bottomNavigationView.setOnBottomNavigationItemClickListener(this);
        bottomNavigationView.selectTab(0);
        bottomNavigationView.disableShadow();
    }

    /**
     * setup the settings of the navigation view
     */
    private void setUpBottomNavigation() {






/*
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        setNavigationIndex(index);
        isFirstTime = true;
        bottomNavigationView.setSelectedItemId(navigationId);
        bottomNavigationView.setItemTextColor(ColorStateList.valueOf(Color.WHITE));*/
    }

    /**
     * to set navigationId to set later the index to show automatically
     *
     * @param index
     */
    private void setNavigationIndex(int index) {
        switch (index) {
            case 0:
                loadMovieMainFragment();
                navigationId = R.id.navigation_home;
                break;
            case 1:
                loadRecommendationSimilarFragment();
                navigationId = R.id.navigation_recommendation;
                break;
            case 2:
                loadRecommendationSimilarFragment();
                navigationId = R.id.navigation_images;
                break;
            case 3:
                loadImagesFragment();
                navigationId = R.id.navigation_download;
                break;
            case 4:
                loadTorrentFragment();
                navigationId = R.id.navigation_youtube;
                break;
            case 5:
                loadYoutubeFragment();
                navigationId = R.id.navigation_youtube;
                break;
            default:
        }
    }
/*
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Functions.stopConnectionsAndStartImageGlide();
        if (navigationId == item.getItemId() && !isFirstTime) {
            return false;
        }
        progressBar.setVisibility(View.VISIBLE);
        isFirstTime = false;
        navigationId = item.getItemId();
        switch (item.getItemId()) {
            case R.id.navigation_home:
                index = 0;
                loadMovieMainFragment();
                break;
            case R.id.navigation_recommendation:
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

    */

    /**
     * to load all the fragments in view
     *
     * @param fragment the fragment you want to show it
     */
    void loadFragment(Fragment fragment) {
        progressBar.setVisibility(View.INVISIBLE);
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.moviedetailid, fragment).commit();
    }

    /**
     * load the movie main view
     */
    void loadMovieMainFragment() {
        if (mainMovie == null)
            tmdbController.getMovie(movieId, new OnMovieDataSuccess() {
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
        //if the recommendedMovies is null
        //then you didn't get the movies, and same for similarmovies
        if (recommendedMovies == null) {
            tmdbController.getRecommendations(movieId, new OnMovieList() {
                @Override
                public void onMovieList(ArrayList<Movie> movies) {
                    recommendedMovies = movies;
                    if (similarMovies != null)
                        loadFragment(MovieRecommendedAndSimilarFragment.newInstance(recommendedMovies, similarMovies));
                }
            });
        }
        if (similarMovies == null) {
            tmdbController.getSimilar(movieId, new OnMovieList() {
                @Override
                public void onMovieList(ArrayList<Movie> movies) {
                    similarMovies = movies;
                    if (recommendedMovies != null)
                        loadFragment(MovieRecommendedAndSimilarFragment.newInstance(recommendedMovies, similarMovies));
                }
            });
        }


        if (similarMovies != null && recommendedMovies != null)
            loadFragment(MovieRecommendedAndSimilarFragment.newInstance(recommendedMovies, similarMovies));


    }

    /**
     * load the torrent download view
     */
    private void loadTorrentFragment() {
        if (mainMovie == null)
            tmdbController.getMovie(movieId, new OnMovieDataSuccess() {
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
                    loadFragment(ImagesFragment.newInstance(imagesMovie.getPosters(), imagesMovie.getBackdrops()));
                }
            });
        } else {
            loadFragment(ImagesFragment.newInstance(imagesMovie.getPosters(), imagesMovie.getBackdrops()));

        }
    }

    /**
     * load youtube for trailers
     */
    void loadYoutubeFragment() {
        if (mainMovie == null)
            tmdbController.getMovie(movieId, new OnMovieDataSuccess() {
                @Override
                public void onSuccess(Movie movie) {
                    mainMovie = movie;
                    loadFragment(YoutubeFragment.newInstance(mainMovie, YoutubeFragment.Type.movie));

                }
            });
        else
            loadFragment(YoutubeFragment.newInstance(mainMovie, YoutubeFragment.Type.movie));
    }


    @Override
    public void onNavigationItemClick(int index) {
        loadMovieMainFragment();
    }
}
