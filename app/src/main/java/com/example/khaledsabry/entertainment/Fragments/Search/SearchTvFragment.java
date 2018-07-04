package com.example.khaledsabry.entertainment.Fragments.Search;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;


public class SearchTvFragment extends Fragment {
    static Tv tv;
    public static SearchTvFragment newInstance(Tv tv) {
        SearchTvFragment fragment = new SearchTvFragment();
        SearchTvFragment.tv = tv;

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_tv, container, false);
        return view;
    }

}
