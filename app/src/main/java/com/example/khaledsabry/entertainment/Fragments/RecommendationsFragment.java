package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapter.RecommendationsPagerAdapter;
import com.example.khaledsabry.entertainment.Connection.TmdbType;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieList;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecommendationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendationsFragment extends Fragment {
    ViewPager viewPager;
    CircleIndicator indicator;
    static int id;
    RecommendationsPagerAdapter recommendationsPagerAdapter;
    public static RecommendationsFragment newInstance(int id) {
        RecommendationsFragment fragment = new RecommendationsFragment();
RecommendationsFragment.id = id;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recommendations, container, false);


         viewPager = v.findViewById(R.id.recoviewPagerid);
         indicator = v.findViewById(R.id.recoindicatorid);

        TmdbType tmdbType = new TmdbType();
        tmdbType.getRecommondations(id, new OnMovieList() {
            @Override
            public void onMovieList(ArrayList<Movie> movies) {
                setObjects(movies);
            }
        });


        moveImage();


        return v;
    }

    private void setObjects(ArrayList<Movie> movies)
    {
         recommendationsPagerAdapter = new RecommendationsPagerAdapter(movies);

        viewPager.setAdapter(recommendationsPagerAdapter);
        indicator.setViewPager(viewPager);
    }


    private void moveImage()
    {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(recommendationsPagerAdapter ==null)
                    return;
                if (recommendationsPagerAdapter.getCount() == viewPager.getCurrentItem() + 1)
                    viewPager.setCurrentItem(0, true);
                else
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(runnable);

            }
        }, 1000, 2000);
    }

}
