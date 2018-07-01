package com.example.khaledsabry.entertainment.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;


public class DetailFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {

static Movie movie;
static int id = -200 ;
    public static DetailFragment newInstance(Movie movie) {
        DetailFragment fragment = new DetailFragment();
        DetailFragment.movie = movie;
        if(DetailFragment.id == -200)
        DetailFragment.id = R.id.navigation_home;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        BottomNavigationView bottomNavigationView = v.findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemReselectedListener(this);


        bottomNavigationView.setSelectedItemId(id);




        return v;
    }

    void loadFragment(Fragment fragment)
    {
        MainActivity.getActivity().loadFragment(R.id.moviedetailid,fragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DetailFragment.id = item.getItemId();

        if(item.getItemId() == R.id.navigation_home) {
            loadFragment(MovieDetailFragment.newInstance(movie));

        }
        else if(item.getItemId() == R.id.navigation_dashboard)
            loadFragment(CastAndCrewFragment.newInstance(movie));
        else if(item.getItemId() == R.id.navigation_notifications)
            loadFragment(PosterFragment.newInstance(movie));
        return true;
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {
        DetailFragment.id = item.getItemId();

        if(item.getItemId() == R.id.navigation_home)
            loadFragment(MovieDetailFragment.newInstance(movie));
        else if(item.getItemId() == R.id.navigation_dashboard)
            loadFragment(CastAndCrewFragment.newInstance(movie));
    }
}
