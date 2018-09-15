package com.example.khaledsabry.entertainment.Fragments.ArtistView;


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
import com.example.khaledsabry.entertainment.Interfaces.OnArtistDataSuccess;
import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.R;
import com.felix.bottomnavygation.BottomNav;
import com.felix.bottomnavygation.ItemNav;
import com.github.ybq.android.spinkit.style.Wave;


public class ArtistNavigationFragment extends Fragment {
    ProgressBar progressBar;

    //to set artist id
    int artistId;

    //to set later the index of the navigation items
    int index = -1;

    //navigation item ids
    int navigationId;
    //boolean must be called so you decide to reselect or not
    boolean isFirstTime = false;

    //mainArtist get the details
    private Artist mainArtist = null;
    private Artist artistRoles = null;

    //private Movie imagesMovie = null;
    // private ArrayList<Movie> recommendedMovies;
    // private ArrayList<Movie> similarMovies;

    BottomNav bottomNav;
    //to get info from the tmdb
    TmdbController tmdbController = new TmdbController();
    CategoryController categoryController = new CategoryController();

    //to navigate to different topics for movie
    // BottomNavigationView bottomNavigationView;


    public static ArtistNavigationFragment newInstance(final int artistId, int index) {
        final ArtistNavigationFragment fragment = new ArtistNavigationFragment();
        fragment.artistId = artistId;
        fragment.index = index;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                fragment.categoryController.addHistory(String.valueOf(artistId), null, LiteDatabaseTables.Category.constantArtist);

            }
        });
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artist_navigation, container, false);

        bottomNav = view.findViewById(R.id.bottom_navigation_id);

        setUpBottomNav();
        progressBar = view.findViewById(R.id.progress_bar_id);
        progressBar.setIndeterminateDrawable(new Wave());
        return view;
    }


    private void setUpBottomNav() {
        bottomNav.addItemNav(new ItemNav(getContext(), R.drawable.commentaccountoutline, "Info").addColorAtive(R.color.blue));
        bottomNav.addItemNav(new ItemNav(getContext(), R.drawable.bookopenpagevariant, "Roles").addColorAtive(R.color.blue));


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
    void loadArtistMainFragment() {
        if (mainArtist == null)
            tmdbController.getPersonDetails(artistId, new OnArtistDataSuccess() {
                @Override
                public void onSuccess(Artist artist) {
                    mainArtist = artist;
                    loadFragment(ArtistMainFragment.newInstance(mainArtist));
                }
            });

        else
            loadFragment(ArtistMainFragment.newInstance(mainArtist));


    }

    /**
     * to set navigationId to set later the index to show automatically
     *
     * @param index
     */
    private void setNavigationIndex(int index) {
        progressBar.setVisibility(View.VISIBLE);
        switch (index) {
            case 0:
                loadArtistMainFragment();
                navigationId = R.id.navigation_home;
                break;
            case 1:
                loadArtistRolesFragment();
                navigationId = R.id.navigation_recommendation;
                break;
        }
    }

    /**
     * if there is no recommended movies go and get it from tmdb server
     * if there is no similar movies go and get it from tmdb server
     * when you get one of them check if the other is found or not if it's found
     * load the fragment if not wait and the other will repeat this check
     * if you are already have the two lists then you will load with the third option
     */
    private void loadArtistRolesFragment() {
       if(artistRoles == null)
       {
           tmdbController.getPersonRoles(artistId, new OnArtistDataSuccess() {
               @Override
               public void onSuccess(Artist artist) {
                   artistRoles = artist;
                   loadFragment(ArtistRolesFragment.newInstance(artistRoles));
               }
           });
       }
       else
           loadFragment(ArtistRolesFragment.newInstance(artistRoles));
    }
}
