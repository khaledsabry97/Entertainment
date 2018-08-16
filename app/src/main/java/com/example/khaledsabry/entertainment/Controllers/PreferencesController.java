package com.example.khaledsabry.entertainment.Controllers;

import android.support.v7.widget.LinearSmoothScroller;

import com.example.khaledsabry.entertainment.Database.UserData;
import com.example.khaledsabry.entertainment.Interfaces.OnDatabaseSuccess;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;

/**
 * Created by KhALeD SaBrY on 15-Aug-18.
 */

public class PreferencesController extends Controller {

    public void updateProfileImage(String image64, final OnSuccess.bool listener)
    {
        databaseController.updateController().userUpdateProfileImage(UserData.getInstance().getUserId(),userTable.profileImage,image64, new OnDatabaseSuccess.bool() {
                    @Override
                    public void onSuccess(boolean state) {
                        listener.onSuccess(state);
                    }
                }
        );
    }
}
