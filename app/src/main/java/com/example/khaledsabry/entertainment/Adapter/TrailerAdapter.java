package com.example.khaledsabry.entertainment.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.R;
import com.example.khaledsabry.entertainment.Settings;
import com.example.khaledsabry.entertainment.YoutubeFragment;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 03-Jul-18.
 */

public class TrailerAdapter extends FragmentPagerAdapter {
    ArrayList<String> videosId = new ArrayList<>();



    public TrailerAdapter(FragmentManager fm, ArrayList<String> videosId) {
        super(fm);
        this.videosId = videosId;
    }

    @Override
    public Fragment getItem(int position) {
        return YoutubeFragment.newInstance(videosId.get(position));
    }

    @Override
    public int getCount() {
        return videosId.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return  view.equals(object);
    }
}
