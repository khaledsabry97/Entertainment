package com.example.khaledsabry.entertainment.Fragments;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Adapters.YoutubeOptionAdapter;
import com.example.khaledsabry.entertainment.Adapters.YoutubeVideoAdapter;
import com.example.khaledsabry.entertainment.Controllers.YoutubeController;
import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.Items.Episode;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Season;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.Items.Youtube;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;


public class YoutubeFragment extends Fragment {
    public enum Type {
        movie,
        tv,
        artist,
        season,
        episode
    }

    //open it and close it
    private DrawerLayout drawerLayout;
    //youtube videos
    private RecyclerView videos;
    //options you select from
    private RecyclerView options;
    //the type of the videos i search for
    private YoutubeFragment.Type type;
    Movie movie;
    Tv tv;
    Artist artist;

    //movie,tv or artist object
    Object object;

    YoutubeOptionAdapter optionAdapter;
    WebFragment webFragment;

    public static YoutubeFragment newInstance(Object item, YoutubeFragment.Type type) {
        YoutubeFragment fragment = new YoutubeFragment();
        fragment.type = type;
        fragment.object = item;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_youtube_custom, container, false);
        videos = view.findViewById(R.id.videos_recycler_id);
        options = view.findViewById(R.id.recycler_options_id);
        drawerLayout = view.findViewById(R.id.drawer_layout);


        setupYoutubeVideoFragment();
        setupVideos();
        setupOptions();
        setupObjectType();

        return view;
    }

    private void setupObjectType() {
        switch (type) {
            case movie:
                loadMovie();
                break;
            case tv:
                loadTv();
                break;

            case artist:
                break;
            case season:
                loadSeason();
                break;
            case episode:
                loadEpisode();
                break;
        }
    }


    private void loadEpisode() {
        Episode  episode = (Episode) object;
        ArrayList<YoutubeController.Type> types;
        ArrayList<String> titles;
        types = new ArrayList<>();
        titles = new ArrayList<>();

        types.add(YoutubeController.Type.trailer);
        types.add(YoutubeController.Type.episode_review);
        types.add(YoutubeController.Type.featurette);
        types.add(YoutubeController.Type.behind_the_scenes);


        titles.add("Trailer");
        titles.add("Reviews");
        titles.add("Featurette");
        titles.add("Behind the Scenes");

        String seasons = " S";
        if (episode.getSeasonNumber() < 10)
            seasons += "0";
        seasons += episode.getSeasonNumber();

        String episodes = "E";
        if (episode.getEpisodeNumber() < 10)
            episodes += "0";
        episodes += episode.getEpisodeNumber();
        optionAdapter = new YoutubeOptionAdapter(this,webFragment, types, titles, episode.getTvTitle()+ seasons + episodes, "");
        options.setAdapter(optionAdapter);
    }

    private void loadSeason() {
      Season  season = (Season) object;
        ArrayList<YoutubeController.Type> types;
        ArrayList<String> titles;
        types = new ArrayList<>();
        titles = new ArrayList<>();

        types.add(YoutubeController.Type.trailer);
        types.add(YoutubeController.Type.tv_review);
        types.add(YoutubeController.Type.featurette);
        types.add(YoutubeController.Type.behind_the_scenes);


        titles.add("Trailer");
        titles.add("Reviews");
        titles.add("Featurette");
        titles.add("Behind the Scenes");

        String seasons = " S";
        if (season.getSeasonNumber() < 10)
            seasons += "0";
        seasons += season.getSeasonNumber();

        optionAdapter = new YoutubeOptionAdapter(this,webFragment, types, titles, season.getTvTitle()+ seasons, "");
        options.setAdapter(optionAdapter);
    }


    /**
     * load the webfragment to upload later the videos
     */
    private void setupYoutubeVideoFragment() {

        webFragment = WebFragment.newInstance(WebFragment.Type.youtube);
        MainActivity.loadFragmentNoReturn(R.id.youtube_player_view, webFragment);

    }



    /**
     * setup the recycler view that takes the videos
     */
    private void setupVideos() {
        videos.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        videos.setLayoutManager(linearLayoutManager);
    }

    /**
     * setup the options you choose to get videos
     * and get videos
     */
    private void setupOptions() {
        options.setHasFixedSize(true);
        options.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    /**
     * get videos for movie
     */
    private void loadMovie() {
        movie = (Movie) object;
        ArrayList<YoutubeController.Type> types;
        ArrayList<String> titles;
        types = new ArrayList<>();
        titles = new ArrayList<>();

        //the searchs types you want
        types.add(YoutubeController.Type.trailer);
        types.add(YoutubeController.Type.movie_review);
        types.add(YoutubeController.Type.featurette);
        types.add(YoutubeController.Type.behind_the_scenes);
        types.add(YoutubeController.Type.soundtrack);


        titles.add("Trailer");
        titles.add("Reviews");
        titles.add("Featurette");
        titles.add("Behind the Scenes");
        titles.add("SoundTrack");


        optionAdapter = new YoutubeOptionAdapter(this,webFragment, types, titles, movie.getTitle(), movie.getYear());
        options.setAdapter(optionAdapter);


    }

    private void loadTv() {
        tv = (Tv) object;
        ArrayList<YoutubeController.Type> types;
        ArrayList<String> titles;
        types = new ArrayList<>();
        titles = new ArrayList<>();

        types.add(YoutubeController.Type.trailer);
        types.add(YoutubeController.Type.movie_review);
        types.add(YoutubeController.Type.featurette);
        types.add(YoutubeController.Type.behind_the_scenes);
        types.add(YoutubeController.Type.soundtrack);


        titles.add("Trailer");
        titles.add("Reviews");
        titles.add("Featurette");
        titles.add("Behind the Scenes");
        titles.add("SoundTrack");


        optionAdapter = new YoutubeOptionAdapter(this,webFragment, types, titles, tv.getTitle(), tv.getYear());
        options.setAdapter(optionAdapter);

    }

    /**
     * after you get from option adapter videos from youtube controller
     * you load the youtubes into the videos recycler view
     *
     * @param youtubes videos for youtube
     */
    public void loadVideos(ArrayList<Youtube> youtubes) {

        YoutubeVideoAdapter adapter = new YoutubeVideoAdapter(drawerLayout, youtubes, webFragment);
        videos.setAdapter(adapter);
        drawerLayout.openDrawer(GravityCompat.END, true);

    }


    @Override
    public void onStop() {
        super.onStop();
        webFragment.onDestroy();
    }
}
