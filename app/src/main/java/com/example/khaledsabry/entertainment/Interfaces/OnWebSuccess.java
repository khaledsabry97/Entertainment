package com.example.khaledsabry.entertainment.Interfaces;

import com.example.khaledsabry.entertainment.Items.Movie;
import com.example.khaledsabry.entertainment.Items.News;
import com.example.khaledsabry.entertainment.Items.Review;
import com.example.khaledsabry.entertainment.Items.Torrent;
import com.example.khaledsabry.entertainment.Items.Tv;

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

    public interface OnNews
    {
        void onSuccess(ArrayList<News> news);

    }

    public interface OnMovie
    {
        void onSuccess(Movie movie);

    }

     interface OnTv
    {
        void onSuccess(Tv tv);

    }

    interface OnReviews
    {
        void onSuccess(ArrayList<Review> reviews);
    }
}
