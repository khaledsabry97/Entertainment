package com.example.khaledsabry.entertainment.Fragments.MovieView;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapter.RecommendationsPagerAdapter;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecommendedAndSimilarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendedAndSimilarFragment extends Fragment {

    private ArrayList<Movie> recommendedMovies = new ArrayList<>();
    private ArrayList<Movie> similarMovies = new ArrayList<>();


    ViewPager viewPager;
    CircleIndicator indicator;

    RecommendationsPagerAdapter recommendationsPagerAdapter;

    public static RecommendedAndSimilarFragment newInstance(ArrayList<Movie> recommendedMovies, ArrayList<Movie> similarMovies) {
        RecommendedAndSimilarFragment fragment = new RecommendedAndSimilarFragment();
        fragment.recommendedMovies = recommendedMovies;
        fragment.similarMovies = similarMovies;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recommended_and_similer, container, false);


        viewPager = v.findViewById(R.id.recoviewPagerid);
        indicator = v.findViewById(R.id.recoindicatorid);

        setObjects(recommendedMovies);
        moveFragment();
        return v;
    }


    private void setObjects(ArrayList<Movie> movies) {
        recommendationsPagerAdapter = new RecommendationsPagerAdapter(movies);

        viewPager.setAdapter(recommendationsPagerAdapter);
        indicator.setViewPager(viewPager);
    }

    private void moveFragment() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (recommendationsPagerAdapter == null)
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
        }, 3000, 3000);

    }
}
