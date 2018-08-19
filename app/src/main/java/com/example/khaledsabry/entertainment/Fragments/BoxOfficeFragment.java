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
import com.example.khaledsabry.entertainment.Interfaces.OnRecyclerViewSuccess;
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
    BoxOfficeAdapter dailyAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;
LinearLayoutManager linearLayoutManager;
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
        // recyclerView = view.findViewById(R.id.recyclerid);
        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewPagerid);

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        adapter = new BoxOfficeAdapter();
        dailyAdapter = new BoxOfficeAdapter();
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return setBoxOffice();

                    case 1:
                        return setDailyBoxOffice();
                    default:
                        return BoxOfficeFragment.newInstance();
                }


            }

            @Override
            public int getCount() {
                return 2;
            }
        });
        setBoxOffice();

        return view;
    }


    private Fragment setBoxOffice() {

        //set the header title to box office
        header.setText("Box Office");
 RecyclerViewPager fragment = RecyclerViewPager.newInstance(adapter, new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false), new OnRecyclerViewSuccess() {
     @Override
     public void onSuccess(RecyclerView recyclerView) {
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
                                 adapter.setData(classification);
                             }
                         });
                     }
                 });
             }
         });
     }
 });
              return fragment;

 }


    private Fragment setDailyBoxOffice() {
        //set the header title to box office
        header.setText("Today's Box Office");

     RecyclerViewPager fragment = RecyclerViewPager.newInstance(dailyAdapter, new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false), new OnRecyclerViewSuccess() {
            @Override
            public void onSuccess(RecyclerView recyclerView) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        //get box office
                        WebApi.getInstance().mojoDaily(0, new OnWebSuccess.OnMovieList() {
                            @Override
                            public void onSuccess(ArrayList<Movie> movies) {
                                final Classification classification = new Classification();

                                classification.setType(Classification.type.dailyboxoffice);
                                classification.setSearchItems(Functions.movies(movies, null, 10));

                                MainActivity.getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        adapter.setData(classification);

                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

     return fragment;


    }


    /* setTabsTitles(tabLayout);
    private void setTabLayout() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        setBoxOffice();
                        break;
                    case 1:
                        setDailyBoxOffice();
                        break;
                    default:
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    */
}
