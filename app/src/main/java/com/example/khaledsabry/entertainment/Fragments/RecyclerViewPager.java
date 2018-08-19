package com.example.khaledsabry.entertainment.Fragments;


import android.content.ReceiverCallNotAllowedException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Adapter.BoxOfficeAdapter;
import com.example.khaledsabry.entertainment.Adapter.CastRecyclerAdapter;
import com.example.khaledsabry.entertainment.Adapter.RecommendationsPagerAdapter;
import com.example.khaledsabry.entertainment.Connection.WebApi;
import com.example.khaledsabry.entertainment.Controllers.Functions;
import com.example.khaledsabry.entertainment.Interfaces.OnRecyclerViewSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnWebSuccess;
import com.example.khaledsabry.entertainment.Items.Classification;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;


public class RecyclerViewPager extends Fragment {

    public enum ViewPagerType
    {
        //For box office
        boxOffice,
        dailyBoxOffice,

        //For Categories
        allCategories,
        movieCategory,
        tvCategory,
        artistCategory,

    }

    ViewPagerType type;
    /*
    public static RecyclerViewPager newInstance(ViewPagerType type) {
        RecyclerViewPager fragment = new RecyclerViewPager();
       fragment.type = type;
        return fragment;
    }
*/

    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    OnRecyclerViewSuccess listener;

    public static RecyclerViewPager newInstance(ViewPagerType type) {
        RecyclerViewPager fragment = new RecyclerViewPager();
        fragment.type = type;
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view_pager, container, false);
        recyclerView = view.findViewById(R.id.recyclerid);
        setRecycler();
        return view;
    }

    private void setRecycler() {
        switch (type)
        {
            case boxOffice:
                setBoxOffice();
                break;


            case dailyBoxOffice:
setDailyBoxOffice();
                break;

            case tvCategory:

                break;
            case allCategories:

                break;

            case movieCategory:


                break;

            case artistCategory:

                break;




            default:
        }
    }



    private void setDailyBoxOffice() {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //get box office
                WebApi.getInstance().mojoDaily(0,new OnWebSuccess.OnMovieList() {
                    @Override
                    public void onSuccess(ArrayList<Movie> movies) {
                        final Classification classification = new Classification();

                        classification.setType(Classification.type.dailyboxoffice);
                        classification.setSearchItems(Functions.movies(movies, null, 10));

                        MainActivity.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                             BoxOfficeAdapter   adapter = new BoxOfficeAdapter();
                                adapter.setData(classification);

                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

                            }
                        });
                    }
                });
            }
        });
    }



    private void setBoxOffice() {

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //get box office
                WebApi.getInstance().mojoBoxOffice(new OnWebSuccess.OnMovieList() {
                    @Override
                    public void onSuccess(ArrayList<Movie> movies) {
                        final Classification classification = new Classification();

                        classification.setType(Classification.type.boxoffice);
                        classification.setSearchItems(Functions.movies(movies, null, 10));

                        MainActivity.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            BoxOfficeAdapter    adapter = new BoxOfficeAdapter();
                            adapter.setData(classification);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

                            }
                        });
                    }
                });
            }
        });
    }

}
