package com.example.khaledsabry.entertainment.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.R;

import java.io.InputStream;
import java.net.URL;


public class MovieDetailFragment extends Fragment {

    ImageView posterImage;
    TextView title;
    TextView overview;
    Movie movie;

    public static MovieDetailFragment newInstance(Movie movie) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.movie = movie;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v  = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        posterImage = v.findViewById(R.id.posterid);
        title = v.findViewById(R.id.titleId);
        overview = v.findViewById(R.id.overviewID);







        return v;
    }


    private void setObjects()
    {
        title.setText(movie.getTitle());
        overview.setText(movie.getOverView());
        po
    }

    public void show(final Movie movie)
    {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                String urlOfImage = movie.getPostorImage();
                Bitmap logo = null;
                try{
                    InputStream is = new URL(urlOfImage).openStream();
                    logo = BitmapFactory.decodeStream(is);
                }catch(Exception e){
                    // Catch the download exception
                    e.printStackTrace();
                }

                final Bitmap finalLogo = logo;


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(finalLogo);
                    }
                });

            }
        });
    }

}
