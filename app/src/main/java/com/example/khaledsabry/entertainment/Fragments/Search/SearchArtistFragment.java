package com.example.khaledsabry.entertainment.Fragments.Search;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchArtistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchArtistFragment extends Fragment {
    static Artist artist;
    public static SearchArtistFragment newInstance(Artist artist) {
        SearchArtistFragment fragment = new SearchArtistFragment();
        SearchArtistFragment.artist = artist;

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_search_artist, container, false);


        return view;
    }

}
