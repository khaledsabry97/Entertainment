package com.example.khaledsabry.entertainment.Interfaces;

import com.example.khaledsabry.entertainment.Items.Artist;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 05-Jul-18.
 */

public interface OnArtistDataSuccess {
    void onSuccess(Artist artist);
   public interface List{
        void onSuccess(ArrayList<Artist> artists);
    }
}
