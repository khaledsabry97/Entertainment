package com.example.khaledsabry.entertainment.Fragments.TvViews;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.khaledsabry.entertainment.Adapters.TvRecommendationsPagerAdapter;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class TvRecommendedAndSimilarFragment extends Fragment {
    //list of movies to show them

    ArrayList<Tv> recommendedTvs;
    ArrayList<Tv> similarTvs;

    //to stop auto scroll
    LabeledSwitch autoChange;


    ViewPager viewPager;
    CircleIndicator indicator;


    TvRecommendationsPagerAdapter tvRecommendationsPagerAdapter;

    public static TvRecommendedAndSimilarFragment newInstance(ArrayList<Tv> recommendedTvs, ArrayList<Tv> similarTvs) {
        TvRecommendedAndSimilarFragment fragment = new TvRecommendedAndSimilarFragment();
        fragment.recommendedTvs = recommendedTvs;
        fragment.similarTvs = similarTvs;
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

        setObjects(recommendedTvs);
        moveFragment();
        return view;
    }

    /**
     * we pass a list of movies to show similar/recommended
     *
     * @param tvs list of movies to show
     */
    private void setObjects(ArrayList<Tv> tvs) {
        tvRecommendationsPagerAdapter = new TvRecommendationsPagerAdapter(tvs);

        autoChange.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                autoChange.setEnabled(!autoChange.isEnabled());
            }
        });
        viewPager.setAdapter(tvRecommendationsPagerAdapter);
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
                if (tvRecommendationsPagerAdapter == null || !autoChange.isOn())
                    return;
                if (tvRecommendationsPagerAdapter.getCount() == viewPager.getCurrentItem() + 1)
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
