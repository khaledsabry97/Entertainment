package com.example.khaledsabry.entertainment.Fragments.MovieView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapter.ReviewPageAdapter;
import com.example.khaledsabry.entertainment.Controllers.MovieController;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewFragment extends Fragment {

    static int movieId;
    static int currentId;

    static Movie movie;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    MovieController movieController = new MovieController();

    public static ReviewFragment newInstance(int movieId) {
        ReviewFragment fragment = new ReviewFragment();
        ReviewFragment.movieId = movieId;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_review, container, false);

        viewPager = v.findViewById(R.id.viewPagerid);
        //    circleIndicator = v.findViewById(R.id.circleIndicatorid);

        loadFragment();
        return v;
    }

    private void loadFragment() {
        if (currentId != movieId)
            movieController.getReviews(movieId, new OnMovieDataSuccess() {
                @Override
                public void onSuccess(Movie movie) {
                    currentId = movieId;
                    ReviewFragment.movie = movie;
                    setObjects();
                }
            });
        else
            setObjects();
    }

    private void setObjects() {
        if (movie.getReviews().size() == 0) {
            MovieDetailFragment.hideReviewView();

        } else {
            ReviewPageAdapter reviewPageAdapter = new ReviewPageAdapter(movie.getReviews());
            viewPager.setAdapter(reviewPageAdapter);
            //   circleIndicator.setViewPager(viewPager);

        }
    }

}
