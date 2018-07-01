package com.example.khaledsabry.entertainment;

/**
 * Created by KhALeD SaBrY on 01-Jul-18.
 */

public class Settings {
    private static final Settings ourInstance = new Settings();

    public static Settings getInstance() {
        return ourInstance;
    }

    private Settings() {
    }

    public static String YoutubeApiKey = "AIzaSyDyMlMX1NEZJUggZdxiFErwuocJSYm7Wp4";

}
