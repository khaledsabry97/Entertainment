package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapter.YoutubeOptionAdapter;
import com.example.khaledsabry.entertainment.Adapter.YoutubeVideoAdapter;
import com.example.khaledsabry.entertainment.Controllers.YoutubeController;
import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.Items.Youtube;
import com.example.khaledsabry.entertainment.R;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.ui.menu.MenuItem;
import com.pierfrancescosoffritti.androidyoutubeplayer.ui.menu.YouTubePlayerMenu;

import java.util.ArrayList;


public class YoutubeCustomFragment extends Fragment {
    public enum Type {
        movie,
        tv,
        artist
    }

    public static DrawerLayout drawerLayout;
    NavigationView navigationView;
    static RecyclerView recyclerView;
    RecyclerView options;
    YoutubeController controller;
    static YoutubeFragment.Type type;
    ArrayList<String> youtubeVideoArrayList = new ArrayList<>();
    ArrayList<Youtube> youtubes = new ArrayList<>();
    YouTubePlayerSupportFragment youTubePlayerFragment;
    static Movie movie;
    static Tv tv;
    static Artist artist;
    static Object item;
    public static YouTubePlayer youTubePlayer;
    ArrayList<YoutubeController.Type> types;
    ArrayList<String> titles;
    YoutubeOptionAdapter optionAdapter;
    public static YoutubeCustomFragment newInstance(Object item, YoutubeFragment.Type type) {
        YoutubeCustomFragment fragment = new YoutubeCustomFragment();
        fragment.type = type;
        YoutubeCustomFragment.item = item;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_youtube_custom, container, false);
        recyclerView = view.findViewById(R.id.recycler_id);
        options = view.findViewById(R.id.recycleroptionsid);
        drawerLayout = view.findViewById(R.id.drawer_layout);
        YouTubePlayerView youtubePlayerView = view.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youtubePlayerView);
        youtubePlayerView.getPlayerUIController().setVideoTitle("sdfsdf");
      PlayerConstants.PlaybackQuality[] d =  PlayerConstants.PlaybackQuality.values();
        youtubePlayerView.getPlayerUIController().setFullScreenButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
youTubePlayer.loadVideo("sdf",0);
        youtubePlayerView.initialize(new YouTubePlayerInitListener() {
            @Override
            public void onInitSuccess(@NonNull final YouTubePlayer initializedYouTubePlayer) {
                initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady() {
                    //    String videoId = "6JYIGclVQdw";
                        youTubePlayer = initializedYouTubePlayer;

                     //   initializedYouTubePlayer.loadVideo(videoId, 0);
                    }

                                                         @Override
                                                         public void onPlaybackQualityChange(@NonNull PlayerConstants.PlaybackQuality playbackQuality) {
                                                             super.onPlaybackQualityChange(playbackQuality);
                                                             PlayerConstants.PlaybackQuality.values();
                                                         }
                                                     }


                );
            }
        }, true);

        types = new ArrayList<>();
        titles = new ArrayList<>();
        options.setHasFixedSize(true);

        recyclerView.setHasFixedSize(true);
        options.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        if (type == YoutubeFragment.Type.movie)
            loadMovie();
        return view;
    }
    private void loadMovie() {
        movie = (Movie) item;
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


        optionAdapter = new YoutubeOptionAdapter(types, titles, movie.getTitle(), movie.getYear());
        options.setAdapter(optionAdapter);


    }

    public static void loadVideos(ArrayList<Youtube> youtubes) {

        YoutubeVideoAdapter adapter = new YoutubeVideoAdapter(youtubes);
        recyclerView.setAdapter(adapter);
        drawerLayout.openDrawer(GravityCompat.END, true);
        if(youTubePlayer != null)
            youTubePlayer.play();

    }
}
