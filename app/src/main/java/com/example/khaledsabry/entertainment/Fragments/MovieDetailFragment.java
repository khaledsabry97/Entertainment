package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.R;


public class MovieDetailFragment extends Fragment {

    ImageView posterImage;
    TextView title;
    static Movie movie;

    TextView overviewText;
    TextView releaseDate;
    TextView runTimeText;

    public static MovieDetailFragment newInstance(Movie movie) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.movie = movie;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.movie_detail_v3, container, false);
        posterImage = v.findViewById(R.id.posterid);
        title = v.findViewById(R.id.titleId);
        overviewText = v.findViewById(R.id.overviewID);
        releaseDate = v.findViewById(R.id.releasetimeid);
        runTimeText = v.findViewById(R.id.timeid);

        setObjects();
        posterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullPoster fullPoster = FullPoster.newInstance(movie.getPosterImage());
                MainActivity.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer,fullPoster).addToBackStack(null).commit();
            }
        });


        return v;
    }


    private void setObjects() {
        overviewText.setText(movie.getOverView());
        releaseDate.setText(movie.getReleaseDate());
        runTimeText.setText(movie.getRunTime()+" min");

        title.setText(movie.getTitle());

        ImageController.putImageHighQuality(movie.getPosterImage(), posterImage);

    }


}
