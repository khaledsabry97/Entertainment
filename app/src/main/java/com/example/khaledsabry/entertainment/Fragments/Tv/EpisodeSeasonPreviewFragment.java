package com.example.khaledsabry.entertainment.Fragments.Tv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.TorrentRecyclerFragment;
import com.example.khaledsabry.entertainment.Items.Episode;
import com.example.khaledsabry.entertainment.Items.Season;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;


public class EpisodeSeasonPreviewFragment extends Fragment {

    static Tv tv;
    static Season season;

    static Episode episode;

    ImageView poster;
    TextView title;
    TextView rate;
    TextView airDate;
    TextView overView;
    TextView download;
    TextView name;
    TextView ratetext;
    TextView titleText;
    View overviewLayout;
    View titleLayout;
    View rateLayout;

    public static EpisodeSeasonPreviewFragment newInstance(Tv tv, Season season, Episode episode) {
        EpisodeSeasonPreviewFragment fragment = new EpisodeSeasonPreviewFragment();
        EpisodeSeasonPreviewFragment.tv = tv;
        EpisodeSeasonPreviewFragment.season = season;
        EpisodeSeasonPreviewFragment.episode = episode;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_episode_preview, container, false);
        title = view.findViewById(R.id.title);
        rate = view.findViewById(R.id.rateid);

        airDate = view.findViewById(R.id.airdateid);

        overView = view.findViewById(R.id.overviewid);
        poster = view.findViewById(R.id.posterrelativelayout);
        download = view.findViewById(R.id.downloadid);
        name = view.findViewById(R.id.episodename);
        overviewLayout = view.findViewById(R.id.overviewlayout);
        titleLayout = view.findViewById(R.id.titleLayout);
        rateLayout = view.findViewById(R.id.rateLayout);
        ratetext = view.findViewById(R.id.ratetextid);
        titleText = view.findViewById(R.id.titletextid);

       // download.setTextAppearance(getContext(),R.style.outline);

        setObjects();

        return view;
    }

    private void setObjects() {
        if (season != null) {
            title.setText(season.getName());

            airDate.setText(season.getAirDate());
            if (season.getOverView().equals(""))
                overviewLayout.setVisibility(View.GONE);
            else
                overView.setText(season.getOverView());

            ImageController.putImageMidQuality(season.getPoster(), poster);
            rateLayout.setVisibility(View.GONE);
            titleLayout.setVisibility(View.GONE);

        } else if (episode != null) {

            title.setText("Season " + getZeroNumber(episode.getSeasonNumber()) + " - " + "Episode " + getZeroNumber(episode.getEpisodeNumber()));
            name.setText(episode.getName());

            airDate.setText(episode.getAirDate());
            if (episode.getRateTmdb() == 0.0)
                rateLayout.setVisibility(View.GONE);
            else
                rate.setText(episode.getRateTmdb() + "/10");

            overView.setText(episode.getOverView());
            ImageController.putImageMidQuality(episode.getPoster(), poster);
        }

        setDownload();
    }

    private void setDownload() {
        String searchString = "";
        if (season != null) {
            String seasons = "S";
            if (season.getSeasonNumber() < 10)
                seasons += "0";
            seasons += season.getSeasonNumber();

            searchString = tv.getTitle() + " " + seasons;
            searchString = setSpecial(searchString, 1);

        } else if (episode != null) {
            String seasons = "S";
            if (episode.getSeasonNumber() < 10)
                seasons += "0";
            seasons += episode.getSeasonNumber();

            String episodes = "E";
            if (episode.getEpisodeNumber() < 10)
                episodes += "0";
            episodes += episode.getEpisodeNumber();


            searchString = tv.getTitle() + " " + seasons + episodes;
            searchString = setSpecial(searchString, 0);

        }


        final String finalSearchString = searchString;
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.moviedetailid, TorrentRecyclerFragment.newInstance(finalSearchString)).addToBackStack(null).commit();
            }
        });

    }

    //state 0 --> episode   state 1 --> season
    private String setSpecial(String searchString, int state) {

        if (tv.getTitle().toLowerCase().contains("wwe") && state == 1) {
            searchString = tv.getTitle() + " " + season.getAirDate().substring(0, 4);
        } else if (tv.getTitle().toLowerCase().contains("wwe") && state == 0) {
            searchString = tv.getTitle() + " " + episode.getAirDate();
        }


        return searchString;
    }


    private String getZeroNumber(int number) {
        String zero = "";
        if (number < 10)
            zero += "0";
        zero += number;
        return zero;
    }

}
