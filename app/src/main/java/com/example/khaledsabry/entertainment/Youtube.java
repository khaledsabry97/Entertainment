package com.example.khaledsabry.entertainment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.Settings;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;


public class Youtube extends Fragment implements YouTubePlayer.OnInitializedListener {



        String videoId = "aJ7BoNG-r2c";
        private YouTubePlayer Player;
        private static final int RECOVERY_DIALOG_REQUEST = 1;


        static String trailerId;
    YouTubePlayerSupportFragment youTubePlayerFragment;
        public static Youtube newInstance(String trailerId) {
            Youtube fragment = new Youtube();
            Youtube.trailerId = trailerId;

            return fragment;

        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View v = inflater.inflate(R.layout.fragment_youtube, container, false);
             youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
            youTubePlayerFragment.initialize(Settings.YoutubeApiKey, this);
            MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.y, youTubePlayerFragment).addToBackStack(null).commit();

            return v;
        }

        @Override
        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
            if (!b)
                youTubePlayer.cueVideo(trailerId);
            Player =youTubePlayer;
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

        @Override
        public void onPause() {

            super.onPause();
            if (youTubePlayerFragment != null) {
                getChildFragmentManager().beginTransaction()
                        .remove(getChildFragmentManager().findFragmentById(R.id.y))
                        .commit();

            }
            Player.release();

        }



}
