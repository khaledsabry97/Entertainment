package com.example.khaledsabry.entertainment.Fragments.MovieView;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Adapter.MainPosterViewPager;
import com.example.khaledsabry.entertainment.Connection.WebApi;
import com.example.khaledsabry.entertainment.Controllers.CategoryController;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.CastFragment;
import com.example.khaledsabry.entertainment.Fragments.CategoryAddFragment;
import com.example.khaledsabry.entertainment.Fragments.CrewFragment;
import com.example.khaledsabry.entertainment.Fragments.ProductionCompanyFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnWebSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;


public class MovieOverviewFragment extends Fragment {

    Movie movie;
    //Layouts
    TextView title, overviewText, releaseDate, runTimeText, genres, budget, revenue, rate, mpaa, status, imdbRating, tomatoesRating;

    ImageView tomatoesPoster;


    Button actorButton, crewButton;




    public static MovieOverviewFragment newInstance(Movie movie) {
        MovieOverviewFragment fragment = new MovieOverviewFragment();
fragment.movie = movie;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_movie_overview, container, false);
        title = view.findViewById(R.id.titleId);
        overviewText = view.findViewById(R.id.overviewID);
        releaseDate = view.findViewById(R.id.releasetimeid);
        runTimeText = view.findViewById(R.id.timeid);
        genres = view.findViewById(R.id.genres_id);
        budget = view.findViewById(R.id.budgetid);
        rate = view.findViewById(R.id.rateid);
        status = view.findViewById(R.id.statusid);
        mpaa = view.findViewById(R.id.mpaaid);
        revenue = view.findViewById(R.id.revenueid);
        actorButton = view.findViewById(R.id.button_actors_id);
        crewButton = view.findViewById(R.id.button_crew_id);
        imdbRating = view.findViewById(R.id.imdbrate);
        tomatoesRating = view.findViewById(R.id.tomatoesrate);
        tomatoesPoster = view.findViewById(R.id.tomatoes_poster_id);

        try {
            setObjects();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }


    private void setObjects() {



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



        loadActorFragment();
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
        MainActivity.loadFragmentNoReturn(R.id.half_movie_id, ReviewFragment.newInstance(movie));
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

    public void getRottenTomatoesInfo() {
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
            ImageController.putDrawableToImageView(R.drawable.fresh, tomatoesPoster);
        else if (movie.getRottentTomatoesRatingType().equals("Rotten"))
            ImageController.putDrawableToImageView(R.drawable.rotten, tomatoesPoster);

        //rate
        if (movie.getRottenTomatoesRate() > 0)
            tomatoesRating.setText(String.valueOf(movie.getRottenTomatoesRate()));
    }



}
