package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Fragments.MovieView.CastFragment;
import com.example.khaledsabry.entertainment.Fragments.MovieView.CrewFragment;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;


public class CastAndCrewFragment extends Fragment {
    static Movie movie;

    public static CastAndCrewFragment newInstance(Movie movie) {
        CastAndCrewFragment fragment = new CastAndCrewFragment();
        CastAndCrewFragment.movie = movie;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_cast_and_crew, container, false);
        setCast();
        setCrew();
        return v;
    }

    private void setCast()
    {
        CastFragment castFragment = CastFragment.newInstance(movie);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.castId,castFragment).commit();
    }
    void setCrew()
    {
        CrewFragment crewFragment = CrewFragment.newInstance(movie);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.crewid,crewFragment).commit();
    }

}
