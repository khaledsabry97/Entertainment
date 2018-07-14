package com.example.khaledsabry.entertainment.Fragments.MovieView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Adapter.ReviewPageAdapter;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Review;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;


public class ReviewFragment extends Fragment {

    static int movieId;
    static int currentId;

    static Movie movie;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    TmdbController tmdbController = new TmdbController();
    View v;
    private static ArrayList<Review> reviews = new ArrayList<>();
    public static ReviewFragment newInstance(ArrayList<Review> reviews) {
        ReviewFragment fragment = new ReviewFragment();
        ReviewFragment.reviews = reviews;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v = inflater.inflate(R.layout.fragment_review, container, false);

        viewPager = v.findViewById(R.id.viewPagerid);
        //    circleIndicator = v.findViewById(R.id.circleIndicatorid);
v.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.moviedetailid,ReviewFragment.newInstance(reviews)).commit();
    }
});
        loadFragment();
        return v;
    }

    private void loadFragment() {
        if (currentId != movieId)
            tmdbController.getReviews(movieId, new OnMovieDataSuccess() {
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
        if (reviews.size() == 0) {
            v.setVisibility(View.GONE);
        } else {
            ReviewPageAdapter reviewPageAdapter = new ReviewPageAdapter(reviews);
            viewPager.setAdapter(reviewPageAdapter);
            //   circleIndicator.setViewPager(viewPager);

        }
    }

}
