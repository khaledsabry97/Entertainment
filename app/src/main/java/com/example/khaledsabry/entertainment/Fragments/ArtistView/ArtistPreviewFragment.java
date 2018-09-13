package com.example.khaledsabry.entertainment.Fragments.ArtistView;


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
    View view;

    public static ArtistPreviewFragment newInstance(Artist artist) {
        ArtistPreviewFragment fragment = new ArtistPreviewFragment();
        fragment.artist = artist;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_movie_preview, container, false);

        title = view.findViewById(R.id.title_id);
        rate = view.findViewById(R.id.rate_id);

        birthDate = view.findViewById(R.id.release_data_id);

        biography = view.findViewById(R.id.overview_id);
        poster = view.findViewById(R.id.backdrop_id);
        placeOfBirth = view.findViewById(R.id.genres_id);

        //adjust the poster view to show a poster not a backdrop
        poster.setScaleType(ImageView.ScaleType.FIT_CENTER);

        setObjects();


        return view;
    }

    /**
     * set the objects on the ui fragment
     */
    private void setObjects() {
        if(artist == null)
            return;
        title.setText(artist.getName());
        //   rate.setText(ar.getRateTmdb()+"/10");
        rate.setVisibility(View.GONE);
        if(artist.getPlaceOfBirth() == null)
            placeOfBirth.setText("");
        else
        placeOfBirth.setText(artist.getPlaceOfBirth());
        biography.setText(artist.getBiography());
        if(artist.getBirthDay() == null)
            birthDate.setVisibility(View.INVISIBLE);
        else
        birthDate.setText("Birth day: " + artist.getBirthDay());
        ImageController.putImageMidQuality(artist.getPosterImage(), poster);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.loadFragmentNoReturn(R.id.mainContainer, ArtistNavigationFragment.newInstance(artist.getId(), 0));
            }
        });


        adjustView(title);
        adjustView(rate);
        adjustView(placeOfBirth);
        adjustView(biography);
        adjustView(birthDate);
    }


    /**
     * invisible the ui view if there is no text in it
     * @param textView the ui element
     */
    void adjustView(TextView textView)
    {
        if(textView.getText().toString().equals(""))
            textView.setVisibility(View.INVISIBLE);
    }

}
