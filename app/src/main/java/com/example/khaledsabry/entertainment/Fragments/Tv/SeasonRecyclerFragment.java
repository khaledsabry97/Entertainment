package com.example.khaledsabry.entertainment.Fragments.Tv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapter.SeasonAdapter;
import com.example.khaledsabry.entertainment.Items.Season;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

public class SeasonRecyclerFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Season> seasons = new ArrayList<>();
    public static int tvId;

    public static SeasonRecyclerFragment newInstance(ArrayList<Season> seasons, int tvId) {
        SeasonRecyclerFragment fragment = new SeasonRecyclerFragment();
        fragment.seasons = seasons;
        SeasonRecyclerFragment.tvId = tvId;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_season_recycler, container, false);
        recyclerView = view.findViewById(R.id.recyclerid);

        SeasonAdapter seasonAdapter = new SeasonAdapter(seasons);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(seasonAdapter);
        recyclerView.setHasFixedSize(true);
        return view;
    }

}
