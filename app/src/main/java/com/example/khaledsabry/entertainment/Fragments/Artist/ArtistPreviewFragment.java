package com.example.khaledsabry.entertainment.Fragments.Artist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Items.Artist;
import com.example.khaledsabry.entertainment.R;


public class ArtistPreviewFragment extends Fragment {

    Artist artist;
    ImageView poster;
    TextView title;
    TextView rate;
    TextView birthDate;
    TextView biography;
    TextView placeOfBirth;
    public static ArtistPreviewFragment newInstance(Artist artist) {
        ArtistPreviewFragment fragment = new ArtistPreviewFragment();
fragment.artist = artist;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artist_preview, container, false);

        title = view.findViewById(R.id.titleid);
        rate = view.findViewById(R.id.rateid);

        birthDate = view.findViewById(R.id.birthdayid);

        biography = view.findViewById(R.id.biographyid);
        poster = view.findViewById(R.id.posterid);
        placeOfBirth = view.findViewById(R.id.placeofbirthid);

        setObjects();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, ArtistNavigationFragment.newInstance(artist.getId(),true)).addToBackStack(null).commit();
            }
        });



        return view;
    }

    private void setObjects() {
        title.setText(artist.getName());
     //   rate.setText(ar.getRateTmdb()+"/10");

        placeOfBirth.setText(artist.getPlaceOfBirth());
        biography.setText(artist.getBiography());
        birthDate.setText("Birth day: " +artist.getBirthDay());
        ImageController.putImageMidQuality(artist.getPosterImage(),poster);

    }

}
