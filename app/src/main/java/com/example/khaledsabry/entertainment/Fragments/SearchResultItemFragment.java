package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;


public class SearchResultItemFragment extends Fragment {
    static Movie movie;

    public static SearchResultItemFragment newInstance(Movie movie) {
        SearchResultItemFragment fragment = new SearchResultItemFragment();
SearchResultItemFragment.movie = movie;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_result_item, container, false);


        return view;
    }

}
