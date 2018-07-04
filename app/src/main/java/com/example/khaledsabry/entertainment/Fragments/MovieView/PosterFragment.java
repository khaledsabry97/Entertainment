package com.example.khaledsabry.entertainment.Fragments.MovieView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapter.PosterAdapter;
import com.example.khaledsabry.entertainment.Controllers.MovieController;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;


public class PosterFragment extends Fragment {
    static  Movie movie;
    static int currentId =-1;
    static int movieId;
    RecyclerView recyclerView;
    ArrayList<String> imgs = new ArrayList<>();
    MovieController movieController = new MovieController();


    public static PosterFragment newInstance(int movieId) {
        PosterFragment fragment = new PosterFragment();
        PosterFragment.movieId = movieId;

        PosterFragment.movie = movie;

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_poster, container, false);

         recyclerView = view.findViewById(R.id.contentPanel);






        loadMovie();
        return view;
    }

    private void setObjects() {
        imgs.addAll(movie.getBackdrops());

        imgs.addAll(movie.getPosters());
        PosterAdapter posterAdapter = new PosterAdapter(imgs);
        recyclerView.setAdapter(posterAdapter);
        recyclerView.setHasFixedSize(true);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4,GridLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(gridLayoutManager);
    }

    private void loadMovie()
    {
        if(movieId !=currentId)
            movieController.getImages(movieId, new OnMovieDataSuccess() {
                @Override
                public void onSuccess(Movie movie) {
                    PosterFragment.movie = movie;
                    PosterFragment.currentId = movieId;
                    setObjects();
                }
            });
        else
            setObjects();

    }

}
