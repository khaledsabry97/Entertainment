package com.example.khaledsabry.entertainment.Fragments.Artist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.R;


public class ArtistPreviewFragment extends Fragment {

    public static ArtistPreviewFragment newInstance() {
        ArtistPreviewFragment fragment = new ArtistPreviewFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artist_preview, container, false);
        return view;
    }

}
