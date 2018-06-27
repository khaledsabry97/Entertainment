package com.example.khaledsabry.entertainment.Activities;

import android.widget.ImageView;

import com.example.khaledsabry.entertainment.Activities.MainActivity;

/**
 * Created by KhALeD SaBrY on 23-Jun-18.
 */

public class CharacterCard {

    private final String DRAWABLE = "drawable/";

    private String title;
    private String imgUri;
    private int resource;

    public CharacterCard(String title, String imgUri) {
        this.title = title;
        this.imgUri = imgUri;
    }

    public String getTitle() {
        return title;
    }

    public int getResource() {
        ImageView imageView;
        imageView = new ImageView(MainActivity.getActivity());
        resource = imageView.getResources().getIdentifier(getImgUri(), null, imageView.getContext().getPackageName());
        return  resource;
    }

    public String getImgUri() {
        return DRAWABLE+imgUri;
    }

    public String getDRAWABLE() {
        return DRAWABLE;
    }

}
