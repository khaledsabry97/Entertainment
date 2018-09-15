package com.example.khaledsabry.entertainment.Fragments.TvViews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapters.SeasonAdapter;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;

public class SeasonRecyclerFragment extends Fragment {

    RecyclerView recyclerView;
    Tv tv;
    DrawerLayout drawerLayout;

    public static SeasonRecyclerFragment newInstance(Tv tv) {
        SeasonRecyclerFragment fragment = new SeasonRecyclerFragment();
        fragment.tv = tv;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_season_recycler, container, false);
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

}
