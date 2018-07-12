package com.example.khaledsabry.entertainment.Fragments.Tv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.R;


public class EpisodeMainFragment extends Fragment {

    public static EpisodeMainFragment newInstance() {
        EpisodeMainFragment fragment = new EpisodeMainFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_episode_main, container, false);
        return view;
    }

}
