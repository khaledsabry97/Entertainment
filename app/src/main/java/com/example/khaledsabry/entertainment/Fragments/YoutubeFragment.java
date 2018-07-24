package com.example.khaledsabry.entertainment.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.khaledsabry.entertainment.Adapter.YoutubeVideoAdapter;
import com.example.khaledsabry.entertainment.Controllers.Settings;
import com.example.khaledsabry.entertainment.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.ArrayList;


public class YoutubeFragment extends Fragment  {

    RecyclerView recyclerView;

    public static YouTubePlayer youTubePlayer;
    ArrayList<String> youtubeVideoArrayList = new ArrayList<>();

    YouTubePlayerSupportFragment youTubePlayerFragment;

    public static YoutubeFragment newInstance(String movieName, Integer movieId) {
        YoutubeFragment fragment = new YoutubeFragment();
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_youtube, container, false);
        recyclerView = v.findViewById(R.id.recycler_view);
        youTubePlayerFragment = (YouTubePlayerSupportFragment) getChildFragmentManager().findFragmentById(R.id.youtube_player_fragment);

        youtubeVideoArrayList.add("aJ7BoNG-r2c");
        youtubeVideoArrayList.add("KHz_QSUIRvc");



        initializeYoutubePlayer();
        recyclerView.setHasFixedSize(true);

        //Horizontal direction recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        populateRecyclerView();
        return v;
    }


    private void initializeYoutubePlayer() {

        if (youTubePlayerFragment == null)
            return;

        youTubePlayerFragment.initialize(Settings.YoutubeApiKey, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                                boolean wasRestored) {
                if (!wasRestored) {
                    youTubePlayer = player;

                    //set the player style default
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

                    //cue the 1st video by default
                    youTubePlayer.cueVideo(youtubeVideoArrayList.get(0));
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
    /**
     * populate the recycler view and implement the click event here
     */
    private void populateRecyclerView() {
        final YoutubeVideoAdapter adapter = new YoutubeVideoAdapter(youtubeVideoArrayList);
        recyclerView.setAdapter(adapter);
        //set click event

    }
}
