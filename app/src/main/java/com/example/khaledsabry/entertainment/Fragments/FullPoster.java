package com.example.khaledsabry.entertainment.Fragments;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.khaledsabry.entertainment.Controllers.ImageController;
import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Interfaces.OnImageConvertedSuccess;
import com.example.khaledsabry.entertainment.R;
import com.squareup.picasso.Picasso;


public class FullPoster extends Fragment {
    static String poster;
    ImageView fullPoster;

    public static FullPoster newInstance(String poster) {
        FullPoster fragment = new FullPoster();
        fragment.poster = poster;

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
ImageController.putImageHighQuality(poster,fullPoster);
        /*
        ImageController.getImageHighQuality(poster, new OnImageConvertedSuccess() {
            @Override
            public void onImageConvertedSuccess(Bitmap bitmap) {
                fullPoster.setImageBitmap(bitmap);

            }
        });
*/
    }

}
