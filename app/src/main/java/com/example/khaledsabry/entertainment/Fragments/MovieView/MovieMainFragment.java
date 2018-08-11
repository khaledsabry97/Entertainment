package com.example.khaledsabry.entertainment.Fragments.MovieView;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Adapter.MainPosterViewPager;
import com.example.khaledsabry.entertainment.Controllers.CategoryController;
import com.example.khaledsabry.entertainment.Controllers.Functions;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Database.Origin.DatabaseTables;
import com.example.khaledsabry.entertainment.Fragments.CategoryAddFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class MovieMainFragment extends Fragment {

    int movieId;
    static int currentMovieId = -1;
    Movie movie = null;

    TextView title;
    TextView overviewText;
    TextView releaseDate;
    TextView runTimeText;
    TextView genres;
    TextView budget;
    TextView revenue;
    TextView rate;
    TextView adult;
    TextView status;
    ImageView favourite;
    CircleIndicator indicator;
    ViewPager viewPager;
    MainPosterViewPager viewPagerAdapter;

    Button actorButton;
    Button crewButton;

    NestedScrollView scrollView;
    static FrameLayout reviewLayout;

    ImageView addNewCategory;
    ImageView addToCategory;
    public static ArrayList<String> categoryNames;
    public static ArrayList<Integer> categoryIds;
    public static ArrayList<Boolean> categoryCheacks;

    CategoryController categoryController = new CategoryController();

    public static MovieMainFragment newInstance(int movieId) {
        MovieMainFragment fragment = new MovieMainFragment();
        fragment.movieId = movieId;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             // Inflate the layout for this fragment
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie_main, container, false);
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
        favourite = v.findViewById(R.id.favourite);
        addNewCategory = v.findViewById(R.id.addnewcategory);
        addToCategory = v.findViewById(R.id.addtocategory);
        categoryController = new CategoryController();
        getMovieDetails();
        return v;
    }

    public void getMovieDetails() {
        movie = MovieNavigationFragment.movie;
        if (movieId != currentMovieId) {
            TmdbController tmdbController = new TmdbController();
            tmdbController.getMovieGetDetails(movieId, new OnMovieDataSuccess() {
                @Override
                public void onSuccess(Movie movie) {
                    currentMovieId = movie.getMovieId();
                    MovieNavigationFragment.movie = movie;
                    setObjects(movie);

                }
            });
        } else
            setObjects(movie);
    }


    private void setObjects(final Movie movie) {
        loadCategories();
        this.movie = movie;

        overviewText.setText(movie.getOverView());
        releaseDate.setText(movie.getReleaseDate());
        runTimeText.setText(movie.getRunTime() + " min");
        genres.setText(movie.getGenreList());
        revenue.setText(movie.getRevneue(true));
        adult.setText((movie.isAdult()));
        budget.setText(movie.getBudget());
        status.setText(movie.getStatus());
        rate.setText(movie.getTmdbRate() + "/10");
        title.setText(movie.getTitle());
        viewPagerAdapter = new MainPosterViewPager(movie.getPosters());
        viewPager.setAdapter(viewPagerAdapter);
        indicator.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // if
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        viewPagerAdapter.movePoster(viewPager, viewPagerAdapter, 7000, 4000);


        loadActorFragment();
        loadReviewFragment();
        loadProductionFragment();

        categoryController.addHistory(String.valueOf(movie.getMovieId()), movie.getMovieImdbId(), categoryController.constants.movie, new OnSuccess.bool() {
            @Override
            public void onSuccess(boolean state) {
            }
        });


        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryController.addFavourite(String.valueOf(movie.getMovieId()), movie.getMovieImdbId(), categoryController.constants.movie, new OnSuccess.bool() {
                    @Override
                    public void onSuccess(boolean state) {
                        if (state)
                            categoryController.toast(movie.getTitle() + " has been added to your Favourite list");

                    }
                });
            }
        });
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

        scrollView.setVisibility(View.VISIBLE);
    }


    private void loadProductionFragment() {
        if (movie == null)
            return;
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.productionframelayoutid, ProductionCompanyFragment.newInstance(movie.getProductionCompanies())).commit();

    }


    private void loadActorFragment() {
        if (movie == null)
            return;
        CastFragment castFragment = CastFragment.newInstance(movie.getCharacters());
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actors_crews_id, castFragment).commit();
    }

    private void loadCrewFragment() {
        if (movie == null)
            return;
        CrewFragment crewFragment = CrewFragment.newInstance(movie.getCrews());
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actors_crews_id, crewFragment).commit();
    }

    private void loadReviewFragment() {
        if (movie == null)
            return;
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ReviewLayoutid, ReviewFragment.newInstance(movie.getReviews())).commit();
    }

    public static void hideReviewView() {
        reviewLayout.setVisibility(View.GONE);
    }


    private void loadCategories() {
        categoryController.getCategories(movieId);
        addToCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (categoryIds != null)
                    openCategoryAdd(categoryNames, categoryIds, categoryCheacks);
            }
        });
    }


    public static void openCategoryAdd(ArrayList<String> names, ArrayList<Integer> ids, ArrayList<Boolean> booleans) {
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.mainContainer, CategoryAddFragment.newInstance(names, ids, booleans)).commit();
    }
}
