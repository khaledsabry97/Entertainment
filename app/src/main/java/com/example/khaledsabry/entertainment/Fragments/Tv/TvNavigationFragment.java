package com.example.khaledsabry.entertainment.Fragments.Tv;


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
import com.example.khaledsabry.entertainment.Controllers.CategoryController;
import com.example.khaledsabry.entertainment.Controllers.Functions;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Fragments.ImagesFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnTvSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

public class TvNavigationFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {
    //to set tv id
    int tvId;

    //to set later the index of the navigation items
    static int index = -1;

    //navigation item ids
    int NavigationId;

    //mainTv get the details for (MovieMainFragment,DownloadFragment)
    private Tv mainTv = null;

    //imagesTv gets the details for ImagesFragment
    private Tv imagesTv = null;
    private ArrayList<Tv> recommendedMovies;
    private ArrayList<Tv> similarMovies;

    //to get info from the tmdb
    TmdbController tmdbController = new TmdbController();
    CategoryController categoryController = new CategoryController();

    //to navigate to different topics for movie
    BottomNavigationView bottomNavigationView;

    static int id = -200;
    int mainView = R.id.home;
    int images = R.id.images;
    int seasons = R.id.seasons;
    int backButtonid = R.id.backButtonid;

    public static TvNavigationFragment newInstance(int tvId, int index) {
        TvNavigationFragment fragment = new TvNavigationFragment();
        fragment.tvId = tvId;
        TvNavigationFragment.index = index;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_detail, container, false);
        bottomNavigationView = view.findViewById(R.id.navigation);
        setUpBottomNavigation();

        return view;
    }

    void loadFragment(Fragment fragment) {
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.moviedetailid, fragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Functions.stopConnectionsAndStartImageGlide();

        TvNavigationFragment.id = item.getItemId();
        if (id == backButtonid)
            getActivity().getSupportFragmentManager().popBackStack();
        else if (id == mainView)
            loadFragment(TvMainFragment.newInstance(tvId));
        else if (id == images)
            loadFragment(ImagesFragment.newInstance(null, null));
        else if (id == seasons)
            loadSeasonsFragment();

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


    private void setUpBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        setNavigationIndex(index);
        bottomNavigationView.setSelectedItemId(NavigationId);
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
