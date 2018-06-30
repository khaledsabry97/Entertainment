package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.R;

public class FullPoster extends Fragment {
    static String poster;
    ImageView fullPoster;
    public static FullPoster newInstance(String poster) {
        FullPoster fragment = new FullPoster();
        FullPoster.poster = poster;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
View v = inflater.inflate(R.layout.fragment_full_poster, container, false);
        fullPoster = v.findViewById(R.id.fullposterid);
        ImageController.putImageHighQuality(poster,fullPoster);
        return v;
    }

}
