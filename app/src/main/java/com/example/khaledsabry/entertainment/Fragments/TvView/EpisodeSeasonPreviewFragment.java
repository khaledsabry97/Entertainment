package com.example.khaledsabry.entertainment.Fragments.TvView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Fragments.MovieView.ReviewFragment;
import com.example.khaledsabry.entertainment.Fragments.TorrentFragment;
import com.example.khaledsabry.entertainment.Fragments.YoutubeFragment;
import com.example.khaledsabry.entertainment.Items.Episode;
import com.example.khaledsabry.entertainment.Items.Season;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.R;
import com.felix.bottomnavygation.BottomNav;
import com.felix.bottomnavygation.ItemNav;


public class EpisodeSeasonPreviewFragment extends Fragment {

    Tv tv;
    Object object;
    Season season;
    Episode episode;

    ImageView poster;
    TextView title;
    TextView rate;
    TextView airDate;
    TextView overView;
    TextView download;
    TextView name;
    TextView ratetext;
    View overviewLayout;
    View nameLayout;
    View rateLayout;
    BottomNav bottomNav;
     String finalSearchString="";
    public static EpisodeSeasonPreviewFragment newInstance(Tv tv, Object object) {
        EpisodeSeasonPreviewFragment fragment = new EpisodeSeasonPreviewFragment();
        fragment.tv = tv;
        fragment.object = object;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_episode_preview, container, false);
        title = view.findViewById(R.id.title_id);
        rate = view.findViewById(R.id.rate_id);
        name = view.findViewById(R.id.episode_name_id);

        airDate = view.findViewById(R.id.air_date_id);

        overView = view.findViewById(R.id.overview_id);
        poster = view.findViewById(R.id.poster_id);
        download = view.findViewById(R.id.download_id);
        overviewLayout = view.findViewById(R.id.overview_layout_id);
        nameLayout = view.findViewById(R.id.name_layout_id);
        rateLayout = view.findViewById(R.id.rate_layout_id);
        ratetext = view.findViewById(R.id.rate_id);
bottomNav = view.findViewById(R.id.bottom_navigation_id);

        setObjects();

setUpBottomNav();
        return view;
    }

    private void setUpBottomNav()
    {
        bottomNav.addItemNav(new ItemNav(getContext(), R.drawable.youtube,"Youtube").addColorAtive(R.color.white));
        bottomNav.addItemNav(new ItemNav(getContext(), R.drawable.download,"Download").addColorAtive(R.color.white));
        if(season != null)
        bottomNav.addItemNav(new ItemNav(getContext(), R.drawable.ic_home_black_24dp, "Reviews").addColorAtive(R.color.white));


        bottomNav.setTabSelectedListener(new BottomNav.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int i) {
                setBottomSelectedIndex(i);
            }

            @Override
            public void onTabLongSelected(int i) {

            }
        });
        bottomNav.build();
    }
    private void setObjects() {
        if (object instanceof Season) {
            season = (Season) object;
            title.setText(season.getName());

            airDate.setText(season.getAirDate());
            if (season.getOverView().equals(""))
                overviewLayout.setVisibility(View.GONE);
            else
                overView.setText(season.getOverView());

            ImageController.putImageMidQuality(season.getPoster(), poster);
            rateLayout.setVisibility(View.GONE);
            nameLayout.setVisibility(View.GONE);

        } else if (object instanceof Episode) {
            episode = (Episode) object;
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


       finalSearchString  = searchString;
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.loadFragmentNoReturn(R.id.season_episode, TorrentFragment.newInstance(finalSearchString));
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

    public void setBottomSelectedIndex(int bottomSelectedIndex) {
        switch (bottomSelectedIndex)
        {
            case 0:
                if(season == null)
                MainActivity.loadFragmentNoReturn(R.id.season_episode, YoutubeFragment.newInstance(object, YoutubeFragment.Type.episode));
                else if(season !=null)
                    MainActivity.loadFragmentNoReturn(R.id.season_episode, YoutubeFragment.newInstance(object, YoutubeFragment.Type.season));

                break;
            case 1:
                MainActivity.loadFragmentNoReturn(R.id.season_episode,TorrentFragment.newInstance(finalSearchString));
                break;
            case 2:
                MainActivity.loadFragmentNoReturn(R.id.season_episode_half, ReviewFragment.newInstance(object));
                break;

        }

    }



}
