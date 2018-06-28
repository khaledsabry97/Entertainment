package com.example.khaledsabry.entertainment.Fragments;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Interfaces.OnImageConvertedSuccess;
import com.example.khaledsabry.entertainment.R;
import com.squareup.picasso.Picasso;


public class MovieDetailFragment extends Fragment {

    ImageView posterImage;
    TextView title;
    TextView overview;
    static Movie movie;

    public static MovieDetailFragment newInstance(Movie movie) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.movie = movie;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        posterImage = v.findViewById(R.id.posterid);
        title = v.findViewById(R.id.titleId);
        overview = v.findViewById(R.id.overviewID);
        setObjects();
       // Picasso.get().load(ImageController.getImageHighQualityUrl(movie.getPosterImage())).into(posterImage);
        posterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullPoster fullPoster = FullPoster.newInstance(movie.getPosterImage());
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,fullPoster).addToBackStack(null).commit();
            //    MainActivity.getActivity().loadFullPosterFragment(movie.getPosterImage());
            }
        });

        setCast();

        return v;
    }


    public void setObjects() {
        title.setText(movie.getTitle());
        overview.setText(movie.getOverView());

        ImageController.putImageMidQuality(movie.getPosterImage(), posterImage);


    }


    private void setCast()
    {
        CastFragment castFragment = CastFragment.newInstance(movie);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.castid,castFragment).commit();
    }
}
