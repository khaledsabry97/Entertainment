package com.example.khaledsabry.entertainment.Fragments.Tv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Interfaces.OnTvSuccess;
import com.example.khaledsabry.entertainment.Items.Episode;
import com.example.khaledsabry.entertainment.Items.Season;
import com.example.khaledsabry.entertainment.Items.Torrent;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

public class TvContentFragment extends Fragment {

     Tv tv;
    public static TvContentFragment newInstance(Tv tv) {
        TvContentFragment fragment = new TvContentFragment();
        fragment.tv = tv;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_content, container, false);
        loadSeasonsFragment();

        return view;


    }


    public static void loadEpisodesFragment(ArrayList<Episode> episodes) {
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.episodesId, EpisodeRecyclerFragment.newInstance(episodes)).commit();

    }

    private void loadSeasonsFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.seasonId, SeasonRecyclerFragment.newInstance(tv.getSeasons(), tv.getId())).commit();

    }

    public static void loadEpisodePreviewFragment(Episode episode) {
       // MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.previewId, EpisodeSeasonPreviewFragment.newInstance(tv,null,episode)).commit();

    }



    public static void loadSeasonPreviewFragment(Season season) {
      //  MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.previewId, EpisodeSeasonPreviewFragment.newInstance(tv,season,null)).commit();

    }



}
