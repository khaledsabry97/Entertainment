package com.example.khaledsabry.entertainment.Fragments.MovieView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecommendedAndSimilarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendedAndSimilarFragment extends Fragment {

 static Movie movie;
static int movieId;

    public static RecommendedAndSimilarFragment newInstance(int movieId) {
        RecommendedAndSimilarFragment fragment = new RecommendedAndSimilarFragment();
RecommendedAndSimilarFragment.movieId = movieId;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recommended_and_similer, container, false);

        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.recommendedid,RecommendationsFragment.newInstance(movieId)).commit();
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.similarid,SimilarFragment.newInstance(movieId)).commit();



        return v;
    }

}
