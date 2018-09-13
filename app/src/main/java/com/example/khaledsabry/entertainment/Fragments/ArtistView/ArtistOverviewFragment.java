package com.example.khaledsabry.entertainment.Fragments.ArtistView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Adapters.MainPosterViewPager;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;


public class ArtistOverviewFragment extends Fragment {
    TextView name;
    TextView biography;
    TextView birhtDate;
    TextView diedIn;
    TextView placeOfBirth;

     Artist artist;
     TextView overviewTitleId;

    public static ArtistOverviewFragment newInstance(Artist artist) {
        ArtistOverviewFragment fragment = new ArtistOverviewFragment();
fragment.artist = artist;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_artist_overview, container, false);
        name = view.findViewById(R.id.title_id);
        biography = view.findViewById(R.id.overview_id);
        birhtDate = view.findViewById(R.id.birth_date_id);
        diedIn = view.findViewById(R.id.died_date_id);
        placeOfBirth = view.findViewById(R.id.place_of_birth_id);
        overviewTitleId = view.findViewById(R.id.overview_title_id);
        setObjects();
        return view;
    }

    private void setObjects() {

overviewTitleId.setText("Biography");
        name.setText(artist.getName());
        birhtDate.setText(artist.getBirthDay());
        diedIn.setText(artist.getDeathDay());
        placeOfBirth.setText(artist.getPlaceOfBirth());
        biography.setText(artist.getBiography());





    }


}
