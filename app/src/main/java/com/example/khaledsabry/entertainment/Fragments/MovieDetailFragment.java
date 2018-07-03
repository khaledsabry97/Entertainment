package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Adapter.MainPosterViewPager;
import com.example.khaledsabry.entertainment.Adapter.ReviewPageAdapter;
import com.example.khaledsabry.entertainment.Controllers.MovieController;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.R;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class MovieDetailFragment extends Fragment {

    static int movieId;
    TextView title;
    static Movie movie = null;

    static int currentMovieId = -1;
    TextView overviewText;
    TextView releaseDate;
    TextView runTimeText;
    TextView genres;
    TextView budget;
    TextView revenue;
    TextView rate;
    TextView adult;
    TextView status;
    CircleIndicator indicator;
    ViewPager viewPager;
    MainPosterViewPager viewPagerAdapter;
    Button actorButton;
    Button crewButton;
    NestedScrollView scrollView;

    public static MovieDetailFragment newInstance(int movieId) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        MovieDetailFragment.movieId = movieId;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             // Inflate the layout for this fragment
                             Bundle savedInstanceState) {
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
        indicator = v.findViewById(R.id.indicator);
        revenue = v.findViewById(R.id.revenueid);
        actorButton = v.findViewById(R.id.button_actors_id);
        crewButton = v.findViewById(R.id.button_crew_id);
        scrollView = v.findViewById(R.id.sideid);
        scrollView.setVisibility(View.INVISIBLE);

        getMovieDetails();
        return v;
    }


    private void setObjects(Movie movie) {
        overviewText.setText(movie.getOverView());
        releaseDate.setText(movie.getReleaseDate());
        runTimeText.setText(movie.getRunTime() + " min");
        genres.setText(movie.getGenreList());
        revenue.setText(movie.getRevneue());
        adult.setText((movie.isAdult()));
        budget.setText(movie.getBudget());
        status.setText(movie.getStatus());
        rate.setText(movie.getTmdbRate() + "/10");
        title.setText(movie.getTitle());
        viewPagerAdapter = new MainPosterViewPager(movie.getPosters());
        viewPager.setAdapter(viewPagerAdapter);
        indicator.setViewPager(viewPager);


        loadActorFragment();
        loadReviewFragment();
        movePoster();
        scrollView.setVisibility(View.VISIBLE);


        actorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActorFragment();

            }
        });

        crewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCrewFragment();
            }
        });
        MainActivity.getActivity().loadFragment(R.id.productionframelayoutid, ProductionCompanyFragment.newInstance(movie));

    }


    private void movePoster() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (viewPagerAdapter == null || viewPager == null)
                    return;
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
    }


    public void getMovieDetails() {
        MovieController movieController = new MovieController();
        if (movieId != currentMovieId)
            movieController.getMovieGetDetails(movieId, new OnMovieDataSuccess() {
                @Override
                public void onSuccess(Movie movie) {
                    currentMovieId = movie.getMovieId();
                    MovieDetailFragment.movie = movie;
                    setObjects(movie);
                }
            });
        else
            setObjects(movie);
    }

    private void loadActorFragment() {
        CastFragment castFragment = CastFragment.newInstance(movie);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actors_crews_id, castFragment).commit();
    }

    private void loadCrewFragment() {
        CrewFragment crewFragment = CrewFragment.newInstance(movie);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actors_crews_id, crewFragment).commit();
    }

    private void loadReviewFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ReviewLayoutid, ReviewFragment.newInstance(movieId)).commit();
    }


}
