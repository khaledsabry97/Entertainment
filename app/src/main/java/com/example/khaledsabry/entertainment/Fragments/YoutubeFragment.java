package com.example.khaledsabry.entertainment.Fragments;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.khaledsabry.entertainment.Adapter.YoutubeOptionAdapter;
import com.example.khaledsabry.entertainment.Adapter.YoutubeVideoAdapter;
import com.example.khaledsabry.entertainment.Controllers.Constants;
import com.example.khaledsabry.entertainment.Controllers.YoutubeController;
import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Tv;
import com.example.khaledsabry.entertainment.Items.Youtube;
import com.example.khaledsabry.entertainment.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.ArrayList;


public class YoutubeFragment extends Fragment {

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
    static Type type;
    public static YouTubePlayer youTubePlayer;
    ArrayList<String> youtubeVideoArrayList = new ArrayList<>();
    ArrayList<Youtube> youtubes = new ArrayList<>();
    YouTubePlayerSupportFragment youTubePlayerFragment;
    static Movie movie;
    static Tv tv;
    static Artist artist;
    static Object item;
    ArrayList<YoutubeController.Type> types;
    ArrayList<String> titles;
    YoutubeOptionAdapter optionAdapter;

    public static YoutubeFragment newInstance(Object item, Type type) {
        YoutubeFragment fragment = new YoutubeFragment();
        fragment.type = type;
        YoutubeFragment.item = item;

        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_youtube, container, false);
        recyclerView = v.findViewById(R.id.recyclerid);
        options = v.findViewById(R.id.recycleroptionsid);
        drawerLayout = v.findViewById(R.id.drawer_layout);
        youTubePlayerFragment = (YouTubePlayerSupportFragment) getChildFragmentManager().findFragmentById(R.id.youtube_player_fragment);
        youTubePlayerFragment.setAllowEnterTransitionOverlap(true);
        types = new ArrayList<>();
        titles = new ArrayList<>();
        initializeYoutubePlayer();
        options.setHasFixedSize(true);

        recyclerView.setHasFixedSize(true);
        options.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        if (type == Type.movie)
            loadMovie();


        return v;
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


    private void initializeYoutubePlayer() {

        if (youTubePlayerFragment == null)
            return;
        youTubePlayerFragment.initialize(Constants.YoutubeApiKey, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                                boolean wasRestored) {
                if (!wasRestored) {
                    youTubePlayer = player;

                    //set the player style default
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    youTubePlayer.play();

                    youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                        @Override
                        public void onLoading() {

                        }

                        @Override
                        public void onLoaded(String s) {

                        }

                        @Override
                        public void onAdStarted() {

                        }

                        @Override
                        public void onVideoStarted() {

                        }

                        @Override
                        public void onVideoEnded() {

                        }

                        @Override
                        public void onError(YouTubePlayer.ErrorReason errorReason) {
                            if (errorReason.toString().equals("UNAUTHORIZED_OVERLAY") && youTubePlayer != null) {

                            }
                        }


                    });


                    youTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
                        @Override
                        public void onPlaying() {

                        }

                        @Override
                        public void onPaused() {

                        }

                        @Override
                        public void onStopped() {

                        }

                        @Override
                        public void onBuffering(boolean b) {

                        }

                        @Override
                        public void onSeekTo(int i) {

                        }
                    });

//cue the 1st video by default
//                    youTubePlayer.cueVideo(youtubes.get(0).getId());
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult youTubeInitializationResult) {

                if (youTubeInitializationResult.isUserRecoverableError()) {
                    youTubeInitializationResult.getErrorDialog(getActivity(), 1).show();
                } else {
                    Toast.makeText(getContext(),
                            "YouTubePlayer.onInitializationFailure(): " + youTubeInitializationResult.toString(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public static void loadVideos(ArrayList<Youtube> youtubes) {
        YoutubeVideoAdapter adapter = new YoutubeVideoAdapter(youtubes);
        recyclerView.setAdapter(adapter);
        drawerLayout.openDrawer(GravityCompat.END, true);
        youTubePlayer.play();

    }


    @Override
    public void onPause() {
        super.onPause();



    }
}


