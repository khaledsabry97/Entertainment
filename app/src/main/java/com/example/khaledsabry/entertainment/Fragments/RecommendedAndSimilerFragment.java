package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.test.mock.MockDialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecommendedAndSimilerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendedAndSimilerFragment extends Fragment {

 static Movie movie;


    public static RecommendedAndSimilerFragment newInstance(Movie movie) {
        RecommendedAndSimilerFragment fragment = new RecommendedAndSimilerFragment();
RecommendedAndSimilerFragment.movie = movie;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recommended_and_similer, container, false);

        MainActivity.getActivity().loadFragment(R.id.recommendedid,RecommendationsFragment.newInstance(movie.getMovieId()));
        MainActivity.getActivity().loadFragment(R.id.similarid,SimilarFragment.newInstance(movie.getMovieId()));

        return v;
    }

}
