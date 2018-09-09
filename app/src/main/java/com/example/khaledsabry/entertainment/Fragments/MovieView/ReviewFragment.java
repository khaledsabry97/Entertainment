package com.example.khaledsabry.entertainment.Fragments.MovieView;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.khaledsabry.entertainment.Adapters.ReviewAdapter;
import com.example.khaledsabry.entertainment.Connection.WebApi;
import com.example.khaledsabry.entertainment.Interfaces.OnWebSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Review;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;
import com.github.ybq.android.spinkit.style.Wave;

import java.util.ArrayList;


public class
ReviewFragment extends Fragment {

    Object content;
    Movie movie;
    Tv tv;

    RecyclerView recyclerView;
    TabLayout tabLayout;

    int lastIndex = -1;
    ProgressBar progressBar;

    ReviewAdapter reviewAdapter;

    public static ReviewFragment newInstance(Object content) {
        ReviewFragment fragment = new ReviewFragment();
        fragment.content = content;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        recyclerView = view.findViewById(R.id.recycler_id);
        tabLayout = view.findViewById(R.id.tablayout);
        progressBar = view.findViewById(R.id.progress_bar_id);


        progressBar.setIndeterminateDrawable(new Wave());
        determineTheContent();
       setUpRecycler();
        setUpTabLayout();
        return view;
    }

    private void setUpTabLayout() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getReviwes(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                return;
            }
        });
        getReviwes(0);
    }

    private void setUpRecycler() {
        reviewAdapter = new ReviewAdapter();
        recyclerView.setAdapter(reviewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setDrawingCacheEnabled(false);
    }

    /**
     * if the conversion works then return if not try on tv
     */
    private void determineTheContent() {
        try {
            movie = (Movie) content;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (movie != null)
            return;
        try {
            tv = (Tv) content;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void getReviwes(int pos) {

        if (movie != null)
            getMovieReviews(pos);
        if(tv != null)
            getTvReviews(pos);

    }

   void setReviews(ArrayList<Review> reviews)
   {
       progressBar.setVisibility(View.INVISIBLE);
       reviewAdapter.setReviews(reviews);

   }

    private void getTvReviews(int position) {
        if(position == lastIndex)
            return;
        lastIndex = position;
        reviewAdapter.setReviews(null);
        progressBar.setVisibility(View.VISIBLE);
        switch (position) {
            case 0:
                WebApi.getInstance().rottenTomatoesMovieReviewsAllCritics(movie.getTitle(), movie.getYear(), new OnWebSuccess.OnMovie() {
                            @Override
                            public void onSuccess(Movie movie1) {
                                setReviews(movie1.getReviews());

                            }
                        }
                );
                break;
            case 1:
                WebApi.getInstance().rottenTomatoesMovieReviewsTopCritics(movie.getTitle(), movie.getYear(), new OnWebSuccess.OnMovie() {
                            @Override
                            public void onSuccess(Movie movie1) {
                                setReviews(movie1.getReviews());

                            }
                        }
                );
                break;

            case 2:
                WebApi.getInstance().rottenTomatoesMovieReviewsFresh(movie.getTitle(), movie.getYear(), new OnWebSuccess.OnMovie() {
                            @Override
                            public void onSuccess(Movie movie1) {
                                setReviews(movie1.getReviews());

                            }
                        }
                );
                break;
            case 3:
                WebApi.getInstance().rottenTomatoesMovieReviewsRotten(movie.getTitle(), movie.getYear(), new OnWebSuccess.OnMovie() {
                            @Override
                            public void onSuccess(Movie movie1) {
                                setReviews(movie1.getReviews());

                            }
                        }
                );
                break;
        }
    }

    public void getMovieReviews(int position) {
        if(position == lastIndex)
            return;
        lastIndex = position;
        reviewAdapter.setReviews(null);
        progressBar.setVisibility(View.VISIBLE);
        switch (position) {
            case 0:
            WebApi.getInstance().rottenTomatoesMovieReviewsAllCritics(movie.getTitle(), movie.getYear(), new OnWebSuccess.OnMovie() {
                        @Override
                        public void onSuccess(Movie movie1) {
                            setReviews(movie1.getReviews());


                        }
                    }
            );
            break;
            case 1:
                WebApi.getInstance().rottenTomatoesMovieReviewsTopCritics(movie.getTitle(), movie.getYear(), new OnWebSuccess.OnMovie() {
                            @Override
                            public void onSuccess(Movie movie1) {
                                setReviews(movie1.getReviews());

                            }
                        }
                );
                break;

            case 2:
                WebApi.getInstance().rottenTomatoesMovieReviewsFresh(movie.getTitle(), movie.getYear(), new OnWebSuccess.OnMovie() {
                            @Override
                            public void onSuccess(Movie movie1) {
                                setReviews(movie1.getReviews());


                            }
                        }
                );
                break;
            case 3:
                WebApi.getInstance().rottenTomatoesMovieReviewsRotten(movie.getTitle(), movie.getYear(), new OnWebSuccess.OnMovie() {
                            @Override
                            public void onSuccess(Movie movie1) {
                                setReviews(movie1.getReviews());

                            }
                        }
                );
                break;
        }

    }
}
