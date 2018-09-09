package com.example.khaledsabry.entertainment.Fragments.TvView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapters.EpisodeAdapter;
import com.example.khaledsabry.entertainment.Items.Episode;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;


public class EpisodeRecyclerFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Episode> episodes = new ArrayList<>();

    public static EpisodeRecyclerFragment newInstance(ArrayList<Episode> episodes) {
        EpisodeRecyclerFragment fragment = new EpisodeRecyclerFragment();
fragment.episodes = episodes;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_episode_recycler, container, false);

        recyclerView = view.findViewById(R.id.recycler_id);


        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView() {

        EpisodeAdapter episodeAdapter = new EpisodeAdapter(episodes);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(episodeAdapter);
        recyclerView.setHasFixedSize(true);

    }

}
