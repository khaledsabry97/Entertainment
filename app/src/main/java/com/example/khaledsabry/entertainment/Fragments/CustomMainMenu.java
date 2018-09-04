package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapter.MainItemsAdapter;
import com.example.khaledsabry.entertainment.Items.Classification;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;


public class CustomMainMenu extends Fragment {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    MainItemsAdapter mainItemsAdapter;
    ArrayList<Classification> mainItems;

    public static CustomMainMenu newInstance(String param1, String param2) {
        CustomMainMenu fragment = new CustomMainMenu();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_custom_main_menu, container, false);
toolbar = view.findViewById(R.id.toolbar);
recyclerView =view.findViewById(R.id.recycler_id);
viewPager = view.findViewById(R.id.view_pager_id);


mainItemsAdapter = new MainItemsAdapter();
recyclerView.setAdapter(mainItemsAdapter);
recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

setMainItems();


        return view;
    }

    private void setMainItems() {
        Classification topMovies = new Classification();
        Classification topNews = new Classification();
        Classification latestDownload = new Classification();


        topMovies.setTitle("Top Movies");
        topNews.setTitle("Top News");
        latestDownload.setTitle("Latest Downloads");

        mainItems.add(topMovies);
        mainItems.add(topNews);
        mainItems.add(latestDownload);

        mainItemsAdapter.setClassifications(mainItems);

    }

}
