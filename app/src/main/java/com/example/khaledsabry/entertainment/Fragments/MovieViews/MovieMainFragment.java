package com.example.khaledsabry.entertainment.Fragments.MovieViews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Adapters.MainPosterViewPager;
import com.example.khaledsabry.entertainment.Controllers.CategoryController;
import com.example.khaledsabry.entertainment.Controllers.Toasts;
import com.example.khaledsabry.entertainment.Fragments.CategoryAddFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;


public class MovieMainFragment extends Fragment {

    CircleIndicator indicator;
    ViewPager viewPager;
    MainPosterViewPager viewPagerAdapter;
    ImageView addToCategory, addFavourite, addWatchLater;
    Movie movie;
    TextView reviews, overview;
    DrawerLayout drawerLayout;


    CategoryController categoryController = new CategoryController();
    public  ArrayList<String> categoryNames;
    public  ArrayList<Integer> categoryIds;
    public  ArrayList<Boolean> categoryChecked;

    public static MovieMainFragment newInstance(Movie movie) {
        MovieMainFragment fragment = new MovieMainFragment();
        fragment.movie = movie;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             // Inflate the layout for this fragment
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_main, container, false);
        viewPager = view.findViewById(R.id.view_pager_id);
        indicator = view.findViewById(R.id.indicator);
        reviews = view.findViewById(R.id.reviews_id);
        overview = view.findViewById(R.id.overview_id);
        addToCategory = view.findViewById(R.id.add_to_category_id);
        drawerLayout = view.findViewById(R.id.drawer_layout);
        addWatchLater = view.findViewById(R.id.add_to_watch_later_id);
        addFavourite = view.findViewById(R.id.add_to_favourite_id);
        categoryController = new CategoryController();

        setObjects();
        return view;
    }

    /**
     * set the main poster functionality
     * get the categories and set it's buttons response
     * set up the ability to get overview fragment
     * set up the ability to get review fragment
     * upload automatically the overview fragment
     */
    private void setObjects() {

        setUpViewPager();

        setUpCategories();

        setUpOverviewFragment();
        setUpReviewsFragment();

        loadOverviewFragment();

    }

    private void loadOverviewFragment() {
        loadFragment(MovieOverviewFragment.newInstance(movie));
    }

    private void setUpReviewsFragment() {
        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(ReviewFragment.newInstance(movie));
            }
        });
    }

    void loadFragment(Fragment fragment) {
        drawerLayout.closeDrawer(GravityCompat.END, true);
        MainActivity.loadFragmentNoReturn(R.id.half_frame_layout, fragment);
    }

    private void setUpOverviewFragment() {
        overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadOverviewFragment();
            }
        });
    }

    private void setUpCategories() {
        loadCategories();
        addToCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAddToCategory();
            }
        });

        addFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAddFavourite();
            }
        });


        addWatchLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAddWatchLater();
            }
        });


    }

    private void setUpViewPager() {
        viewPagerAdapter = new MainPosterViewPager(movie.getPosters());
        viewPager.setAdapter(viewPagerAdapter);
        indicator.setViewPager(viewPager);

        viewPagerAdapter.movePoster(viewPager, viewPagerAdapter, 7000, 4000);

    }


    private void loadCategories() {
        categoryController.getCategories(movie.getId(), 1, new OnSuccess.objects() {
            @Override
            public void onSuccess(ArrayList<Object> objects) {
                categoryIds = (ArrayList<Integer>) objects.get(0);
                categoryNames = (ArrayList<String>) objects.get(1);
                categoryChecked = (ArrayList<Boolean>) objects.get(2);
            }
        });
    }


    void setAddToCategory() {
        if (categoryIds != null)
            openCategoryAdd(categoryNames, categoryIds, categoryChecked);
        else
            loadCategories();
    }

    public void openCategoryAdd(ArrayList<String> names, ArrayList<Integer> ids, ArrayList<Boolean> booleans) {
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.mainContainer, CategoryAddFragment.newInstance(names, ids, booleans, 1, String.valueOf(movie.getId()))).commit();
    }


    void setAddFavourite() {
        categoryController.addFavourite(String.valueOf(movie.getId()), movie.getMovieImdbId(), categoryController.constants.movie, new OnSuccess.bool() {
            @Override
            public void onSuccess(boolean state) {
                if (state) {
                    loadCategories();
                    Toasts.success(movie.getTitle() + " has been added to your favourites");
                }
                else
                    Toasts.error(movie.getTitle() + " failed to be added to your favourites");

            }
        });
    }

    void setAddWatchLater() {

    }

}