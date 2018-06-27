package com.example.khaledsabry.entertainment.Fragments;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.khaledsabry.entertainment.Controllers.Controller;
import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.OnImageConvertedSuccess;
import com.example.khaledsabry.entertainment.R;


public class FullPoster extends Fragment {
    Movie movie;
    ImageView fullPoster;

    public static FullPoster newInstance(Movie movie) {
        FullPoster fragment = new FullPoster();
        fragment.movie = movie;

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_full_poster, container, false);
        fullPoster = v.findViewById(R.id.fullposterid);
        setPoster();
        return v;
    }

    private void setPoster()
    {
        ImageController.getImageHighQuality(movie.getPosterImage(), new OnImageConvertedSuccess() {
            @Override
            public void onImageConvertedSuccess(Bitmap bitmap) {
                fullPoster.setImageBitmap(bitmap);

            }
        });

    }

}
