package com.example.khaledsabry.entertainment.Fragments.MovieView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Adapter.MainPosterViewPager;
import com.example.khaledsabry.entertainment.Controllers.Functions;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.R;

import me.relex.circleindicator.CircleIndicator;


public class MovieMainFragment extends Fragment {

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
    static FrameLayout reviewLayout;

    public static MovieMainFragment newInstance(int movieId) {
        MovieMainFragment fragment = new MovieMainFragment();
        MovieMainFragment.movieId = movieId;
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
        reviewLayout = v.findViewById(R.id.ReviewLayoutid);
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
        loadProductionFragment();

        Functions functions = new Functions();
        functions.movePoster(viewPager,viewPagerAdapter,3000,2000);
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

    }

    private void loadProductionFragment() {
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.productionframelayoutid, ProductionCompanyFragment.newInstance(movie.getProductionCompanies())).commit();

    }


    public void getMovieDetails() {
        TmdbController tmdbController = new TmdbController();
        if (movieId != currentMovieId)
            tmdbController.getMovieGetDetails(movieId, new OnMovieDataSuccess() {
                @Override
                public void onSuccess(Movie movie) {
                    currentMovieId = movie.getMovieId();
                    MovieMainFragment.movie = movie;
                    setObjects(movie);
                }
            });
        else
            setObjects(movie);
    }

    private void loadActorFragment() {
        CastFragment castFragment = CastFragment.newInstance(movie.getCharacters());
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actors_crews_id, castFragment).commit();
    }

    private void loadCrewFragment() {
        CrewFragment crewFragment = CrewFragment.newInstance(movie.getCrews());
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actors_crews_id, crewFragment).commit();
    }

    private void loadReviewFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ReviewLayoutid, ReviewFragment.newInstance(movie.getReviews())).commit();
    }

public static void hideReviewView()
{
    reviewLayout.setVisibility(View.GONE);
}

}
