package com.example.khaledsabry.entertainment.Database;

import android.support.v4.app.NavUtils;

/**
 * Created by KhALeD SaBrY on 02-Aug-18.
 */

public class UserData {

    private  String username;
    private  Integer userId;
    private  String email;
    private static UserData userData = null;


    private UserData() {

    }

    public static UserData getInstance() {
        if (userData == null)
            userData = new UserData();
        return userData;
    }


    public  Integer getUserId() {
        return userId;
    }

    public  void setUserId(Integer userId) {
        this.userId = userId;
    }

    public  String getUsername() {
        return username;
    }

    public  void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
