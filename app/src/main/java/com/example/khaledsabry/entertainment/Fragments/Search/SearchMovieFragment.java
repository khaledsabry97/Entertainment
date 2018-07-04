package com.example.khaledsabry.entertainment.Fragments.Search;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchMovieFragment extends Fragment {
static Movie movie;
    public static SearchMovieFragment newInstance(Movie movie) {
        SearchMovieFragment fragment = new SearchMovieFragment();
        SearchMovieFragment.movie = movie;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_movie, container, false);


        return view;
    }

}
