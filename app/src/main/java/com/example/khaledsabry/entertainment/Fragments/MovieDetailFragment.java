package com.example.khaledsabry.entertainment.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Adapter.ViewPagerAdapter;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.R;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class MovieDetailFragment extends Fragment {

    ImageView posterImage;
    TextView title;
    static Movie movie;

    TextView overviewText;
    TextView releaseDate;
    TextView runTimeText;
    TextView genres;

    TextView budget;
    TextView rate;
    TextView adult;
    TextView status;

    int counterPosters = 0;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    public static MovieDetailFragment newInstance(Movie movie) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.movie = movie;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.movie_detail_v3, container, false);
        title = v.findViewById(R.id.titleId);
        overviewText = v.findViewById(R.id.overviewID);
        releaseDate = v.findViewById(R.id.releasetimeid);
        runTimeText = v.findViewById(R.id.timeid);
        genres = v.findViewById(R.id.genresid);
        budget = v.findViewById(R.id.budgetid);
        rate = v.findViewById(R.id.rateid);
        status = v.findViewById(R.id.statusid);
        adult = v.findViewById(R.id.adultid);
        viewPager = v.findViewById(R.id.viewPagerid);
        viewPagerAdapter = new ViewPagerAdapter(movie.getPosters());
        viewPager.setAdapter(viewPagerAdapter);
        final CircleIndicator indicator = (CircleIndicator) v.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);

        setObjects();


        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (viewPagerAdapter.getCount() == viewPager.getCurrentItem() + 1)
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
        }, 2000, 2000);


        return v;
    }


    private void setObjects() {
        overviewText.setText(movie.getOverView());
        releaseDate.setText(movie.getReleaseDate());
        runTimeText.setText(movie.getRunTime() + " min");
        genres.setText(movie.getGenreList());

        adult.setText((movie.isAdult()));
        budget.setText(movie.getBudget() + "");
        status.setText(movie.getStatus());
        rate.setText(movie.getTmdbRate() + "/10");
        title.setText(movie.getTitle());

        MainActivity.getActivity().loadFragment(R.id.productionframelayoutid,ProductionCompanyFragment.newInstance(movie));

    }


}
