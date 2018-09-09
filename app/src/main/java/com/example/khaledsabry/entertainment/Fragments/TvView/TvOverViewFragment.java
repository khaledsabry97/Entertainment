package com.example.khaledsabry.entertainment.Fragments.TvView;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Connection.WebApi;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.CastFragment;
import com.example.khaledsabry.entertainment.Fragments.CrewFragment;
import com.example.khaledsabry.entertainment.Fragments.ProductionCompanyFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnWebSuccess;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;


public class TvOverViewFragment extends Fragment {

    Tv tv;

    TextView title;

    TextView overviewText;
    TextView firstAirDate;
    TextView lastAirDate;
    TextView runTimeText;
    TextView genres;

    TextView numberOfSeasons;
    TextView numberOfEpisodes;
    ImageView tomatoesPoster;
    TextView mpaa, imdbRating, tomatoesRating;
    TextView rate;
    TextView status;
    Button actorButton;
    Button crewButton;
    View actorCrewLayout;

    public static TvOverViewFragment newInstance(Tv tv) {
        TvOverViewFragment fragment = new TvOverViewFragment();
        fragment.tv = tv;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_over_view, container, false);
        title = view.findViewById(R.id.title_id);
        overviewText = view.findViewById(R.id.overview_id);
        firstAirDate = view.findViewById(R.id.firstairdateid);
        runTimeText = view.findViewById(R.id.runtimeid);
        genres = view.findViewById(R.id.genres_id);
        lastAirDate = view.findViewById(R.id.lastairdateid);
        mpaa = view.findViewById(R.id.mpaa_id);
        rate = view.findViewById(R.id.rate_id);
        actorCrewLayout = view.findViewById(R.id.actors_crews_id);
        numberOfEpisodes = view.findViewById(R.id.numberofepisodesid);
        numberOfSeasons = view.findViewById(R.id.numberofseasonsid);
        actorButton = view.findViewById(R.id.button_actors_id);
        crewButton = view.findViewById(R.id.button_crew_id);
        imdbRating = view.findViewById(R.id.imdbrate);
        tomatoesRating = view.findViewById(R.id.tomatoesrate);
        tomatoesPoster = view.findViewById(R.id.tomatoes_poster_id);
        setObjects();


        return view;
    }


    private void setObjects() {
        getImdbInfo();
        getRottenTomatoesInfo();
        overviewText.setText(tv.getOverView());
        firstAirDate.setText(tv.getFirstAirDate());
        runTimeText.setText(tv.getRunTime() + " min");
        genres.setText(tv.getGenreList());
        numberOfSeasons.setText(String.valueOf(tv.getNumberOfSeasons()));
        numberOfEpisodes.setText(String.valueOf(tv.getNumberOfEpisodes()));
        lastAirDate.setText(tv.getLastAirDate());
        rate.setText(tv.getRateTmdb() + "");
        title.setText(tv.getTitle());
        if (tv.getActors().size() == 0)
            actorCrewLayout.setVisibility(View.GONE);
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

    private void loadActorFragment() {
        CastFragment castFragment = CastFragment.newInstance(tv.getActors());
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actors_crews_id, castFragment).commit();
    }

    private void loadCrewFragment() {
        CrewFragment crewFragment = CrewFragment.newInstance(tv.getCrew());
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actors_crews_id, crewFragment).commit();
    }

    private void loadProductionFragment() {
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.productionframelayoutid, ProductionCompanyFragment.newInstance(tv.getProductionCompanies())).commit();

    }


    public void getImdbInfo() {
        if (tv.getImdbId() == null)
            return;

        if (tv.getImdbRate() != 0.0)
            setImdbPosterRating();
        else
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    WebApi.getInstance().imdbTvDetails(tv.getImdbId(), new OnWebSuccess.OnTv() {
                        @Override
                        public void onSuccess(Tv tv1) {
                            tv.setImdbRate(tv1.getImdbRate());
                            tv.setMpaa(tv1.getMpaa());
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
        if (tv.getRottenTomatoesRate() != 0.0) {
            setTomatoesPosterRating();
        } else {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    WebApi.getInstance().rottenTomatoesTvPreview(tv.getTitle(), tv.getYear(), new OnWebSuccess.OnTv() {
                        @Override
                        public void onSuccess(Tv tv1) {
                            tv.setRottenTomatoesRatingType(tv1.getRottenTomatoesRatingType());
                            tv.setRottenTomatoesRate(tv1.getRottenTomatoesRate());
                            MainActivity.getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    setTomatoesPosterRating();
                                }
                            });
                        }
                    });
                }
            });
        }
    }


    /**
     * set the poster of the imdb
     * set the rate of the imdb
     */
    void setImdbPosterRating() {
        if (imdbRating == null || mpaa == null)
            return;
        if (!String.valueOf(tv.getImdbRate()).equals("0.0")) {
            imdbRating.setText(String.valueOf(tv.getImdbRate()));
        }
        if (!String.valueOf(tv.getMpaa()).equals("")) {
            mpaa.setText(tv.getMpaa());
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
        if (tv.getRottenTomatoesRatingType().equals("Certified Fresh"))
            ImageController.putDrawableToImageView(R.drawable.certified2, tomatoesPoster);
        else if (tv.getRottenTomatoesRatingType().equals("Fresh"))
            ImageController.putDrawableToImageView(R.drawable.fresh, tomatoesPoster);
        else if (tv.getRottenTomatoesRatingType().equals("Rotten"))
            ImageController.putDrawableToImageView(R.drawable.rotten, tomatoesPoster);

        //rate
        if (tv.getRottenTomatoesRate() > 0)
            tomatoesRating.setText(String.valueOf(tv.getRottenTomatoesRate()));
    }


}
