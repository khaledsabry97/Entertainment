package com.example.khaledsabry.entertainment.Fragments.MovieView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

import java.util.HashMap;


public class MoviePreviewFragment extends Fragment {
static Movie movie;
ImageView poster;
TextView title;
TextView rate;
TextView overView;
TextView releaseDate;
TextView genres;

TmdbController tmdbController = new TmdbController();
    public static MoviePreviewFragment newInstance(Movie movie) {
        MoviePreviewFragment fragment = new MoviePreviewFragment();
        MoviePreviewFragment.movie = movie;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_movie, container, false);
        poster = view.findViewById(R.id.posterrelativelayout);
        title = view.findViewById(R.id.title);
        rate = view.findViewById(R.id.rateid);
        overView = view.findViewById(R.id.contentid);
        releaseDate = view.findViewById(R.id.releaseDateid);
        genres = view.findViewById(R.id.genresid);
        setObjects();

view.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, MovieNavigationFragment.newInstance(movie.getMovieId(),true)).addToBackStack(null).commit();
    }
});
        return view;
    }

    private void setObjects() {
        title.setText(movie.getTitle());
        rate.setText(movie.getTmdbRate()+"/10");
        overView.setText(movie.getOverView());
        releaseDate.setText("Release Date : "+movie.getReleaseDate());
        ImageController.putImageMidQuality(movie.getBackDropPoster(),poster);

        final Movie mov = movie;
tmdbController.getGenres(new OnMovieDataSuccess() {
    @Override
    public void onSuccess(Movie movie1) {

        HashMap<Integer,String> map = new HashMap<>();
        for(int i = 0 ;i < movie1.getGenres().size();i++)
        {
            map.put(movie1.getGenres().get(i).getId(),movie1.getGenres().get(i).getName());
        }
        for(int i =0;i<mov.getGenres().size();i++)
        {
            mov.getGenres().get(i).setName(map.get(mov.getGenres().get(i).getId()));
        }

        genres.setText(mov.getGenreList());
    }
});
    }

}
