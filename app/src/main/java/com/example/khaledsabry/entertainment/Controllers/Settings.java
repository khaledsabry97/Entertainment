package com.example.khaledsabry.entertainment.Controllers;

import android.util.DisplayMetrics;
import android.view.WindowManager;

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

    public static String YoutubeApiKey = "AIzaSyDyMlMX1NEZJUggZdxiFErwuocJSYm7Wp4";
    public static String TmdbApiKey = "3628f9974c19710b3a434cf958458799";

    public void setWidthAndHeight(WindowManager windowManager)
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
    }

}
