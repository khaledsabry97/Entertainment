package com.example.khaledsabry.entertainment.Fragments.Tv;


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

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Adapter.MainPosterViewPager;
import com.example.khaledsabry.entertainment.Controllers.Functions;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Fragments.MovieView.CastFragment;
import com.example.khaledsabry.entertainment.Fragments.MovieView.CrewFragment;
import com.example.khaledsabry.entertainment.Fragments.MovieView.ProductionCompanyFragment;
import com.example.khaledsabry.entertainment.Fragments.MovieView.ReviewFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnTvSuccess;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;

import me.relex.circleindicator.CircleIndicator;

public class TvMainFragment extends Fragment {

    static int tvId;
    TextView title;
    static Tv tv = null;

    static int currentTvId = -1;
    TextView overviewText;
    TextView firstAirDate;
    TextView lastAirDate;
    TextView runTimeText;
    TextView genres;

    TextView numberOfSeasons;
    TextView numberOfEpisodes;

    TextView rate;
    TextView status;
    CircleIndicator indicator;
    ViewPager viewPager;
    MainPosterViewPager viewPagerAdapter;
    Button actorButton;
    Button crewButton;
    NestedScrollView scrollView;
    View actorCrewLayout;
    static FrameLayout reviewLayout;

    public static TvMainFragment newInstance(int tvId) {
        TvMainFragment fragment = new TvMainFragment();
        TvMainFragment.tvId = tvId;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             // Inflate the layout for this fragment
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tv_main, container, false);
        title = v.findViewById(R.id.titleId);
        overviewText = v.findViewById(R.id.overviewID);
        firstAirDate = v.findViewById(R.id.firstairdateid);
        runTimeText = v.findViewById(R.id.runtimeid);
        genres = v.findViewById(R.id.genresid);
        lastAirDate = v.findViewById(R.id.lastairdateid);
        rate = v.findViewById(R.id.rateid);
        actorCrewLayout = v.findViewById(R.id.actor_crew_layout);
        numberOfEpisodes = v.findViewById(R.id.numberofepisodesid);
        viewPager = v.findViewById(R.id.viewPagerid);
        indicator = v.findViewById(R.id.indicator);
        numberOfSeasons = v.findViewById(R.id.numberofseasonsid);
        actorButton = v.findViewById(R.id.button_actors_id);
        crewButton = v.findViewById(R.id.button_crew_id);
        scrollView = v.findViewById(R.id.sideid);
        scrollView.setVisibility(View.INVISIBLE);
        reviewLayout = v.findViewById(R.id.ReviewLayoutid);
        getMovieDetails();
        return v;
    }


    private void setObjects(Tv tv) {
        overviewText.setText(tv.getOverView());
        firstAirDate.setText(tv.getFirstAirDate());
        runTimeText.setText(tv.getRunTime() + " min");
        genres.setText(tv.getGenreList());
        numberOfSeasons.setText(String.valueOf(tv.getNumberOfSeasons()));
        numberOfEpisodes.setText(String.valueOf(tv.getNumberOfEpisodes()));
        lastAirDate.setText(tv.getLastAirDate());
        rate.setText(tv.getRateTmdb() + "/10");
        title.setText(tv.getTitle());
        viewPagerAdapter = new MainPosterViewPager(tv.getPosters());
        viewPager.setAdapter(viewPagerAdapter);
        indicator.setViewPager(viewPager);
        if (tv.getActors().size() == 0)
            actorCrewLayout.setVisibility(View.GONE);
        loadActorFragment();
        loadReviewFragment();
        loadProductionFragment();

        Functions functions = new Functions();
        functions.movePoster(viewPager, viewPagerAdapter, 3000, 2000);
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
        MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.productionframelayoutid, ProductionCompanyFragment.newInstance(tv.getProductionCompanies())).commit();

    }


    public void getMovieDetails() {
        TmdbController tmdbController = new TmdbController();
        if (tvId != currentTvId)
            tmdbController.getTv(tvId, new OnTvSuccess() {
                @Override
                public void onSuccess(Tv tv) {
                    TvMainFragment.tv = tv;

                    setObjects(tv);
                }
            });

        else
            setObjects(tv);
    }

    private void loadActorFragment() {
        CastFragment castFragment = CastFragment.newInstance(tv.getActors());
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actors_crews_id, castFragment).commit();
    }

    private void loadCrewFragment() {
        CrewFragment crewFragment = CrewFragment.newInstance(tv.getCrew());
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.actors_crews_id, crewFragment).commit();
    }

    private void loadReviewFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ReviewLayoutid, ReviewFragment.newInstance(tv.getReviews())).commit();
    }

    public static void hideReviewView() {
        reviewLayout.setVisibility(View.GONE);
    }

}


