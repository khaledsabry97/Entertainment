package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieMoreDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieMoreDetailsFragment extends Fragment {

    public static MovieMoreDetailsFragment newInstance() {
        MovieMoreDetailsFragment fragment = new MovieMoreDetailsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie_more_details, container, false);


        return v;
    }

}
