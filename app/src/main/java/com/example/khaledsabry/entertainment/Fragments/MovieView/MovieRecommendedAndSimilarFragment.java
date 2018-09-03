package com.example.khaledsabry.entertainment.Fragments.MovieView;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.khaledsabry.entertainment.Adapter.RecommendationsPagerAdapter;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieRecommendedAndSimilarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieRecommendedAndSimilarFragment extends Fragment {

    //list of movies to show them
    private ArrayList<Movie> recommendedMovies = new ArrayList<>();
    private ArrayList<Movie> similarMovies = new ArrayList<>();
    //to stop auto scroll
    LabeledSwitch autoChange;


    ViewPager viewPager;
    CircleIndicator indicator;

    RecommendationsPagerAdapter recommendationsPagerAdapter;

    public static MovieRecommendedAndSimilarFragment newInstance(ArrayList<Movie> recommendedMovies, ArrayList<Movie> similarMovies) {
        MovieRecommendedAndSimilarFragment fragment = new MovieRecommendedAndSimilarFragment();
        fragment.recommendedMovies = recommendedMovies;
        fragment.similarMovies = similarMovies;
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommended_and_similer, container, false);

        viewPager = view.findViewById(R.id.recoviewPagerid);
        indicator = view.findViewById(R.id.recoindicatorid);
        autoChange = view.findViewById(R.id.auto_change);

        //to bring auto scoll to front
        RelativeLayout relativeLayout = view.findViewById(R.id.relative_layout_id);
        relativeLayout.bringChildToFront(autoChange);

        setObjects(recommendedMovies);
        moveFragment();
        return view;
    }

    /**
     * we pass a list of movies to show similar/recommended
     * @param movies list of movies to show
     */
    private void setObjects(ArrayList<Movie> movies) {
        recommendationsPagerAdapter = new RecommendationsPagerAdapter(movies);

        autoChange.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                autoChange.setEnabled(!autoChange.isEnabled());
            }
        });
        viewPager.setAdapter(recommendationsPagerAdapter);
        indicator.setViewPager(viewPager);
    }

    /**
     * scroll the view of movies
     */
    private void moveFragment() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (recommendationsPagerAdapter == null || !autoChange.isOn())
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
        }, 4000, 4000);

    }
}
