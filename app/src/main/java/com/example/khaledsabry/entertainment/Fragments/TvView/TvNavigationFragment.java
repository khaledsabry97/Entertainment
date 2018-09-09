package com.example.khaledsabry.entertainment.Fragments.TvView;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.CategoryController;
import com.example.khaledsabry.entertainment.Controllers.Functions;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Database.Origin.LiteDatabaseTables;
import com.example.khaledsabry.entertainment.Fragments.YoutubeFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnTvList;
import com.example.khaledsabry.entertainment.Interfaces.OnTvSuccess;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;
import com.github.ybq.android.spinkit.style.Wave;

import java.util.ArrayList;

public class TvNavigationFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {
    ProgressBar progressBar;
    //boolean must be called so you decide to reselect or not
    boolean isFirstTime = false;
    //to set tv id
    int tvId;

    //to set later the index of the navigation items
    int index = -1;

    //navigation item ids
    int navigationId;

    //mainTv get the details for (MovieMainFragment,DownloadFragment)
    private Tv mainTv = null;

    //imagesTv gets the details for ImagesFragment
    private Tv imagesTv = null;
    private ArrayList<Tv> recommendedTvs;
    private ArrayList<Tv> similarTvs;

    //to get info from the tmdb
    TmdbController tmdbController = new TmdbController();
    CategoryController categoryController = new CategoryController();

    //to navigate to different topics for movie
    BottomNavigationView bottomNavigationView;

    public static TvNavigationFragment newInstance(final int tvId, int index) {
        final TvNavigationFragment fragment = new TvNavigationFragment();
        fragment.tvId = tvId;
        fragment.index = index;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                fragment.categoryController.addHistory(String.valueOf(tvId), null, LiteDatabaseTables.Category.constantTv);

            }
        });
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_detail, container, false);
        bottomNavigationView = view.findViewById(R.id.navigation);
        progressBar = view.findViewById(R.id.progress_bar_id);
        progressBar.setIndeterminateDrawable(new Wave());
        setUpBottomNavigation();

        return view;
    }


    private void setUpBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        isFirstTime = true;

        setNavigationIndex(index);
        bottomNavigationView.setSelectedItemId(navigationId);
        bottomNavigationView.setItemTextColor(ColorStateList.valueOf(Color.WHITE));

    }


    /**
     * to set navigationId to set later the index to show automatically
     *
     * @param index
     */
    private void setNavigationIndex(int index) {
        switch (index) {
            case 0:
                index = 0;
                navigationId = R.id.home;
                break;
            case 1:
                navigationId = R.id.navigation_seasons;
                break;
            case 2:
                navigationId = R.id.navigation_recommendation;
                break;
            case 3:
                navigationId = R.id.navigation_download;
                break;
            case 4:
                navigationId = R.id.navigation_youtube;
                break;
            default:
        }
    }

    void loadFragment(Fragment fragment) {
        progressBar.setVisibility(View.INVISIBLE);

        MainActivity.loadFragmentNoReturn(R.id.moviedetailid, fragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Functions.stopConnectionsAndStartImageGlide();
        if (navigationId == item.getItemId() && !isFirstTime) {
            return false;
        }
        progressBar.setVisibility(View.VISIBLE);
        isFirstTime = false;
        navigationId = item.getItemId();
        switch (navigationId) {
            case R.id.home:
                index = 0;
                loadTvMainFragment();
                break;
            case R.id.navigation_seasons:
                index = 1;
                loadSeasonsFragment();
                break;
            case R.id.navigation_recommendation:
                index = 2;
                loadRecommendationSimilarFragment();
                break;
            case R.id.navigation_youtube:
                loadYoutubeFragment();
                break;
        }
        return true;
    }


    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }

    /**
     * load the seasons fragment after you ge the main tv
     */
    private void loadSeasonsFragment() {
        if (mainTv == null) {
            tmdbController.getTv(tvId, new OnTvSuccess() {
                @Override
                public void onSuccess(Tv tv) {
                    mainTv = tv;
                    loadFragment(TvContentFragment.newInstance(mainTv));

                }
            });
        } else
            loadFragment(TvContentFragment.newInstance(mainTv));
    }


    /**
     * load the tv main view
     */
    void loadTvMainFragment() {
        if (mainTv == null)
            tmdbController.getTv(tvId, new OnTvSuccess() {
                @Override
                public void onSuccess(Tv tv) {
                    mainTv = tv;
                    loadFragment(TvMainFragment.newInstance(mainTv));
                }
            });

        else
            loadFragment(TvMainFragment.newInstance(mainTv));

    }

    /**
     * if there is no recommended tvs go and get it from tmdb server
     * if there is no similar tvs go and get it from tmdb server
     * when you get one of them check if the other is found or not if it's found
     * load the fragment if not wait and the other will repeat this check
     * if you are already have the two lists then you will load with the third option
     */
    private void loadRecommendationSimilarFragment() {
        //if the recommendedTvs is null
        //then you didn't get the movies, and same for similarmovies
        if (recommendedTvs == null) {
            tmdbController.getTvRecommendation(tvId, new OnTvList() {
                @Override
                public void onSuccess(ArrayList<Tv> tvs) {
                    recommendedTvs = tvs;
                    if (similarTvs != null)
                        loadFragment(TvRecommendedAndSimilarFragment.newInstance(recommendedTvs, similarTvs));
                }
            });

        }
        if (similarTvs == null) {
            tmdbController.getTvSimilar(tvId, new OnTvList() {
                @Override
                public void onSuccess(ArrayList<Tv> tvs) {
                    similarTvs = tvs;
                    if (recommendedTvs != null)
                        loadFragment(TvRecommendedAndSimilarFragment.newInstance(recommendedTvs, similarTvs));

                }
            });

        }


        if (similarTvs != null && recommendedTvs != null)
            loadFragment(TvRecommendedAndSimilarFragment.newInstance(recommendedTvs, similarTvs));


    }


    /**
     * load youtube for trailers and different things else
     */
    void loadYoutubeFragment() {
        if (mainTv == null)
            tmdbController.getTv(tvId, new OnTvSuccess() {
                @Override
                public void onSuccess(Tv tv) {
                    mainTv = tv;
                    loadFragment(YoutubeFragment.newInstance(mainTv, YoutubeFragment.Type.tv));

                }
            });


        else
            loadFragment(YoutubeFragment.newInstance(mainTv, YoutubeFragment.Type.tv));
    }
}
