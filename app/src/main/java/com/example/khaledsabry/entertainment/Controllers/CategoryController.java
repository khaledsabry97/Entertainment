package com.example.khaledsabry.entertainment.Controllers;

import android.provider.SyncStateContract;

import com.example.khaledsabry.entertainment.Interfaces.OnDatabaseSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;

/**
 * Created by KhALeD SaBrY on 03-Aug-18.
 */

public class CategoryController extends Controller {



    public void addFavourite(String tmdbId, String imdbId, int type, final OnSuccess.bool listener)
    {


        databaseController.insertController().categoryAdd(null,constants.favourite, userData.getUserId(), tmdbId, imdbId, type, null, new OnDatabaseSuccess.bool() {
            @Override
            public void onSuccess(boolean state) {
                listener.onSuccess(state);
            }
        });
    }


    public void addHistory(String tmdbId, String imdbId, int type, final OnSuccess.bool listener)
    {


        databaseController.insertController().categoryAdd(null,constants.history, userData.getUserId(), tmdbId, imdbId, type, null, new OnDatabaseSuccess.bool() {
            @Override
            public void onSuccess(boolean state) {
                listener.onSuccess(state);
            }
        });
    }
}
