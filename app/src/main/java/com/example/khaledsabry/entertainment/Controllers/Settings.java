package com.example.khaledsabry.entertainment.Controllers;

import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.google.android.youtube.player.YouTubePlayer;

/**
 * Created by KhALeD SaBrY on 01-Jul-18.
 */

public class Settings {

    public int width;
    public int height;

    private static final Settings ourInstance = new Settings();

    public static Settings getInstance() {
        return ourInstance;
    }

    private Settings() {
    }

    public static  final String YoutubeApiKey = "AIzaSyDyMlMX1NEZJUggZdxiFErwuocJSYm7Wp4";
    public static final String TmdbApiKey = "3628f9974c19710b3a434cf958458799";
    public static final String youtubeConnectionBaseUrl = "https://www.googleapis.com/youtube/v3/search?key="+YoutubeApiKey+"&part=snippet";

    public void setWidthAndHeight(WindowManager windowManager)
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
    }

}
