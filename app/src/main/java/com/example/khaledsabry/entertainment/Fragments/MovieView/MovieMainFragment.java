package com.example.khaledsabry.entertainment.Fragments.MovieView;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.khaledsabry.entertainment.Adapter.MainPosterViewPager;
import com.example.khaledsabry.entertainment.Connection.WebApi;
import com.example.khaledsabry.entertainment.Controllers.CategoryController;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Fragments.CategoryAddFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnMovieDataSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnWebSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;


public class MovieMainFragment extends Fragment {

    int movieId;
    static int currentMovieId = -1;
    Movie movie = null;

    //Layouts
    NestedScrollView scrollView;
    static FrameLayout reviewLayout;
    LinearLayout writeNewCategoryLayout;

    TextView title;
    TextView overviewText;
    TextView releaseDate;
    TextView runTimeText;
    TextView genres;
    TextView budget;
    TextView revenue;
    TextView rate;
    TextView mpaa;
    TextView status;
    ImageView favourite;
    TextView imdbRating;
    TextView tomatoesRating;
    CircleIndicator indicator;
    ViewPager viewPager;
    MainPosterViewPager viewPagerAdapter;
    ImageView tomatoesPoster;

    Button actorButton;
    Button crewButton;


    EditText categoryNew;
    Button addCategoryButton;
    ImageView addNewCategory;
    ImageView addToCategory;
    public static ArrayList<String> categoryNames;
    public static ArrayList<Integer> categoryIds;
    public static ArrayList<Boolean> categoryCheacks;

    CategoryController categoryController = new CategoryController();

    public static MovieMainFragment newInstance(Movie movie) {
        MovieMainFragment fragment = new MovieMainFragment();
        fragment.movie = movie;
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
        mpaa = v.findViewById(R.id.mpaaid);
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
        writeNewCategoryLayout = v.findViewById(R.id.addnew);
        categoryNew = v.findViewById(R.id.categorytext);
        addCategoryButton = v.findViewById(R.id.addcategory);
        imdbRating = v.findViewById(R.id.imdbrate);
        tomatoesRating = v.findViewById(R.id.tomatoesrate);
        tomatoesPoster = v.findViewById(R.id.tomatoesposter);
        categoryController = new CategoryController();
        try {
            setObjects();
        } catch (Exception e) {
            e.printStackTrace();
        }
     /*   try {
            getMovieDetails();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/
        return v;
    }
/*
    public void getMovieDetails() throws Exception {
       movie = MovieNavigationFragment.movie;
        if (movieId != currentMovieId) {
            TmdbController tmdbController = new TmdbController();
            tmdbController.getMovieGetDetails(movieId, new OnMovieDataSuccess() {
                @Override
                public void onSuccess(Movie movie) {
                    currentMovieId = movie.getMovieId();
                    MovieNavigationFragment.movie = movie;
                    try {
                        setObjects(movie);

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }
            });
        } else
            setObjects(movie);
    }

*/
    private void setObjects() throws Exception {
        loadCategories();
        getImdbInfo();
        getRottenTomatoesInfo();

        overviewText.setText(movie.getOverView());
        releaseDate.setText(movie.getReleaseDate());
        runTimeText.setText(movie.getRunTime() + " min");
        genres.setText(movie.getGenreList());
        revenue.setText(movie.getRevneue(true));
        budget.setText(movie.getBudget());
        status.setText(movie.getStatus());
        rate.setText(movie.getTmdbRate() + "");
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
                            categoryController.toast(movie.getTitle() + " has been added to your Favourite categoryItem");

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
                    openCategoryAdd(categoryNames, categoryIds, categoryCheacks, movieId);
            }
        });


        addNewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewCategoryLayout.setVisibility(View.VISIBLE);
                addCategoryButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setAddNewCategory(categoryNew.getText().toString());

                    }
                });
            }
        });
    }


    public void openCategoryAdd(ArrayList<String> names, ArrayList<Integer> ids, ArrayList<Boolean> booleans, int movieId) {
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.mainContainer, CategoryAddFragment.newInstance(names, ids, booleans, 1, String.valueOf(movieId))).commit();
    }

    public void setAddNewCategory(String categoryName) {
        if (!categoryName.isEmpty()) {
            categoryController.addMovieCategory(categoryName, new OnSuccess.bool() {
                @Override
                public void onSuccess(boolean state) {

                }
            });
            writeNewCategoryLayout.setVisibility(View.GONE);
            categoryNew.setText("");
        } else
            categoryController.toast("write a name for the category");

    }


    public void getImdbInfo() {
        if (movie.getMovieImdbId() == null)
            return;
        WebApi.getInstance().imdbMovieDetails(movie.getMovieImdbId(), new OnWebSuccess.OnMovie() {
            @Override
            public void onSuccess(Movie movie) {
                if (!String.valueOf(movie.getImdbRate()).equals("0.0"))
                    imdbRating.setText(String.valueOf(movie.getImdbRate()));
                if (!String.valueOf(movie.getMpaa()).equals(""))
                    mpaa.setText(movie.getMpaa());
            }
        });
    }

    public void getRottenTomatoesInfo() throws Exception {

        AsyncTask.execute(new Runnable() {
                              @Override
                              public void run() {
                                  WebApi.getInstance().rottenTomatoesMoviePreview(movie.getTitle(), movie.getYear(), new OnWebSuccess.OnMovie() {
                                      @Override
                                      public void onSuccess(final Movie movie1) {
                                          movie.setRottenTomatoesRate(movie1.getRottenTomatoesRate());
                                          movie.setMovieRottenTomatoesId(movie1.getMovieRottenTomatoesId());
                                          MainActivity.getActivity().runOnUiThread(new Runnable() {
                                              @Override
                                              public void run() {

                                                  if (movie1.getRottentTomatoesRatingType().equals("Certified Fresh"))
                                                      ImageController.putDrawableToImageView(R.drawable.certified2,tomatoesPoster);
                                                  else if (movie1.getRottentTomatoesRatingType().equals("Fresh"))
                                                      Glide.with(getContext()).load(R.drawable.fresh2).into(tomatoesPoster);
                                                  else if (movie1.getRottentTomatoesRatingType().equals("Rotten"))
                                                      Glide.with(getContext()).load(R.drawable.rotten).into(tomatoesPoster);


                                                  if (movie1.getRottenTomatoesRate() > 0)
                                                      tomatoesRating.setText(String.valueOf(movie1.getRottenTomatoesRate()));

                                              }
                                          });

                                      }
                                  });
                              }
                          }
        );
    }

}