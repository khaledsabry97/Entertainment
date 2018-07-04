package com.example.khaledsabry.entertainment.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.example.khaledsabry.entertainment.YoutubeFragment;

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
        return view.equals(object);
    }
}
