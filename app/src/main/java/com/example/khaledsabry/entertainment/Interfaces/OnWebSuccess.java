package com.example.khaledsabry.entertainment.Interfaces;

import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.Torrent;

import java.util.ArrayList;

/**
 * Created by KhALeD SaBrY on 14-Jul-18.
 */

public interface OnWebSuccess {

    public interface OnTorrentSearch
    {
        void onSuccess(ArrayList<Torrent> torrents);

    }

    public interface OnMovieList
    {
        void onSuccess(ArrayList<Movie> movies);

    }
}
