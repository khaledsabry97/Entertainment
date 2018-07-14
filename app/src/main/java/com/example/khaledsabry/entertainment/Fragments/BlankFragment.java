package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Connection.ApiConnections;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.R;


public class BlankFragment extends Fragment {

    MainActivity mainActivity;
    public static BlankFragment newInstance() {
        BlankFragment fragment = new BlankFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v  = inflater.inflate(R.layout.fragment_blank, container, false);
        ApiConnections.getInstance().setContext(getContext());
        TmdbController tmdbController = new TmdbController();
        tmdbController.getMovieGetDetails(68735, new OnMovieDataSuccess() {
            @Override
            public void onSuccess(Movie movie) {
             //   loadMovieDetails(movie);
            }
        });





        return v;
    }

/*
    public void loadMovieDetails(Movie movie)
    {
        mainActivity = (MainActivity) getActivity();
        mainActivity.loadMovieDetailFragment(movie);

    }*/

}
