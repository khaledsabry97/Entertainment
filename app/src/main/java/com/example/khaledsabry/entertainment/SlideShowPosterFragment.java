package com.example.khaledsabry.entertainment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapter.ViewPagerAdapter;
import com.example.khaledsabry.entertainment.Items.Movie;


public class SlideShowPosterFragment extends Fragment {

    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
   static Movie movie;
    public static SlideShowPosterFragment newInstance(Movie movie) {
        SlideShowPosterFragment fragment = new SlideShowPosterFragment();
        SlideShowPosterFragment.movie = movie;

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_slide_show_poster, container, false);
        viewPager = v.findViewById(R.id.viewPagerid);
       // viewPagerAdapter = new ViewPagerAdapter(getContext(),movie.getPosters());
        viewPager.setAdapter(viewPagerAdapter);
        return v;
    }

}
