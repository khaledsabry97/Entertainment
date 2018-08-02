package com.example.khaledsabry.entertainment.Database;

/**
 * Created by KhALeD SaBrY on 02-Aug-18.
 */

public class UserData {

    private static String username;


    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserData.username = username;
    }
}
