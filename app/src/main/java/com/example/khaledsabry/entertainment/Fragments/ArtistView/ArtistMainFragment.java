package com.example.khaledsabry.entertainment.Fragments.ArtistView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Adapters.MainPosterViewPager;
import com.example.khaledsabry.entertainment.Controllers.Functions;
import com.example.khaledsabry.entertainment.Controllers.TmdbController;
import com.example.khaledsabry.entertainment.Interfaces.OnArtistDataSuccess;
import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.R;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class ArtistMainFragment extends Fragment {
    TextView name;
    TextView biography;
    TextView birhtDate;
    TextView diedIn;
    TextView placeOfBirth;
    TextView profession;
    ViewPager viewPager;
    MainPosterViewPager mainPosterViewPager;
    CircleIndicator indicator;
    static Artist artist;
static int id;
static int currentId = -1;
static int currentImagesId =-1;
static ArrayList<String> images = new ArrayList<>();
TmdbController tmdbController = new TmdbController();
    public static ArtistMainFragment newInstance(int artistId) {
        ArtistMainFragment fragment = new ArtistMainFragment();
        ArtistMainFragment.id = artistId;
        Bundle args = new Bundle();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_artist_main, container, false);

        name = v.findViewById(R.id.nameid);
        biography = v.findViewById(R.id.bigraphyid);
        birhtDate = v.findViewById(R.id.birthdayid);
        diedIn = v.findViewById(R.id.diedid);
        placeOfBirth = v.findViewById(R.id.placeofbirthid);
        viewPager = v.findViewById(R.id.view_pager_id);
        indicator = v.findViewById(R.id.indicator);
loadFragment();
        return v;
    }

    private void setObjects() {


        name.setText(artist.getName());
        birhtDate.setText(artist.getBirthDay());
        diedIn.setText(artist.getDeathDay());
        placeOfBirth.setText(artist.getPlaceOfBirth());
        biography.setText(artist.getBiography());

        loadPosters();




    }

    private void loadPosters() {

        if(id != currentImagesId)
        {
            tmdbController.getPersonImages(id, new OnArtistDataSuccess() {
                @Override
                public void onSuccess(Artist artist) {
                    images.clear();
                    images.addAll(artist.getImages());
                    currentImagesId = id;
                    setImages();
                }
            });
        }
        else
            setImages();


    }

    private void setImages() {
        ArrayList<String> images = new ArrayList<>();
        images.add(artist.getPosterImage());
        images.addAll(this.images);
        mainPosterViewPager = new MainPosterViewPager(images);
        viewPager.setAdapter(mainPosterViewPager);
        indicator.setViewPager(viewPager);
        Functions functions = new Functions();

        functions.movePoster(viewPager,mainPosterViewPager,1000,2000);

    }

    private void loadFragment() {
        if(id != currentId)
        {
            tmdbController.getPersonDetails(id, new OnArtistDataSuccess() {
                @Override
                public void onSuccess(Artist artist) {
                    ArtistMainFragment.artist = artist;
                    currentId = id;
                    setObjects();
                }
            });
        }
        else
            setObjects();

    }


}
