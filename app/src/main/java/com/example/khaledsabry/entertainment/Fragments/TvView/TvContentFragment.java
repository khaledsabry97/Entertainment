package com.example.khaledsabry.entertainment.Fragments.TvView;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Adapters.SeasonAdapter;
import com.example.khaledsabry.entertainment.Items.Episode;
import com.example.khaledsabry.entertainment.Items.Season;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

public class TvContentFragment extends Fragment {

    static Tv tv;
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;

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
        recyclerView = view.findViewById(R.id.recycler_id);
        drawerLayout = view.findViewById(R.id.drawer_layout_id);


        setupRecyclerView();
        return view;


    }

    /**
     * setup the recycler view to show the seasons for a tv
     */
    private void setupRecyclerView() {
        drawerLayout.openDrawer(GravityCompat.END,true);
        SeasonAdapter seasonAdapter = new SeasonAdapter(tv.getSeasons(), tv.getId(),drawerLayout);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(seasonAdapter);
        recyclerView.setHasFixedSize(true);
    }

    public static void loadEpisodesFragment(ArrayList<Episode> episodes) {
        MainActivity.loadFragmentNoReturn(R.id.episodesId, EpisodeRecyclerFragment.newInstance(episodes));

    }


    public static void loadSeasonEpisodePreviewFragment(Object object) {
        MainActivity.loadFragmentNoReturn(R.id.previewId, EpisodeSeasonPreviewFragment.newInstance(tv,object));

    }



}
