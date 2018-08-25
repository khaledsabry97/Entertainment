package com.example.khaledsabry.entertainment.Fragments;


import android.app.ActionBar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Adapter.BoxOfficeAdapter;
import com.example.khaledsabry.entertainment.Connection.WebApi;
import com.example.khaledsabry.entertainment.Controllers.Functions;
import com.example.khaledsabry.entertainment.Interfaces.OnWebSuccess;
import com.example.khaledsabry.entertainment.Items.Classification;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.SearchItem;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;


public class TopGenresFragment extends Fragment {

    RecyclerView recyclerView;
    TextView header;
    BoxOfficeAdapter adapter;
    TabLayout tabLayout;
    LinearLayoutManager linearLayoutManager;

    // TODO: Rename and change types and number of parameters
    public static TopGenresFragment newInstance() {
        TopGenresFragment fragment = new TopGenresFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_genres, container, false);
        header = view.findViewById(R.id.header);
        recyclerView = view.findViewById(R.id.recyclerid);
        tabLayout = view.findViewById(R.id.tablayout);

        //addTabs();



        adapter = new BoxOfficeAdapter();
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                switch (tab.getPosition())
                {
                    //top 250
                    case 0:
                    {
                        getImdbTop250Movies();
                    }
                }
            }

            @Override
            public void onTabUnselected(Tab tab) {

            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        });


        try {
            getImdbTop250Movies();

        }
catch (Exception e)
{
    e.printStackTrace();
}
        return view;
    }

    private void getImdbTop250Movies() {
        WebApi.getInstance().imdbTop250Movies(new OnWebSuccess.OnMovie() {
            @Override
            public void onSuccess(final Movie movie) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        final Classification classification = new Classification();
                        classification.setType(Classification.type.imdbTop250);
                        ArrayList<SearchItem> searchItems = new ArrayList<>();
                        SearchItem searchItem = new SearchItem();
                        searchItem.setMovie(movie);
                        searchItems.add(searchItem);
                        classification.setSearchItems(searchItems);


                        MainActivity.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.addData(classification);
                                adapter.notifyItemInserted(adapter.classification.getSearchItems().size());
    //adapter.notifyDataSetChanged();

                            }
                        });
                    }
                });

            }
        });
    }
/*
    private void addTabs() {
        TabItem tabItem = new TabItem(getContext());

        tabItem.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tabLayout.addView(tabItem,0);
    }
*/
}
