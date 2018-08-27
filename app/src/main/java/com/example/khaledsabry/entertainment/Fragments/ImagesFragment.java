package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Adapter.PosterAdapter;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnTvSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;


public class ImagesFragment extends Fragment {

    RecyclerView recyclerView;
    PosterAdapter posterAdapter;
    ImageView poster;
    ArrayList<String> posters;
    ArrayList<String> backDrops;
    Button fullScreen,fhd,hd,sd;

    public static ImagesFragment newInstance(ArrayList<String> posters,ArrayList<String> backDrops) {
        ImagesFragment fragment = new ImagesFragment();
        fragment.posters = posters;
        fragment.backDrops = backDrops;


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_poster, container, false);
        recyclerView = view.findViewById(R.id.contentPanel);
        poster = view.findViewById(R.id.posterzoom);
        fullScreen = view.findViewById(R.id.fullscreen);
        fhd = view.findViewById(R.id.fhd);
        hd = view.findViewById(R.id.hd);
        sd  = view.findViewById(R.id.sd);


        posterAdapter = new PosterAdapter(poster);
        posterAdapter.setData(posters);
        recyclerView.setAdapter(posterAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }

}
