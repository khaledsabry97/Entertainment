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
import com.example.khaledsabry.entertainment.Interfaces.OnTvSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;


public class PosterFragment extends Fragment {
    static Movie movie;
    static Tv tv;

    static int currentId = -1;
    static int contentId;
    RecyclerView recyclerView;
    ArrayList<String> imgs = new ArrayList<>();
    static Type type;
    MovieController movieController = new MovieController();
    PosterAdapter posterAdapter;

    public static PosterFragment newInstance(int contentId, Type type) {
        PosterFragment fragment = new PosterFragment();
        PosterFragment.contentId = contentId;
        PosterFragment.type = type;


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_poster, container, false);

        recyclerView = view.findViewById(R.id.contentPanel);


        if (type == Type.movie)
            loadMovie();
        else if (type == Type.tv)
            loadTv();


        recyclerView.setHasFixedSize(true);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4, GridLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(gridLayoutManager);
        return view;
    }

    private void setMovieImg() {
        imgs.addAll(movie.getBackdrops());

        imgs.addAll(movie.getPosters());
        posterAdapter = new PosterAdapter(imgs);
        recyclerView.setAdapter(posterAdapter);


    }

    private void loadMovie() {
        if (contentId != currentId)
            movieController.getMovieImages(contentId, new OnMovieDataSuccess() {
                @Override
                public void onSuccess(Movie movie) {
                    PosterFragment.movie = movie;
                    PosterFragment.currentId = contentId;

                    setMovieImg();
                }
            });
        else
            setMovieImg();

    }

    private void loadTv() {
        if (contentId != currentId)
            movieController.getTvImages(contentId, new OnTvSuccess() {
                @Override
                public void onSuccess(Tv tv) {
                    PosterFragment.tv = tv;
                    PosterFragment.currentId = contentId;

                    setTvImg();
                }
            });
        else
            setTvImg();
    }

    private void setTvImg() {

        imgs.addAll(tv.getBackdrops());

        imgs.addAll(tv.getPosters());
         posterAdapter = new PosterAdapter(imgs);
        recyclerView.setAdapter(posterAdapter);


    }

    public enum Type {
        movie,
        tv,
        artist
    }
}
