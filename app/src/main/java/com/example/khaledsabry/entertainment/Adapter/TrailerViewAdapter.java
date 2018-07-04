package com.example.khaledsabry.entertainment.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.khaledsabry.entertainment.R;
import com.example.khaledsabry.entertainment.Controllers.Settings;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.ArrayList;

import static com.example.khaledsabry.entertainment.Activities.MainActivity.getActivity;

/**
 * Created by KhALeD SaBrY on 03-Jul-18.
 */

public class TrailerViewAdapter extends PagerAdapter implements YouTubePlayer.OnInitializedListener {
    ArrayList<String> videosId = new ArrayList<>();
    int pos = 0;

    android.support.v4.app.FragmentManager g;
    Fragment f;


      public TrailerViewAdapter(ArrayList<String> videosId,Fragment f) {
          this.videosId = videosId;
          this.f = f;
      }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_youtube,container,false);
        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        f.getChildFragmentManager().beginTransaction().add(R.id.youtube_fragment,youTubePlayerFragment).commit();
pos = position;
        youTubePlayerFragment.initialize(Settings.YoutubeApiKey, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(!b)
                    youTubePlayer.cueVideo(videosId.get(position));
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
        container.addView(v,position);
        return v;
    }

    @Override
    public int getCount() {
        return videosId.size();
    }




    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {


    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(getActivity(),1).show();
        } else {
            Toast.makeText(getActivity(),
                    "YouTubePlayer.onInitializationFailure(): " + youTubeInitializationResult.toString(),
                    Toast.LENGTH_LONG).show();
        }
        {

        }
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //container.removeView((View) object);
    }
}
