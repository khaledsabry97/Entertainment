package com.example.khaledsabry.entertainment.Fragments.TvView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.R;

public class TvTorrentFragment extends Fragment {

    public static TvTorrentFragment newInstance() {
        TvTorrentFragment fragment = new TvTorrentFragment();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_torrent, container, false);


        return view;
    }

}
