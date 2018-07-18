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


public class TvPreviewFragment extends Fragment {


    static Tv tv;
    static Season season;

    static Episode episode;

    ImageView poster;
    TextView title;
    TextView rate;
    TextView airDate;
    TextView overView;
    TextView download;
    static boolean canDownlaod;

    public static TvPreviewFragment newInstance(Tv tv, Season season, Episode episode, boolean canDownload) {
        TvPreviewFragment fragment = new TvPreviewFragment();
        TvPreviewFragment.tv = tv;
        TvPreviewFragment.season = season;
        TvPreviewFragment.episode = episode;
        TvPreviewFragment.canDownlaod = canDownload;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_preview, container, false);
        title = view.findViewById(R.id.titleid);
        rate = view.findViewById(R.id.rateid);

        airDate = view.findViewById(R.id.airdateid);

        overView = view.findViewById(R.id.overviewid);
        poster = view.findViewById(R.id.posterid);
        download = view.findViewById(R.id.downloadid);


        setObjects();

        if (tv != null)
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, TvNavigationFragment.newInstance(tv.getId(), true)).addToBackStack(null).commit();
                }
            });


        return view;
    }

    private void setObjects() {
        if (tv != null) {
            title.setText(tv.getTitle());
            rate.setText(tv.getRateTmdb() + "/10");
            airDate.setText("First Air Date : " + tv.getFirstAirDate());
            overView.setText(tv.getOverView());
            ImageController.putImageMidQuality(tv.getBackDrop(), poster);
        } else if (season != null) {
            title.setText(season.getName());

            airDate.setText(season.getAirDate());
            overView.setText(season.getOverView());
            ImageController.putImageMidQuality(season.getPoster(), poster);
            rate.setVisibility(View.GONE);


        } else if (episode != null) {

            title.setText(episode.getName());

            airDate.setText(episode.getAirDate());
            rate.setText(episode.getRateTmdb() + "/10");

            overView.setText(episode.getOverView());
            ImageController.putImageMidQuality(episode.getPoster(), poster);
        }
        if (!canDownlaod)
            download.setVisibility(View.INVISIBLE);
        else
            setDownload();

    }


    private void setDownload() {
        String searchString = "";
        if (season != null) {
            String seasons = "S";
            if (season.getSeasonNumber() < 10)
                seasons += "0";
            seasons += season.getSeasonNumber();

            searchString = TvNavigationFragment.tv.getTitle() + " " + seasons;
        } else if (episode != null) {
            String seasons = "S";
            if (episode.getSeasonNumber() < 10)
                seasons += "0";
            seasons += episode.getSeasonNumber();

            String episodes = "E";
            if (episode.getEpisodeNumber() < 10)
                episodes += "0";
            episodes += episode.getEpisodeNumber();


            searchString = TvNavigationFragment.tv.getTitle() + " " + seasons + episodes;

        }


        final String finalSearchString = searchString;
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.moviedetailid, TorrentRecyclerFragment.newInstance(finalSearchString)).addToBackStack(null).commit();
            }
        });

    }

}
