package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Adapter.PosterAdapter;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class PosterFragment extends Fragment {
    static  Movie movie;

    public static PosterFragment newInstance(Movie movie) {
        PosterFragment fragment = new PosterFragment();

        PosterFragment.movie = movie;

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_poster, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.contentPanel);
        ArrayList<String> imgs = new ArrayList<>();
        imgs.addAll(movie.getBackdrops());

        imgs.addAll(movie.getPosters());
        PosterAdapter posterAdapter = new PosterAdapter(imgs);
        recyclerView.setAdapter(posterAdapter);
        recyclerView.setHasFixedSize(true);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4,GridLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(gridLayoutManager);




        return view;
    }

}
