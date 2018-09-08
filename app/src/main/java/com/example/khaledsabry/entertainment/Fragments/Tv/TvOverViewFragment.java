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
import com.example.khaledsabry.entertainment.Fragments.CastFragment;
import com.example.khaledsabry.entertainment.Fragments.CrewFragment;
import com.example.khaledsabry.entertainment.Fragments.ProductionCompanyFragment;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;

import me.relex.circleindicator.CircleIndicator;


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
        title = view.findViewById(R.id.titleId);
        overviewText = view.findViewById(R.id.overviewID);
        firstAirDate = view.findViewById(R.id.firstairdateid);
        runTimeText = view.findViewById(R.id.runtimeid);
        genres = view.findViewById(R.id.genres_id);
        lastAirDate = view.findViewById(R.id.lastairdateid);
        rate = view.findViewById(R.id.rateid);
        actorCrewLayout = view.findViewById(R.id.actors_crews_id);
        numberOfEpisodes = view.findViewById(R.id.numberofepisodesid);
        numberOfSeasons = view.findViewById(R.id.numberofseasonsid);
        actorButton = view.findViewById(R.id.button_actors_id);
        crewButton = view.findViewById(R.id.button_crew_id);
        setObjects();


        return view;
    }


    private void setObjects() {
        overviewText.setText(tv.getOverView());
        firstAirDate.setText(tv.getFirstAirDate());
        runTimeText.setText(tv.getRunTime() + " min");
        genres.setText(tv.getGenreList());
        numberOfSeasons.setText(String.valueOf(tv.getNumberOfSeasons()));
        numberOfEpisodes.setText(String.valueOf(tv.getNumberOfEpisodes()));
        lastAirDate.setText(tv.getLastAirDate());
        rate.setText(tv.getRateTmdb() + "/10");
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

}
