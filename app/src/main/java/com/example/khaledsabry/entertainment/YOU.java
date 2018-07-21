package com.example.khaledsabry.entertainment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Adapter.TrailerAdapter;

import java.util.ArrayList;


public class YOU extends Fragment {

    static String trailerId;
     ArrayList<String> trailers = new ArrayList<>();
    public static YOU newInstance(String trailerId) {
        YOU fragment = new YOU();
        YOU.trailerId = trailerId;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gdfgfdg, container, false);
        ViewPager viewPager =v.findViewById(R.id.viewPagerid);
       trailers.add(trailerId);
        trailers.add("KHz_QSUIRvc");
        TrailerAdapter trailerAdapter;
        trailerAdapter = new TrailerAdapter(trailers);


        viewPager.setAdapter(trailerAdapter);
       // viewPager.setCurrentItem(0);
     //   getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.hello,YoutubeFragment.newInstance(trailerId)).commit();
        return v;
    }

}
