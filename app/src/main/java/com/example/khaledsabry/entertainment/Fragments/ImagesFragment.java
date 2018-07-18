package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapter.PosterAdapter;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnTvSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;


public class ImagesFragment extends Fragment {
    static Movie movie;
    static Tv tv;

    static int currentId = -1;
    static int contentId;
    RecyclerView recyclerView;
    ArrayList<String> imgs = new ArrayList<>();
    static Type type;
    TmdbController tmdbController = new TmdbController();
    PosterAdapter posterAdapter;

    public static ImagesFragment newInstance(int contentId, Type type) {
        ImagesFragment fragment = new ImagesFragment();
        ImagesFragment.contentId = contentId;
        ImagesFragment.type = type;


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


      //  GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4, GridLayoutManager.VERTICAL, false);
     //   recyclerView.setLayoutManager(gridLayoutManager);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.GAP_HANDLING_NONE);
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
            tmdbController.getMovieImages(contentId, new OnMovieDataSuccess() {
                @Override
                public void onSuccess(Movie movie) {
                    ImagesFragment.movie = movie;
                    ImagesFragment.currentId = contentId;

                    setMovieImg();
                }
            });
        else
            setMovieImg();

    }

    private void loadTv() {
        if (contentId != currentId)
            tmdbController.getTvImages(contentId, new OnTvSuccess() {
                @Override
                public void onSuccess(Tv tv) {
                    ImagesFragment.tv = tv;
                    ImagesFragment.currentId = contentId;

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
