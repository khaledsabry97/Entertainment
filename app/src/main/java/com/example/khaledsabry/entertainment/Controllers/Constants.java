package com.example.khaledsabry.entertainment.Controllers;

import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.net.PortUnreachableException;

/**
 * Created by KhALeD SaBrY on 01-Jul-18.
 */

public class Constants {

    private int width;
    private int height;
    private boolean isTablet = false;
    public static  final String YoutubeApiKey = "AIzaSyDKkRa54f_6sidlzY6PSD4ySKbrH1jVfWE";
    public static final String TmdbApiKey = "3628f9974c19710b3a434cf958458799";
    public static final String youtubeConnectionSearchBaseUrl = "https://www.googleapis.com/youtube/v3/search?key="+YoutubeApiKey+"&part=snippet";
    public static final String youtubeConnectionPlaylistBaseUrl = "https://www.googleapis.com/youtube/v3/playlistItems?key="+YoutubeApiKey+"&part=snippet";
    public static final String tmdbBaseUrl = "https://api.themoviedb.org/3/";
    public static final String latestTrailersPlaylist = "PLScC8g4bqD47c-qHlsfhGH3j6Bg7jzFy-";

    public String getTmdbBaseUrl() {
        return tmdbBaseUrl;
    }

    public boolean isTablet() {
        return isTablet;
    }

    public void setTablet(boolean tablet) {
        isTablet = tablet;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private static final Constants ourInstance = new Constants();

    public static Constants getInstance() {
        return ourInstance;
    }

    private Constants() {
    }



    public void setWidthAndHeight(WindowManager windowManager)
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
    }

}
