package com.example.khaledsabry.entertainment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.khaledsabry.entertainment.Controllers.Settings;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;


public class YoutubeFragment extends Fragment implements YouTubePlayer.OnInitializedListener {
    String videoId = "aJ7BoNG-r2c";

    private YouTubePlayer YPlayer;
    private static final int RECOVERY_DIALOG_REQUEST = 1;


    static String trailerId;

    public static YoutubeFragment newInstance(String trailerId) {
        YoutubeFragment fragment = new YoutubeFragment();
        YoutubeFragment.trailerId = trailerId;
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_youtube, container, false);
        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        getChildFragmentManager().beginTransaction().replace(R.id.youtube_fragment, youTubePlayerFragment).addToBackStack(null).commit();

        youTubePlayerFragment.initialize(Settings.YoutubeApiKey, this);
        return v;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b)
            youTubePlayer.cueVideo(trailerId);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this.getActivity(), 1).show();
        } else {
            Toast.makeText(this.getActivity(),
                    "YouTubePlayer.onInitializationFailure(): " + youTubeInitializationResult.toString(),
                    Toast.LENGTH_LONG).show();
        }
        {

        }
    }

}
