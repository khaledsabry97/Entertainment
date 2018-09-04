package com.example.khaledsabry.entertainment.Fragments.MovieView;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import com.example.khaledsabry.entertainment.Adapter.MainPosterViewPager;
import com.example.khaledsabry.entertainment.Connection.WebApi;
import com.example.khaledsabry.entertainment.Controllers.CategoryController;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.CastFragment;
import com.example.khaledsabry.entertainment.Fragments.CategoryAddFragment;
import com.example.khaledsabry.entertainment.Fragments.CrewFragment;
import com.example.khaledsabry.entertainment.Fragments.ProductionCompanyFragment;
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
    TextView title, overviewText, releaseDate, runTimeText, genres, budget, revenue, rate, mpaa, status, imdbRating, tomatoesRating;

    ImageView  tomatoesPoster, addNewCategory, addToCategory,addFavourite,addWatchLater;

    CircleIndicator indicator;
    ViewPager viewPager;
    MainPosterViewPager viewPagerAdapter;

    Button actorButton, crewButton, addCategoryButton;


    EditText categoryNew;

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
        View view = inflater.inflate(R.layout.fragment_movie_main, container, false);
        title = view.findViewById(R.id.titleId);
        overviewText = view.findViewById(R.id.overviewID);
        releaseDate = view.findViewById(R.id.releasetimeid);
        runTimeText = view.findViewById(R.id.timeid);
        genres = view.findViewById(R.id.genres_id);
        budget = view.findViewById(R.id.budgetid);
        rate = view.findViewById(R.id.rateid);
        status = view.findViewById(R.id.statusid);
        mpaa = view.findViewById(R.id.mpaaid);
        viewPager = view.findViewById(R.id.view_pager_id);
        indicator = view.findViewById(R.id.indicator);
        revenue = view.findViewById(R.id.revenueid);
        actorButton = view.findViewById(R.id.button_actors_id);
        crewButton = view.findViewById(R.id.button_crew_id);
        scrollView = view.findViewById(R.id.sideid);
        scrollView.setVisibility(View.INVISIBLE);
        reviewLayout = view.findViewById(R.id.ReviewLayoutid);
        addNewCategory = view.findViewById(R.id.addnewcategory);
        addToCategory = view.findViewById(R.id.add_to_category_id);
        writeNewCategoryLayout = view.findViewById(R.id.addnew);
        categoryNew = view.findViewById(R.id.categorytext);
        addCategoryButton = view.findViewById(R.id.addcategory);
        imdbRating = view.findViewById(R.id.imdbrate);
        tomatoesRating = view.findViewById(R.id.tomatoesrate);
        tomatoesPoster = view.findViewById(R.id.tomatoesposter);
        addWatchLater = view.findViewById(R.id.add_to_watch_later_id);
        addFavourite = view.findViewById(R.id.add_to_favourite_id);
        categoryController = new CategoryController();

        try {
            setObjects();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

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


        viewPagerAdapter.movePoster(viewPager, viewPagerAdapter, 7000, 4000);


        loadActorFragment();
        loadReviewFragment();
        loadProductionFragment();



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
        categoryController.getCategories(movie.getMovieId());


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


    public void openCategoryAdd(ArrayList<String> names, ArrayList<Integer> ids, ArrayList<Boolean> booleans) {
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.mainContainer,CategoryAddFragment.newInstance(names, ids, booleans, 1, String.valueOf(movie.getMovieId()))).commit();
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

        if (movie.getImdbRate() != 0.0)
            setImdbPosterRating();
        else
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    WebApi.getInstance().imdbMovieDetails(movie.getMovieImdbId(), new OnWebSuccess.OnMovie() {
                        @Override
                        public void onSuccess(Movie movie1) {
                            movie.setImdbRate(movie1.getImdbRate());
                            movie.setMpaa(movie1.getMpaa());
                            MainActivity.getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setImdbPosterRating();
                                }
                            });

                        }
                    });
                }
            });

    }

    public void getRottenTomatoesInfo() throws Exception {
        if (movie.getRottenTomatoesRate() != 0.0) {
            setTomatoesPosterRating();
        } else {
            AsyncTask.execute(new Runnable() {
                                  @Override
                                  public void run() {
                                      WebApi.getInstance().rottenTomatoesMoviePreview(movie.getTitle(), movie.getYear(), new OnWebSuccess.OnMovie() {
                                          @Override
                                          public void onSuccess(final Movie movie1) {

                                              movie.setRottentTomatoesRatingType(movie1.getRottentTomatoesRatingType());
                                              movie.setRottenTomatoesRate(movie1.getRottenTomatoesRate());
                                              movie.setMovieRottenTomatoesId(movie1.getMovieRottenTomatoesId());
                                              MainActivity.getActivity().runOnUiThread(new Runnable() {
                                                  @Override
                                                  public void run() {
                                                      setTomatoesPosterRating();

                                                  }
                                              });

                                          }
                                      });
                                  }
                              }
            );
        }
    }


    /**
     * set the poster of the imdb
     * set the rate of the imdb
     */
    void setImdbPosterRating() {
        if (imdbRating == null || mpaa == null)
            return;
        if (!String.valueOf(movie.getImdbRate()).equals("0.0")) {
            imdbRating.setText(String.valueOf(movie.getImdbRate()));
        }
        if (!String.valueOf(movie.getMpaa()).equals("")) {
            mpaa.setText(movie.getMpaa());
        }
    }

    /**
     * set the poster of the rotten tomatoes
     * set the rate of the rotten tomatoes
     */
    void setTomatoesPosterRating() {
        //check
        if (tomatoesPoster == null || tomatoesRating == null)
            return;
        //poster
        if (movie.getRottentTomatoesRatingType().equals("Certified Fresh"))
            ImageController.putDrawableToImageView(R.drawable.certified2, tomatoesPoster);
        else if (movie.getRottentTomatoesRatingType().equals("Fresh"))
            ImageController.putDrawableToImageView(R.drawable.fresh2, tomatoesPoster);
        else if (movie.getRottentTomatoesRatingType().equals("Rotten"))
            ImageController.putDrawableToImageView(R.drawable.rotten, tomatoesPoster);

        //rate
        if (movie.getRottenTomatoesRate() > 0)
            tomatoesRating.setText(String.valueOf(movie.getRottenTomatoesRate()));
    }


    void setAddToCategory()
    {
        if (categoryIds != null)
            openCategoryAdd(categoryNames, categoryIds, categoryCheacks);
    }

    void setAddFavourite()
    {
        categoryController.addFavourite(String.valueOf(movie.getMovieId()), movie.getMovieImdbId(), categoryController.constants.movie, new OnSuccess.bool() {
            @Override
            public void onSuccess(boolean state) {
                if (state)
                    categoryController.toast(movie.getTitle() + " has been added to your Favourite categoryItem");

            }
        });
    }

    void setAddWatchLater()
    {

    }

}