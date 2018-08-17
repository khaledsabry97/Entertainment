package com.example.khaledsabry.entertainment.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Adapter.BoxOfficeAdapter;
import com.example.khaledsabry.entertainment.Connection.WebApi;
import com.example.khaledsabry.entertainment.Controllers.Functions;
import com.example.khaledsabry.entertainment.Interfaces.OnWebSuccess;
import com.example.khaledsabry.entertainment.Items.Classification;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;
import java.util.List;


public class BoxOfficeFragment extends Fragment {

    RecyclerView recyclerView;
    TextView header;
    BoxOfficeAdapter adapter;
ViewPager viewPager;
Fragment fragment;
    TabLayout tabLayout;

    public static BoxOfficeFragment newInstance() {
        BoxOfficeFragment fragment = new BoxOfficeFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_box_office, container, false);
        header = view.findViewById(R.id.header);
        recyclerView = view.findViewById(R.id.recyclerid);
        tabLayout = view.findViewById(R.id.tablayout);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position)
                {
                    case 0:
                        setBoxOffice();
                        break;
                    case 1:
                        setDailyBoxOffice();
                        break;
                };
                return fragment;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
        setTabLayout();
        setBoxOffice();

        return view;
    }


    private void setBoxOffice() {

        //set the header title to box office
        header.setText("Box Office");

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
                                adapter = new BoxOfficeAdapter(classification);
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


    private void setDailyBoxOffice() {

        //set the header title to box office
        header.setText("Today's Box Office");

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //get box office
                WebApi.getInstance().mojoBoxOffice(new OnWebSuccess.OnMovieList() {
                    @Override
                    public void onSuccess(ArrayList<Movie> movies) {
                        final Classification classification = new Classification();

                        classification.setType(Classification.type.dailyboxoffice);
                        classification.setSearchItems(Functions.movies(movies, null, 10));

                        MainActivity.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new BoxOfficeAdapter(classification);
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


    //  setTabsTitles(tabLayout);
private void setTabLayout()
{

}
}
